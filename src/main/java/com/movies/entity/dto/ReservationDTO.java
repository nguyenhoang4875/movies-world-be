package com.movies.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private int id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timeCreated;

    private int status;

    private int userId;

    @NotNull
    private int filmId;

    private String filmName;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @NotNull
    private LocalDateTime showTime;

    private String room;

    @NotNull
    private List<String> seat;
}
