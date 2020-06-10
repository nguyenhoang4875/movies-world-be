package com.movies.controlller;

import com.movies.entity.dao.ShowTimeFilm;
import com.movies.service.ShowTimeFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/showtimefilms")
public class ShowTimeFilmController {

    @Autowired
    private ShowTimeFilmService showTimeFilmService;

    @GetMapping("/{filmId}")
    public List<ShowTimeFilm> getShowTimeFileByFilmId(@PathVariable Integer filmId){
        return showTimeFilmService.getShowTimeFileByFilmId(filmId);
    }
}
