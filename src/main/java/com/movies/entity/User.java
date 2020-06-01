package com.movies.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "\"user\"")
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
    @NonNull
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Comment> comments; //list comment that user sent

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postedUser")
    @JsonIgnore
    private List<Film> postedFilms;//list film that user posted

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Rating> ratings;//list rating that user rated

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Reservation> reservations;

}
