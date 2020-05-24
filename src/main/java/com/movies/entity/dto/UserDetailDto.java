package com.movies.entity.dto;

import lombok.Data;

@Data
public class UserDetailDto {
    private Integer id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String address;
}
