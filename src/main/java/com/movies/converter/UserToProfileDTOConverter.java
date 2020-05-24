package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.dto.ProfileDTO;
import com.movies.entity.User;
import com.movies.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class UserToProfileDTOConverter extends Converter<User, ProfileDTO> {
    @Override
    public ProfileDTO convert(User source) throws BadRequestException {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(source.getId());
        profileDTO.setUsername(source.getUsername());
        profileDTO.setFullName(source.getFullName());
        profileDTO.setAddress(source.getAddress());
        profileDTO.setAvatar(source.getAvatar());
        profileDTO.setEmail(source.getEmail());
        profileDTO.setPhone(source.getPhone());
        profileDTO.setRoles(source.getRoles());
        profileDTO.setEnable(source.isEnable());
        return profileDTO;
    }
}
