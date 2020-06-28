package com.movies.service;

import com.movies.entity.dao.User;
import com.movies.entity.dto.UserDetailDto;

import java.util.List;

public interface UserService {

    List<UserDetailDto> getAllUsers(String role);

    User save(User user);

    User findOneByUsername(String username);

    boolean checkIfValidOldPassword(User user, String oldPassword);

    void changeUserPassword(User user, String password);

    User findUserByEmail(String userEmail);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    User getUserByPasswordResetToken(String token);

    UserDetailDto update(UserDetailDto userDetailDto);

    void delete(Integer userId);

    User findUserById(Integer userId);

    User getCurrentUser();

    UserDetailDto getUserById(Integer userId);

    List<UserDetailDto> search(String keyword, String roleName);
}
