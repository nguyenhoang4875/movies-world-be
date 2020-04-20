package com.movies.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Data
@Entity
@Table(name = "films")
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private String trailer;

    private boolean status;

    @OneToOne
    @JoinColumn(name = "desc_id")
    private FilmDescription filmDescription;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User postedUser;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
    private List<Comment> comments;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
    private List<Rating> ratings;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "film_genre",
            joinColumns = {
                    @JoinColumn(name = "film_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id")
            },
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"film_id", "genre_id"})
            }
    )
    private List<Genre> genres;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
    private List<ShowTimeFilm> showTimeFilms;

}
