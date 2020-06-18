package com.movies.service.impl;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Film;
import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dto.FilmDTO;
import com.movies.entity.dto.FilmTimeDTO;
import com.movies.repository.ShowTimeFilmRepository;
import com.movies.service.ShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShowTimeServiceImpl implements ShowTimeService {
    @Autowired
    private ShowTimeFilmRepository showTimeFilmRepository;

    @Autowired
    private Converter<Film, FilmDTO> filmFilmDTOConverter;

    @Override
    public List<Date> getDateShow(Integer filmId) {
        return showTimeFilmRepository.findDate(filmId);
    }

    @Override
    public List<Date> getTimeShow(Integer filmId, Date date) {
        return showTimeFilmRepository.findTime(filmId, date);
    }

    @Override
    public List<ShowTimeFilm> getAll() {
        return showTimeFilmRepository.findAllByTime();
    }

    @Override
    public List<FilmTimeDTO> getShowTimeInDay(Date d) {
        List<Film> films = showTimeFilmRepository.findFilmByDate(d);
        List<FilmTimeDTO> filmTimeDTOS = new ArrayList<>();
        for (Film film : films) {
            FilmTimeDTO filmTimeDTO = new FilmTimeDTO();
            filmTimeDTO.setFilm(filmFilmDTOConverter.convert(film));
            List<Date> timeList = showTimeFilmRepository.findTimeFromNow(film.getId(), d);
            List<String> stringList = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            for (Date time: timeList) {
                stringList.add(format.format(time));
            }
            filmTimeDTO.setTime(stringList);
            filmTimeDTOS.add(filmTimeDTO);
        }

        return filmTimeDTOS;
    }
}
