package com.movies.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DateTimeFilmDTO {
    @JsonFormat(pattern = "E, dd-MM-yyyy")
    private LocalDate date;

    @JsonFormat(pattern = "hh:mm a")
    private List<LocalDateTime> timeList;

    public DateTimeFilmDTO(LocalDate date) {
        this.date = date;
    }
}
