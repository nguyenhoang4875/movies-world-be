package com.movies.repository;

import com.movies.entity.dao.Film;
import com.movies.entity.dao.ShowTimeFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ShowTimeFilmRepository extends JpaRepository<ShowTimeFilm, Integer> {
    @Query("Select s from ShowTimeFilm s where s.time = :d and s.film.id = :filmId")
    ShowTimeFilm findOneByFilmAndTime(@Param("filmId") Integer filmId, @Param("d") LocalDateTime dateTime);

    List<ShowTimeFilm> findAllByFilmId(Integer filmId);

    @Query("Select distinct cast(s.time as LocalDateTime) as date  from ShowTimeFilm s where s.film.id = :filmId and s.time >= NOW() order by date ")
    List<LocalDateTime> findDate(@Param("filmId") Integer filmId);

    @Query("Select distinct s.film from ShowTimeFilm s where DATE(s.time) = DATE(:d) and s.time >= NOW()")
    List<Film> findFilmByDate(@Param("d") LocalDate date);

    @Query("Select distinct s.time from ShowTimeFilm s where s.film.id = :filmId and DATE(s.time) = DATE(:d) and s.time >= NOW() order by s.time")
    List<LocalDateTime> findTimeFromNow(@Param("filmId") Integer filmId, @Param("d") LocalDate date);
}
