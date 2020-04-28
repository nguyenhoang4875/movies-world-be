package com.movies.service;

import com.movies.entity.User;

public interface UserService {
    void save(User user);
    User findOneByUsername(String username);
}
