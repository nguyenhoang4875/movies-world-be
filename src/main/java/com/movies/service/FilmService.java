package com.movies.service;

import com.movies.entity.dao.Film;
import com.movies.entity.dto.FilmDTO;

import java.util.List;
import java.util.Optional;


public interface FilmService {

    List<Film> getNowShowingFilms();

    List<Film> getComingSoonFilms();

    Optional<Film> getFilmById(Integer id);

    List<Film> findAllFilmsForCustomer();

    List<Film> findAllFilmsByNameForCustomer(String q);

    List<Film> getAllFilms();

    Film addFilm(FilmDTO filmDTO);

    Film updateFilm(Integer filmId, FilmDTO filmDTO);

    boolean updateStatusFilm(Integer filmId);

    List<Film> search(String keyword);
}
