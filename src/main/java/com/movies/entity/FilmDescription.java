package com.movies.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "film_descriptions")
public class FilmDescription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "time_limit",nullable = false,length = 100)
    private String timeLimit;

    private Date premiere;

    @Column(nullable = false,length = 100)
    private String artist;

    @Column(nullable = false,length = 100)
    private String director;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String nation;

    @OneToOne(mappedBy = "filmDescription")
    @JsonIgnore
    private Film film;

}
