package com.movies.entity.dto;

import com.movies.entity.FilmDescription;
import com.movies.entity.Genre;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class FilmDTO {
    int id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Trailer is mandatory")
    private String trailer;

    @NotBlank(message = "Poster is mandatory")
    private String poster;

    private boolean status;

    private float ratePoint;

    private FilmDescription filmDescription;

    private Integer postedUserId;

    private String postedUserName;

    private List<Genre> genres;
}
