package com.movies.entity.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, unique = true, nullable = false)
    @Size(min = 1, max = 50)
    @NonNull
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column(nullable = false)
    @NonNull
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(length = 100, name = "full_name", nullable = false)
    @Size(min = 1, max = 50)
    @NonNull
    @NotBlank(message = "Full Name is mandatory")
    private String fullName;

    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(length = 11, nullable = true)
    private String phone;

    @Column(length = 100)
    private String address;

    private boolean enable;

    @Column(length = 100)
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
