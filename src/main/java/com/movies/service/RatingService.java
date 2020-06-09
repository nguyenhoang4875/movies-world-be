package com.movies.service;

import com.movies.entity.dao.Film;
import com.movies.entity.dao.Rating;
import com.movies.entity.dao.User;

public interface RatingService {
    Rating save(Rating rating);

    Rating findByUserAndFilm(Film film, User user);
}
