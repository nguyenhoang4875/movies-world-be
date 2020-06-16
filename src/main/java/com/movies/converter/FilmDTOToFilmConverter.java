package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Film;
import com.movies.entity.dto.FilmDTO;
import com.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmDTOToFilmConverter extends Converter<FilmDTO, Film> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Film convert(FilmDTO source) {

        Film film = new Film();

        film.setId(source.getId());
        film.setName(source.getName());
        film.setTrailer(source.getTrailer());
        film.setPoster(source.getPoster());
        film.setStatus(source.isStatus());
        film.setFilmDescription(source.getFilmDescription());

        return film;
    }
}
