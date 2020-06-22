package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Genre;
import com.movies.entity.dto.GenreDTO;
import com.movies.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoToGenreDaoConverter extends Converter<GenreDTO, Genre> {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre convert(GenreDTO source) {
        return genreRepository.findByName(source.getName());
    }
}
