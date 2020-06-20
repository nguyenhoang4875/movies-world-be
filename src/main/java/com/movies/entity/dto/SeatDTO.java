package com.movies.entity.dto;

import lombok.Data;

@Data
public class SeatDTO {
    private int id;

    private String name;

    private int status;

    private int showTimeFilmId;

    private int reservationId;
}
