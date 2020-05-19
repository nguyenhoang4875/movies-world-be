package com.movies.controlller;

import com.movies.entity.ConfirmationToken;
import com.movies.entity.User;
import com.movies.exception.InvalidOldPasswordException;
import com.movies.service.ConfirmationTokenService;
import com.movies.service.RoleService;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/api")
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

    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody User user, HttpServletRequest request) {
        User existingUser = userService.findOneByUsername((user.getUsername()));
        if (existingUser != null){
            return new ResponseEntity<>("This user already exists!", HttpStatus.BAD_REQUEST);
        }
        else{
            user.setEnable(false);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(roleService.findOneByName("Customer"));
            userService.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenService.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("thutranglop92@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:9000/api/confirm-account?token=" + confirmationToken.getToken());
            javaMailSender.send(mailMessage);
            return new ResponseEntity<>("Successful Registration!",HttpStatus.OK);
        }
    }
    @GetMapping("/confirm-account")
    public ResponseEntity<String> confirmRegister(@RequestParam("token") String confirmationToken){
        ConfirmationToken token = confirmationTokenService.findByToken(confirmationToken);
        if (token != null){
            User user = userService.findOneByUsername(token.getUser().getUsername());
            user.setEnable(true);
            userService.save(user);
            return new ResponseEntity<>("Account verified!",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("The link is invalid or broken!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/profile")
    public User getProfile(Principal principal) {
        return userService.findOneByUsername(principal.getName());
    }

    @PutMapping("/edit-profile")
    public ResponseEntity<User> editProfile(Principal principal, @RequestBody User user) {
        User currentUser = userService.findOneByUsername(principal.getName());
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentUser.setFullName(user.getFullName());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhone(user.getPhone());
        userService.save(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public String changePassword(Principal principal, @RequestParam("password") String password,
                                 @RequestParam("oldpassword") String oldPassword) {
        User user = userService.findOneByUsername(principal.getName());
        if (!userService.checkIfValidOldPassword(user, oldPassword)) {
            throw new InvalidOldPasswordException("Old password is not correct");
        }
        userService.changeUserPassword(user, password);
        return "Change password successfully";
    }


}
