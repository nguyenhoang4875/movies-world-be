package com.movies.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Integer id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date timeCreated;

    private boolean status;

    private Integer userId;

    private String filmName;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date showTime;

    private String room;

    private List<String> seat;
}
