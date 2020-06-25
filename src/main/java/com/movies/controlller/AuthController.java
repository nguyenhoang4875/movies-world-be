package com.movies.controlller;

import com.movies.config.TokenProvider;
import com.movies.converter.bases.Converter;
import com.movies.entity.dao.ConfirmationToken;
import com.movies.entity.dao.MessageResponse;
import com.movies.entity.dao.Role;
import com.movies.entity.dao.User;
import com.movies.entity.dto.Login;
import com.movies.entity.dto.ProfileDTO;
import com.movies.entity.dto.UserDto;
import com.movies.exception.BadRequestException;
import com.movies.exception.ConflictException;
import com.movies.exception.InvalidOldPasswordException;
import com.movies.exception.NotFoundException;
import com.movies.service.ConfirmationTokenService;
import com.movies.service.RoleService;
import com.movies.service.UserService;
import io.micrometer.core.instrument.util.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private Converter<User, UserDto> userDaoToUserDtoConverter;

    @Autowired
    private Converter<User, ProfileDTO> userProfileDTOConverter;


    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerAccount(@RequestBody @Valid User user) throws IOException, JSONException {
        User existedUser = userService.findOneByUsername((user.getUsername()));
        User existedEmail = userService.findUserByEmail(user.getEmail());
        if (existedEmail != null) {
            throw new BadRequestException("EXISTED EMAIL");
        }
        if (existedUser != null) {
            throw new BadRequestException("EXISTED USER");
        } else {
            user.setEnable(false);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Set<Role> tempRoles = new HashSet<>();
            tempRoles.add(roleService.findOneByName("ROLE_CUSTOMER"));
            user.setRoles(tempRoles);
            userService.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenService.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("thutranglop92@gmail.com");
            //create short link in firebase
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://firebasedynamiclinks.googleapis.com/v1/shortLinks?key=AIzaSyAhq8rlkSp1UBN8oLjmI8IvLubtTx03gNU");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            String link = "http://localhost/register/post?token="+ confirmationToken.getToken();

            StringEntity entity = new StringEntity("{\"dynamicLinkInfo\": {\"domainUriPrefix\": \"https://moviesworld.page.link\", \"link\": \""+link+"\",\"androidInfo\":{\"androidPackageName\": \"com.example.MovieWorld\"}}}");
            httpPost.setEntity(entity);
            HttpResponse httpResponse = client.execute(httpPost);
            String content = IOUtils.toString(httpResponse.getEntity().getContent());
            JSONObject jsonResult = new JSONObject(content);
            String shortlink = jsonResult.getString("shortLink");

            mailMessage.setText("To confirm your account, please click here : " + shortlink );
            javaMailSender.send(mailMessage);
            MessageResponse msg = new MessageResponse(
                    HttpStatus.OK.value(),
                    "SUCCESS",
                    LocalDateTime.now());
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    }

    @PostMapping("staffs/register")
    public User registerAccountByAdmin(@RequestBody User user) {
        User existingUser = userService.findOneByUsername((user.getUsername()));
        if (existingUser != null) {
            throw new ConflictException("This username already exists!");
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            Set<Role> tempRoles = new HashSet<>();
            tempRoles.add(roleService.findOneByName("ROLE_STAFF"));
            user.setRoles(tempRoles);
            return userService.save(user);
        }
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<MessageResponse> confirmRegister(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenService.findByToken(confirmationToken);
        if (token != null) {
            User user = userService.findOneByUsername(token.getUser().getUsername());
            user.setEnable(true);
            userService.save(user);
            MessageResponse msg = new MessageResponse(
                    HttpStatus.OK.value(),
                    "SUCCESS",
                    LocalDateTime.now());
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            throw new BadRequestException("INVALID LINK");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getProfile(Principal principal) {
        ProfileDTO profileDTO = userProfileDTOConverter.convert(userService.findOneByUsername(principal.getName()));
        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> login(@RequestBody Login login) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUsername(),
                        login.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        UserDto userDto = userDaoToUserDtoConverter.convert(userService.findOneByUsername(login.getUsername()));
        userDto.setExpired(tokenProvider.getExpirationDateFromToken(token));
        userDto.setToken(token);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/edit-profile")
    public ResponseEntity<ProfileDTO> editProfile(Principal principal, @RequestBody @Valid User user) {
        User currentUser = userService.findOneByUsername(principal.getName());
        if (currentUser == null) {
            throw new BadRequestException("NOT FOUND");
        }

        currentUser.setFullName(user.getFullName());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhone(user.getPhone());
        userService.save(currentUser);
        ProfileDTO profileDTO = userProfileDTOConverter.convert(currentUser);
        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<MessageResponse> changePassword(Principal principal, @RequestParam("password") String password,
                                                          @RequestParam("oldpassword") String oldPassword) {
        User user = userService.findOneByUsername(principal.getName());
        if (!userService.checkIfValidOldPassword(user, oldPassword)) {
            throw new InvalidOldPasswordException("INCORRECT OLD PASSWORD");
        }
        userService.changeUserPassword(user, password);
        MessageResponse msg = new MessageResponse(
                HttpStatus.OK.value(),
                "SUCCESS",
                LocalDateTime.now());
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestParam("email") String userEmail) throws IOException, JSONException {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new NotFoundException("USER NOT FOUND");
        }
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        //send email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("thutranglop92@gmail.com");
        //create short link in firebase
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://firebasedynamiclinks.googleapis.com/v1/shortLinks?key=AIzaSyAhq8rlkSp1UBN8oLjmI8IvLubtTx03gNU");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        String link = "http://localhost/resetPass/post?token="+ token;
        StringEntity entity = new StringEntity("{\"dynamicLinkInfo\": {\"domainUriPrefix\": \"https://moviesworld.page.link\", \"link\": \""+link+"\",\"androidInfo\":{\"androidPackageName\": \"com.example.MovieWorld\"}}}");
        httpPost.setEntity(entity);
        HttpResponse httpResponse = client.execute(httpPost);
        String content = IOUtils.toString(httpResponse.getEntity().getContent());
        JSONObject jsonResult = new JSONObject(content);
        String shortlink = jsonResult.getString("shortLink");

        mailMessage.setText("To reset your password, please click here : " + shortlink );
        javaMailSender.send(mailMessage);
        MessageResponse msg = new MessageResponse(
                HttpStatus.OK.value(),
                "SUCCESS",
                LocalDateTime.now());
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/reset-password")
    public ResponseEntity<MessageResponse> confirmResetPassword(@RequestParam("token") String token) {
        String result = userService.validatePasswordResetToken(token);
        if (result == null) {
            MessageResponse msg = new MessageResponse(
                    HttpStatus.OK.value(),
                    "VALID TOKEN",
                    LocalDateTime.now());
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            throw new BadRequestException(result);
        }
    }


    @PostMapping("save-password")
    public ResponseEntity savePassword(@RequestParam("token") String token,
                                       @RequestParam("newPassword") String newPassword) {
        String result = userService.validatePasswordResetToken(token);
        if (result != null) {
            throw new BadRequestException(result);
        } else {
            User user = userService.getUserByPasswordResetToken(token);
            if (user != null) {
                userService.changeUserPassword(user, newPassword);
                MessageResponse msg = new MessageResponse(
                        HttpStatus.OK.value(),
                        "SUCCESS",
                        LocalDateTime.now());
                return new ResponseEntity<>(msg, HttpStatus.OK);
            } else {
                throw new BadRequestException("NOT FOUND USER");
            }

        }
    }

}
