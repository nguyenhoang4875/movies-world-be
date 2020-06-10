package com.movies.service.impl;

import com.movies.entity.dao.Film;

import com.movies.repository.FilmRepository;
import com.movies.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmServiveImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<Film> getNowShowingFilms() {
        List<Film> films = filmRepository.findAllShowingNow();
        return films;
    }

    @Override
    public List<Film> getComingSoonFilms() {
        return filmRepository.findComingSoonFilms();
    }

    @Override
    public Optional<Film> getFilmById(Integer id) {
        return filmRepository.findById(id);
    }

    @Override
    public List<Film> findAllFilmsForCustomer() {
        return filmRepository.findAllFilmsForCustomer();
    }

    @Override
    public List<Film> findAllFilmsByNameForCustomer(String q) {
        return filmRepository.findAllFilmsByNameForCustomer(q);
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }
}
