package com.movies.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class RowSeat {
    private char rowName;
    private List<SeatDTO> seats;

    public RowSeat(char row) {
        rowName = row;
    }
}
