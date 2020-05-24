package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.User;
import com.movies.entity.dto.UserDetailDto;
import org.springframework.stereotype.Component;

@Component
public class UserDaoToUserDetailDtoConverter extends Converter<User, UserDetailDto> {

    @Override
    public UserDetailDto convert(User source) {
        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setId(source.getId());
        userDetailDto.setUsername(source.getUsername());
        userDetailDto.setFullName(source.getFullName());
        userDetailDto.setEmail(source.getEmail());
        userDetailDto.setAddress(source.getAddress());
        userDetailDto.setPhone(source.getPhone());
        return userDetailDto;
    }
}
