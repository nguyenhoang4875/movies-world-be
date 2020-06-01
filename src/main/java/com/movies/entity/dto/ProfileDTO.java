package com.movies.entity.dto;

import com.movies.entity.dao.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class ProfileDTO {
    int id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Full Name is mandatory")
    private String fullName;

    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;

    private String phone;

    private String address;

    private boolean enable;

    private String avatar;

    private Set<Role> roles;
}
