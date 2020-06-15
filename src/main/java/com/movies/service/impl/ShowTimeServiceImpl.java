package com.movies.service.impl;

import com.movies.entity.dao.ShowTimeFilm;
import com.movies.repository.ShowTimeFilmRepository;
import com.movies.service.ShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ShowTimeServiceImpl implements ShowTimeService {
    @Autowired
    private ShowTimeFilmRepository showTimeFilmRepository;

    @Override
    public List<Date> getDateShow(Integer filmId) {
        return showTimeFilmRepository.findDate(filmId);
    }

    @Override
    public List<Date> getTimeShow(Integer filmId, Date date) {
        return showTimeFilmRepository.findTime(filmId, date);
    }

    @Override
    public List<ShowTimeFilm> getAll() {
        return showTimeFilmRepository.findAllByTime();
    }
}
