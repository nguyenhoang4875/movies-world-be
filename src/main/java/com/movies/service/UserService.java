package com.movies.service;

import com.movies.entity.dto.UserDetailDto;

import java.util.List;
import com.movies.entity.dao.User;

public interface UserService {

    List<UserDetailDto> getAllUsers();

    void save(User user);

    User findOneByUsername(String username);

    boolean checkIfValidOldPassword(User user, String oldPassword);

    void changeUserPassword(User user, String password);
}
