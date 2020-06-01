package com.movies.service;

import com.movies.entity.dao.User;

public interface UserService {

    void save(User user);

    User findOneByUsername(String username);

    boolean checkIfValidOldPassword(User user, String oldPassword);

    void changeUserPassword(User user, String password);
}
