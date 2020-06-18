package com.movies.service;

import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dto.FilmTimeDTO;
import com.movies.entity.dto.ShowTimeFilmDto;

import java.util.Date;
import java.util.List;

public interface ShowTimeFilmService {

    List<ShowTimeFilm> getShowTimeFilmByFilmId(Integer filmId);

    ShowTimeFilmDto addShowTimeFilm(Integer filmId, ShowTimeFilmDto showTimeFilmDto);

    List<Date> getDateShow(Integer filmId);

    List<Date> getTimeShow(Integer filmId, Date date);

    List<ShowTimeFilm> getAll();

    List<FilmTimeDTO> getShowTimeInDay(Date d);
}
