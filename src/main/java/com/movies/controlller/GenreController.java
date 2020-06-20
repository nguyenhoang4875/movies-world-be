package com.movies.controlller;

import com.movies.entity.dao.Genre;
import com.movies.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<String> getAllGenres(){
      List<String> genres = new ArrayList<>();
        for (Genre genre: genreService.getAllGenre() ) {
            genres.add(genre.getName());
        }
        return genres;

    }

}
