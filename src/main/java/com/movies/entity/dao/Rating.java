package com.movies.entity.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ratings")
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "film_id")
    @JsonIgnore
    private Film film;

    @Min(value = 1, message = "Invalid point")
    @Max(value = 5, message = "Invalid point")
    @NotNull(message = "Point is mandatory")
    private int point;
}
