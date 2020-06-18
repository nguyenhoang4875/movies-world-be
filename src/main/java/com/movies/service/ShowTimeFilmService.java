package com.movies.service;

import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dto.ShowTimeFilmDto;

import java.util.List;

public interface ShowTimeFilmService {

    List<ShowTimeFilm> getShowTimeFilmByFilmId(Integer filmId);

    ShowTimeFilmDto addShowTimeFilm(Integer filmId, ShowTimeFilmDto showTimeFilmDto);
}
