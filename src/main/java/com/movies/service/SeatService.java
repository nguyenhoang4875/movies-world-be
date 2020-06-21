package com.movies.service;

import com.movies.entity.dao.Seat;
import com.movies.entity.dao.ShowTimeFilm;

import java.util.List;
import java.util.Optional;

public interface SeatService {
    List<Seat> getAllByShowTimeFilm(ShowTimeFilm showTimeFilm);

    Seat getOneByNameAndShowTimeFilm(String nameSeat, ShowTimeFilm showTimeFilm);

    Seat save(Seat seat);

    Optional<Seat> getById(int id);
}
