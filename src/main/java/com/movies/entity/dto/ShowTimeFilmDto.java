package com.movies.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ShowTimeFilmDto {
    private Integer id;
    private Integer filmId;
    private LocalDateTime time;
    private RoomDto room;

}
