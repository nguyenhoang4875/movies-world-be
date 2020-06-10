package com.movies.service.impl;

import com.movies.entity.dao.ShowTimeFilm;
import com.movies.repository.ShowTimeFilmRepository;
import com.movies.service.ShowTimeFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowTimeFilmServiceImpl implements ShowTimeFilmService {

    @Autowired
    private ShowTimeFilmRepository showTimeFilmRepository;

    @Override
    public List<ShowTimeFilm> getShowTimeFileByFilmId(Integer filmId) {
        return showTimeFilmRepository.findAllByFilmId(filmId);
    }
}
