package com.movies.service;

import com.movies.entity.dao.User;
import com.movies.entity.dto.UserDetailDto;

import java.util.List;

public interface UserService {

    List<UserDetailDto> getAllUsers();

    User save(User user);

    User findOneByUsername(String username);

    boolean checkIfValidOldPassword(User user, String oldPassword);

    void changeUserPassword(User user, String password);

    UserDetailDto update(UserDetailDto userDetailDto);

    void delete(Integer userId);
}
