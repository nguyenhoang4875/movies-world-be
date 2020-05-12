package com.movies.service.impl;

import com.movies.entity.Film;

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
}
