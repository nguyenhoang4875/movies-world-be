package com.movies.service;

import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dto.FilmTimeDTO;
import com.movies.entity.dto.ShowTimeFilmDto;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ShowTimeFilmService {

    List<ShowTimeFilm> getShowTimeFilmByFilmId(Integer filmId);

    ShowTimeFilmDto addShowTimeFilm(Integer filmId, ShowTimeFilmDto showTimeFilmDto);

    List<LocalDateTime> getDateShow(Integer filmId);

    List<LocalDateTime> getTimeShow(Integer filmId, LocalDate date);

    List<FilmTimeDTO> getShowTimeInDay(LocalDate d);

    ShowTimeFilm getOneByFilmAndTime(int filmId, LocalDateTime dateTime);

    void addShowTimeFilmList(Integer filmId, List<ShowTimeFilmDto> showTimeFilmList);

    ShowTimeFilmDto updateShowTimeFilm(Integer showTimeFilmId, ShowTimeFilmDto showTimeFilmDto);

    void deleteShowTimeFilm(Integer showTimeFilmId);

    ShowTimeFilm getShowTimeFilmById(Integer showTimeFilmId);
}
