package com.movies.service;

import com.movies.entity.dao.Film;

import java.util.List;
import java.util.Optional;


public interface FilmService {

    List<Film> getNowShowingFilms();

    List<Film> getComingSoonFilms();

    Optional<Film> getFilmById(Integer id);
}
