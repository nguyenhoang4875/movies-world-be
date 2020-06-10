package com.movies.service;

import com.movies.entity.dao.ShowTimeFilm;

import java.util.List;

public interface ShowTimeFilmService {
    List<ShowTimeFilm> getShowTimeFileByFilmId(Integer filmId);
}
