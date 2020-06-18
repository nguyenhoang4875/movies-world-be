package com.movies.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ShowTimeFilmDto {
    private Integer id;
    private Integer filmId;
    private Date time;
    private RoomDto room;

}
