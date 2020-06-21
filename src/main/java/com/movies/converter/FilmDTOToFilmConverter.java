package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Film;
import com.movies.entity.dao.FilmDescription;
import com.movies.entity.dao.Genre;
import com.movies.entity.dto.FilmDTO;
import com.movies.entity.dto.GenreDTO;
import com.movies.repository.FilmDescriptionRepository;
import com.movies.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmDTOToFilmConverter extends Converter<FilmDTO, Film> {

    @Autowired
    private FilmDescriptionRepository filmDescriptionRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Film convert(FilmDTO source) {
        Film film = new Film();
        film.setName(source.getName());
        film.setTrailer(source.getTrailer());
        film.setPoster(source.getPoster());
        film.setStatus(source.isStatus());
        if (source.getGenres() != null) {
            List<Genre> genres = new ArrayList<>();
            for (GenreDTO genre : source.getGenres()) {
                genres.add(genreRepository.findByName(genre.getName()));
            }
            film.setGenres(genres);
        }
        FilmDescription filmDescription = filmDescriptionRepository.save(source.getFilmDescription());
        film.setFilmDescription(filmDescription);
        return film;
    }
}
