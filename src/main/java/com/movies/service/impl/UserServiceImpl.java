package com.movies.service.impl;

import com.movies.entity.User;
import com.movies.repository.UserRepository;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
