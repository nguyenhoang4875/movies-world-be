package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.User;
import com.movies.entity.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDaoToUserDtoConverter extends Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
