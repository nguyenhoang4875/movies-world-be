package com.movies.service.impl;

import com.movies.entity.dao.Seat;
import com.movies.entity.dao.ShowTimeFilm;
import com.movies.repository.SeatRepository;
import com.movies.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> getAllByShowTimeFilm(ShowTimeFilm showTimeFilm) {
        return seatRepository.findAllByShowTimeFilm(showTimeFilm);
    }
}
