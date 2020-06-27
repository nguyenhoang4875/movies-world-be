package com.movies.controlller;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Film;
import com.movies.entity.dao.Rating;
import com.movies.entity.dao.User;
import com.movies.entity.dto.FilmBookingDTO;
import com.movies.entity.dto.FilmDTO;
import com.movies.exception.NotFoundException;
import com.movies.service.FilmService;
import com.movies.service.RatingService;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api/films")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private Converter<Film, FilmDTO> filmFilmDTOConverter;
    @Autowired
    private Converter<Film, FilmBookingDTO> filmFilmBookingDTOConverter;

    @GetMapping
    public List<FilmDTO> getAllFilms() {
        List<Film> films = filmService.getAllFilms();
        return filmFilmDTOConverter.convert(films);
    }

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

    @GetMapping("/booking")
    public List<FilmBookingDTO> getFilms() {
        List<Film> films = filmService.findAllFilmsForCustomer();
        return filmFilmBookingDTOConverter.convert(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilm(@PathVariable Integer id) {
        Optional<Film> film = filmService.getFilmById(id);
        if (!film.isPresent()) {
            throw new NotFoundException("NOT FOUND");
        }
        return new ResponseEntity<>(filmFilmDTOConverter.convert(film.get()), HttpStatus.OK);
    }

    @GetMapping("/searching")
    public List<FilmDTO> getFilms(@RequestParam(required = false) String name) {
        List<Film> films;
        if (name == null) {
            films = filmService.findAllFilmsForCustomer();
        } else {
            films = filmService.findAllFilmsByNameForCustomer(name.trim().toLowerCase());
        }
        return filmFilmDTOConverter.convert(films);
    }

    @PutMapping("/{id}/rate")
    public ResponseEntity<Rating> rateFilm(@RequestBody @Valid Rating rating,
                                           @PathVariable("id") Integer filmId,
                                           Principal principal) {
        User user = userService.findOneByUsername(principal.getName());
        Film film = filmService.getFilmById(filmId).get();
        Rating currentRating = ratingService.findByUserAndFilm(film, user);
        if (currentRating == null) {
            rating.setUser(user);
            rating.setFilm(film);
            ratingService.save(rating);
        } else {
            currentRating.setPoint(rating.getPoint());
            ratingService.save(currentRating);
        }

        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping("/get-rate")
    public ResponseEntity<Rating> getRatePoint(@RequestParam Integer userId,
                                               @RequestParam Integer filmId) {
        User user = userService.findUserById(userId);
        Film film = filmService.getFilmById(filmId).get();
        Rating rating = ratingService.findByUserAndFilm(film, user);
        if (rating == null) {
            throw new NotFoundException("NOT FOUND");
        }
        return new ResponseEntity<>(rating, HttpStatus.OK);

    }

    @PostMapping
    public FilmDTO addFilm(@RequestBody FilmDTO filmDTO) {
        return filmFilmDTOConverter.convert(filmService.addFilm(filmDTO));
    }

    @PutMapping("/{filmId}")
    public FilmDTO updateFilm(@PathVariable Integer filmId, @RequestBody FilmDTO filmDTO) {
        filmDTO.setId(filmId);
        return filmFilmDTOConverter.convert(filmService.updateFilm(filmId, filmDTO));
    }

    @PutMapping("update-status/{filmId}")
    public boolean updateStatusFilm(@PathVariable Integer filmId) {
        return filmService.updateStatusFilm(filmId);
    }

    @GetMapping("/search")
    public List<FilmDTO> search(@RequestParam String keyword) {
        return filmFilmDTOConverter.convert(filmService.search(keyword));
    }

    @DeleteMapping("/{filmId}")
    public void deleteFilm(@PathVariable Integer filmId) {
        filmService.deleteFilm(filmId);
    }
}
