package com.movies.controlller;

import com.movies.entity.Film;
import com.movies.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping("/now-showing")
    public ResponseEntity<List<Film>> getNowShowingFilms() {
        List<Film> films = filmService.getNowShowingFilms();
        if (films.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/coming-soon")
    public ResponseEntity<List<Film>> getComingSoonFilms() {
        List<Film> films = filmService.getComingSoonFilms();
        if (films.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }
}
