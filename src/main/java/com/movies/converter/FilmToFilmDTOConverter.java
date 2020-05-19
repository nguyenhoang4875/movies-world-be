package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.dto.FilmDTO;
import com.movies.entity.Film;
import com.movies.exception.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class FilmToFilmDTOConverter extends Converter<Film, FilmDTO> {
    @Override
    public FilmDTO convert(Film source) throws BadRequestException {

        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setId(source.getId());
        filmDTO.setName(source.getName());
        filmDTO.setTrailer(source.getTrailer());
        filmDTO.setStatus(source.isStatus());
        filmDTO.setPoster(source.getPoster());
        filmDTO.setFilmDescription(source.getFilmDescription());

        if(source.getPostedUser() != null){
            filmDTO.setPostedUserId(source.getPostedUser().getId());
            filmDTO.setPostedUserName(source.getPostedUser().getFullName());
        }

        if(source.getGenres() != null) {
            filmDTO.setGenres(source.getGenres());
        }

        return filmDTO;
    }
}
