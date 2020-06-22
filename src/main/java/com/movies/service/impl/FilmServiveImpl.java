package com.movies.service.impl;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Film;
import com.movies.entity.dto.FilmDTO;
import com.movies.repository.FilmRepository;
import com.movies.service.FilmService;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiveImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private Converter<FilmDTO, Film> filmDtoToFilmDaoConverter;

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

    @Override
    public Film addFilm(FilmDTO filmDTO) {
        Film film = filmDtoToFilmDaoConverter.convert(filmDTO);
        film.setPostedUser(userService.getCurrentUser());
        filmRepository.save(film);
        return film;
    }

    @Override
    public Film updateFilm(Integer filmId, FilmDTO filmDTO) {
        Film filmUpdate = filmRepository.getOne(filmId);
        Film film = filmDtoToFilmDaoConverter.convert(filmDTO);
        filmUpdate.setName(film.getName());
        filmUpdate.setTrailer(film.getTrailer());
        filmUpdate.setGenres(film.getGenres());
        filmUpdate.setFilmDescription(film.getFilmDescription());
        filmUpdate.setPoster(film.getPoster());
        filmRepository.save(filmUpdate);
        return filmUpdate;
    }
}
