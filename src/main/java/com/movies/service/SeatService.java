package com.movies.service;

import com.movies.entity.dao.Seat;
import com.movies.entity.dao.ShowTimeFilm;

import java.util.List;

public interface SeatService {
    List<Seat> getAllByShowTimeFilm(ShowTimeFilm showTimeFilm);
}
