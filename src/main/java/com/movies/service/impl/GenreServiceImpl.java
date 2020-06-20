package com.movies.service.impl;

import com.movies.entity.dao.Genre;
import com.movies.repository.GenreRepository;
import com.movies.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }
}
