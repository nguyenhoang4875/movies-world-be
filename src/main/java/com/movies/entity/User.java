package com.movies.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, unique = true, nullable = false)
    @Size(min = 1, max = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(length = 100, name = "full_name", nullable = false)
    @Size(min = 1, max = 50)
    private String fullName;

    @Email
    private String email;

    @Column(length = 11,nullable = false)
    private String phone;

    @Column(length = 100)
    private String address;

    private boolean enable;

    @Column(length = 100)
    private String avatar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments; //list comment that user sent

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postedUser")
    private List<Film> postedFilms;//list film that user posted

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Rating> ratings;//list rating that user rated

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Reservation> reservations;

}
