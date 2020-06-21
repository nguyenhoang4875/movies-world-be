package com.movies.controlller;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Genre;
import com.movies.entity.dto.GenreDTO;
import com.movies.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private Converter<Genre, GenreDTO> genreDaoToGenreDTOConverter;

    @GetMapping
    public List<GenreDTO> getAllGenres() {
        return genreDaoToGenreDTOConverter.convert(genreService.getAllGenre());
    }

}
