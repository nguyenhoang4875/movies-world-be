package com.movies.repository;

import com.movies.entity.dao.Film;
import com.movies.entity.dao.Rating;
import com.movies.entity.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Rating findByFilmAndUser(Film film, User user);
}
