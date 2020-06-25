package com.movies.service.impl;

import com.movies.entity.dao.Seat;
import com.movies.entity.dao.ShowTimeFilm;
import com.movies.repository.SeatRepository;
import com.movies.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> getAllByShowTimeFilm(ShowTimeFilm showTimeFilm) {
        return seatRepository.findAllByShowTimeFilmOrderByName(showTimeFilm);
    }

    @Override
    public Seat getOneByNameAndShowTimeFilm(String nameSeat, ShowTimeFilm showTimeFilm) {
        return seatRepository.findSeatByNameAndShowTimeFilm(nameSeat, showTimeFilm);
    }

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public Optional<Seat> getById(int id) {
        return seatRepository.findById(id);
    }
}
