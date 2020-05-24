package com.movies.service.impl;

import com.movies.entity.dao.User;
import com.movies.repository.UserRepository;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Converter<User, UserDetailDto> userDaoToUserDetailDtoConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDetailDto> getAllUsers() {
        return userDaoToUserDetailDtoConverter.convert(userRepository.findAll());
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
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
}
