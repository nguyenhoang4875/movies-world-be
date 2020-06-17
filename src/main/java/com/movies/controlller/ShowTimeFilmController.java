package com.movies.controlller;

import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dto.ShowTimeFilmDto;
import com.movies.service.ShowTimeFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtimefilms")
public class ShowTimeFilmController {

    @Autowired
    private ShowTimeFilmService showTimeFilmService;

    @GetMapping("/{filmId}")
    public List<ShowTimeFilm> getShowTimeFilmByFilmId(@PathVariable Integer filmId) {
        return showTimeFilmService.getShowTimeFilmByFilmId(filmId);
    }

    @PostMapping("/{filmId}")
    public ShowTimeFilmDto addShowTimeFilmBy(@PathVariable Integer filmId, @RequestBody ShowTimeFilmDto showTimeFilmDto) {
        return showTimeFilmService.addShowTimeFilm(filmId, showTimeFilmDto);
    }
}
