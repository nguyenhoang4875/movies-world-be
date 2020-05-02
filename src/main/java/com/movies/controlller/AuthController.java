package com.movies.controlller;

import com.movies.config.TokenProvider;
import com.movies.entity.ConfirmationToken;
import com.movies.entity.Role;
import com.movies.entity.User;
import com.movies.entity.dto.AuthToken;
import com.movies.entity.dto.Login;
import com.movies.service.ConfirmationTokenService;
import com.movies.service.RoleService;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

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
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;


    @PostMapping("/register")
    public String registerAccount(@RequestBody User user, HttpServletRequest request) {
        User existingUser = userService.findOneByUsername((user.getUsername()));
        String message = "";
        if (existingUser != null) {
            message = "This user already exists!";
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setEnable(false);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:9000/api/confirm-account?token=" + confirmationToken.getToken());
            javaMailSender.send(mailMessage);
            message = "Successful Registration!";
        }
        return message;
    }

    @GetMapping("/confirm-account")
    public String confirmRegister(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenService.findByToken(confirmationToken);
        if (token != null) {
            User user = userService.findOneByUsername(token.getUser().getUsername());
            user.setEnable(true);
            userService.save(user);
            return "Account verified!";
        } else {
            return "The link is invalid or broken!";
        }
    }

    @GetMapping("/profile")
    public User getProfile(Principal principal) {
        return userService.findOneByUsername(principal.getName());
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
        return ResponseEntity.ok(new AuthToken(token));
    }

}
