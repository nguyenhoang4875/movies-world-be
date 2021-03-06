package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Film;
import com.movies.entity.dao.Genre;
import com.movies.entity.dao.Rating;
import com.movies.entity.dto.FilmDTO;
import com.movies.entity.dto.GenreDTO;
import com.movies.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmToFilmDTOConverter extends Converter<Film, FilmDTO> {

    @Autowired
    private Converter<Genre, GenreDTO> genreDaoToGenreDtoConverter;

    @Override
    public FilmDTO convert(Film source) throws BadRequestException {

        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setId(source.getId());
        filmDTO.setName(source.getName());
        filmDTO.setTrailer(source.getTrailer());
        filmDTO.setStatus(source.isStatus());
        filmDTO.setPoster(source.getPoster());
        filmDTO.setFilmDescription(source.getFilmDescription());

        if (source.getPostedUser() != null) {
            filmDTO.setPostedUserId(source.getPostedUser().getId());
            filmDTO.setPostedUserName(source.getPostedUser().getFullName());
        }

        if (source.getGenres() != null) {
            filmDTO.setGenres(genreDaoToGenreDtoConverter.convert(source.getGenres()));
        }

        float point = 0;
        if (source.getRatings() != null) {
            for (Rating rating : source.getRatings()) {
                point += rating.getPoint();
            }
            point = point / source.getRatings().size();
        }
        filmDTO.setRatePoint((float) Math.round(point * 10) / 10);
        return filmDTO;
    }
}
