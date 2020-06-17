package com.movies.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String token;
    private Date expired;
    private Set roles;
}
