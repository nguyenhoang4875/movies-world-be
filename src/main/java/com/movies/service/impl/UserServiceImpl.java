package com.movies.service.impl;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.PasswordResetToken;
import com.movies.entity.dao.User;
import com.movies.entity.dto.UserDetailDto;
import com.movies.repository.PasswordResetTokenRepository;
import com.movies.repository.UserRepository;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Converter<User, UserDetailDto> userDaoToUserDetailDtoConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public List<UserDetailDto> getAllUsers(String role) {
        return userDaoToUserDetailDtoConverter.convert(userRepository.findByRolesName(role));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
        if (passToken == null) {
            return "INVALID TOKEN";
        } else {
            Calendar calendar = Calendar.getInstance();
            if (passToken.getExpiryDate().before(calendar.getTime())) {
                return "EXPIRED TOKEN";
            }
        }
        return null;
    }

    @Override
    public User getUserByPasswordResetToken(String token) {
        return passwordResetTokenRepository.findByToken(token).getUser();
    }

    @Override
    public UserDetailDto update(UserDetailDto userDetailDto) {
        User user = userRepository.getOne(userDetailDto.getId());
        user.setUsername(userDetailDto.getUsername());
        user.setFullName(userDetailDto.getFullName());
        user.setEmail(userDetailDto.getEmail());
        user.setAddress(userDetailDto.getAddress());
        user.setPhone(userDetailDto.getPhone());
        userRepository.save(user);
        return userDetailDto;
    }

    @Override
    public void delete(Integer userId) {
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    @Transactional
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public UserDetailDto getUserById(Integer userId) {
        return userDaoToUserDetailDtoConverter.convert(userRepository.getOne(userId));
    }

    @Override
    public List<UserDetailDto> search(String keyword, String roleName) {

        keyword = keyword.toLowerCase().trim();
        List<User> userList = userRepository.findByRolesName(roleName);
        List<User> results = new ArrayList<>();

        for (User user : userList) {
            if (user.getUsername().toLowerCase().contains(keyword) ||
                    user.getFullName().toLowerCase().contains(keyword) ||
                    user.getEmail().toLowerCase().contains(keyword) ||
                    user.getAddress().toLowerCase().contains(keyword) ||
                    user.getPhone().toLowerCase().contains(keyword)
            ) {
                results.add(user);
            }
        }
        return userDaoToUserDetailDtoConverter.convert(results);
    }

}
