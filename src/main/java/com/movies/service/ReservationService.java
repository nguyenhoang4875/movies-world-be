package com.movies.service;

import com.movies.entity.dao.Reservation;
import com.movies.entity.dao.User;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> findReservationByUser(User user);
    Reservation save(Reservation reservation);

    Optional<Reservation> getOneById(int id);
}
