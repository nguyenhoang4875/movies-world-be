package com.movies.config;


import com.movies.entity.dao.Role;
import com.movies.entity.dao.User;
import com.movies.repository.RoleRepository;
import com.movies.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@Configuration
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Value("${jwt-key}")
    private String signingKey;


    private void addRoleIfMissing(String name, String description) {
        if (roleRepository.findByName(name) == null) {
            roleRepository.save(new Role(name, description));
        }
    }

    private void addUserIfMissing(String username, String password, String... roles) {
        if (userRepository.findByUsername(username) == null) {
            User user = new User(username, new BCryptPasswordEncoder().encode(password), "John Doe", "john@gmail.com");
            user.setRoles(new HashSet<>());

            for (String role : roles) {
                user.getRoles().add(roleRepository.findByName(role));
            }

            userRepository.save(user);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        addRoleIfMissing("ROLE_ADMIN", "Administrators");
        addRoleIfMissing("ROLE_STAFF", "Staffs");

        addUserIfMissing("user", "fun123", "ROLE_STAFF");
        addUserIfMissing("customer", "fun123", "ROLE_CUSTOMER");

        addUserIfMissing("admin", "fun123", "ROLE_STAFF", "ROLE_ADMIN");

        if (signingKey == null || signingKey.length() == 0) {
            String jws = Jwts.builder()
                    .setSubject("MoviesWorld")
                    .signWith(SignatureAlgorithm.HS256, "MoviesWorld").compact();

            System.out.println("Use this jwt key:");
            System.out.println("jwt-key=" + jws);
        }
    }
}
