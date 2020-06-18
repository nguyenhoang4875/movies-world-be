package com.movies.service;

import com.movies.entity.dao.Reservation;
import com.movies.entity.dao.User;

import java.util.List;

public interface ReservationService {
    List<Reservation> findReservationByUser(User user);
}
