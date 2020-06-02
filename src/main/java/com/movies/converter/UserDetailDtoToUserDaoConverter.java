package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.User;
import com.movies.entity.dto.UserDetailDto;
import org.springframework.stereotype.Component;

@Component
public class UserDetailDtoToUserDaoConverter extends Converter<UserDetailDto, User> {

    @Override
    public User convert(UserDetailDto source) {
        User user= new User();
        user.setId(source.getId());
        user.setUsername(source.getUsername());
        user.setFullName(source.getFullName());
        user.setEmail(source.getEmail());
        user.setAddress(source.getAddress());
        user.setPhone(source.getPhone());
        return user;
    }

}
