package com.movies.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GenreDTO {
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;
}
