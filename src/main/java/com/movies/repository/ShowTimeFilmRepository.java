package com.movies.repository;

import com.movies.entity.ShowTimeFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowTimeFilmRepository extends JpaRepository<ShowTimeFilm, Integer> {
    @Query("Select s from ShowTimeFilm s where DATE(s.time) = DATE(NOW())")
    List<ShowTimeFilm> findAllByTime();
}
