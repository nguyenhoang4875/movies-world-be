package com.movies.repository;

import com.movies.entity.dao.ShowTimeFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ShowTimeFilmRepository extends JpaRepository<ShowTimeFilm, Integer> {
    @Query("Select s from ShowTimeFilm s where DATE(s.time) = DATE(NOW())")
    List<ShowTimeFilm> findAllByTime();

    List<ShowTimeFilm> findAllByFilmId(Integer filmId);

    @Query("Select DATE(s.time) from ShowTimeFilm s where s.film.id = :filmId")
    List<Date> findDate(@Param("filmId") Integer filmId);

    @Query("Select s.time from ShowTimeFilm s where s.film.id = :filmId and DATE(s.time) = DATE(:d)")
    List<Date> findTime(@Param("filmId") Integer filmId, @Param("d") Date date);

}
