package com.movies.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmTimeDTO {
    private FilmDTO film;

//    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(pattern="HH:mm:ss")
//    private List<Date> time;
    private List<String> time;
}
