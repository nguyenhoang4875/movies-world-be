package com.movies.controlller;

import com.movies.converter.bases.Converter;
import com.movies.entity.Film;
import com.movies.entity.dto.FilmDTO;
import com.movies.exception.NotFoundException;
import com.movies.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @Autowired
    private Converter<Film, FilmDTO> filmFilmDTOConverter;


    @GetMapping("/now-showing")
    public List<FilmDTO> getNowShowingFilms() {
        List<Film> films = filmService.getNowShowingFilms();
        return filmFilmDTOConverter.convert(films);
    }

    @GetMapping("/coming-soon")
    public List<FilmDTO> getComingSoonFilms() {
        List<Film> films = filmService.getComingSoonFilms();
        return filmFilmDTOConverter.convert(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilm(@PathVariable Integer id) {
        Optional<Film> film = filmService.getFilmById(id);
        if (!film.isPresent()) {
            throw new NotFoundException("NOT FOUND");
        }
        return new ResponseEntity<>(filmFilmDTOConverter.convert(film.get()), HttpStatus.OK);
    }
}
