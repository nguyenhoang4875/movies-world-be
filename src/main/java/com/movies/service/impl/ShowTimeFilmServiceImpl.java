package com.movies.service.impl;

import com.movies.entity.dao.Film;
import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dto.ShowTimeFilmDto;
import com.movies.repository.FilmRepository;
import com.movies.repository.RoomRepository;
import com.movies.repository.ShowTimeFilmRepository;
import com.movies.service.ShowTimeFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowTimeFilmServiceImpl implements ShowTimeFilmService {

    @Autowired
    private ShowTimeFilmRepository showTimeFilmRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<ShowTimeFilm> getShowTimeFilmByFilmId(Integer filmId) {
        return showTimeFilmRepository.findAllByFilmId(filmId);
    }

    @Override
    public ShowTimeFilmDto addShowTimeFilm(Integer filmId, ShowTimeFilmDto showTimeFilmDto) {
        ShowTimeFilm showTimeFilm = new ShowTimeFilm();
        showTimeFilm.setTime(showTimeFilmDto.getTime());
        showTimeFilm.setFilm(filmRepository.getOne(filmId));
        showTimeFilm.setRoom(roomRepository.getOne(showTimeFilmDto.getRoom().getId()));
        showTimeFilm = showTimeFilmRepository.save(showTimeFilm);
        Film film = filmRepository.getOne(filmId);
        film.getShowTimeFilms().add(showTimeFilm);
        showTimeFilmDto.setFilmId(filmId);
        showTimeFilmDto.setId(showTimeFilm.getId());
        return showTimeFilmDto;
    }


}
