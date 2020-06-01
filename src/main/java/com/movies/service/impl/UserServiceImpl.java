package com.movies.service.impl;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.User;
import com.movies.entity.dto.UserDetailDto;
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
}
