package com.movies.entity.dao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "film_descriptions")
public class FilmDescription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "time_limit", nullable = false, length = 100)
    private String timeLimit;

    private LocalDate premiere;

    @Column(nullable = false, length = 100)
    private String artist;

    @Column(nullable = false, length = 100)
    private String director;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String nation;

}
