package com.movies.entity.dto;

import com.movies.entity.dao.FilmDescription;
import com.movies.entity.dao.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

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
