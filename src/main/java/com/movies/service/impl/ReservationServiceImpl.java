package com.movies.service.impl;

import com.movies.entity.dao.Reservation;
import com.movies.entity.dao.User;
import com.movies.repository.ReservationRepository;
import com.movies.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> findReservationByUser(User user) {
        return reservationRepository.findAllByUserOrderByTimeDesc(user);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> getOneById(int id) {
        return reservationRepository.findById(id);
    }
}
