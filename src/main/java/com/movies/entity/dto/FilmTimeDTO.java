package com.movies.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmTimeDTO {
    private FilmDTO film;

    @JsonFormat(pattern="HH:mm:ss")
    private List<LocalDateTime> time;

}
