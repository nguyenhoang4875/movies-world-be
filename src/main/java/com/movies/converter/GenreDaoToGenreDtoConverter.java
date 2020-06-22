package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Genre;
import com.movies.entity.dto.GenreDTO;
import org.springframework.stereotype.Component;

@Component
public class GenreDaoToGenreDtoConverter extends Converter<Genre, GenreDTO> {
    @Override
    public GenreDTO convert(Genre source) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(source.getId());
        genreDTO.setName(source.getName());
        return genreDTO;
    }
}
