package com.movies.service.impl;

import com.movies.entity.dao.Film;
import com.movies.entity.dao.Rating;
import com.movies.entity.dao.User;
import com.movies.repository.RatingRepository;
import com.movies.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating findByUserAndFilm(Film film, User user) {
        return ratingRepository.findByFilmAndUser(film, user);
    }
}
