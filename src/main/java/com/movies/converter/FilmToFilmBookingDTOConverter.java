package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Film;
import com.movies.entity.dto.FilmBookingDTO;
import org.springframework.stereotype.Component;

@Component
public class FilmToFilmBookingDTOConverter extends Converter<Film, FilmBookingDTO> {
    @Override
    public FilmBookingDTO convert(Film source) {
        FilmBookingDTO filmBookingDTO = new FilmBookingDTO();
        filmBookingDTO.setId(source.getId());
        filmBookingDTO.setName(source.getName());
        filmBookingDTO.setPoster(source.getPoster());
        return filmBookingDTO;
    }
}
