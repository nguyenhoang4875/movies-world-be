package com.movies.controlller;

import com.movies.entity.ConfirmationToken;
import com.movies.entity.Role;
import com.movies.entity.User;
import com.movies.service.ConfirmationTokenService;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private JavaMailSender javaMailSender;


    @PostMapping("/register")
    public String registerAccount(@RequestBody User user,HttpServletRequest request) {
        User existingUser = userService.findOneByUsername((user.getUsername()));
        if (existingUser != null){
            return "This email already exists!";
        }
        else{
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setEnable(false);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRole(new Role());
            userService.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenService.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("thutranglop92@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +  request.getContextPath() +"/api/confirm-account?token=" + confirmationToken.getToken());
            javaMailSender.send(mailMessage);
            return "Successful Registration!";
        }
    }
}
