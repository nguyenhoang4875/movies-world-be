package com.movies.repository;

import com.movies.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film,Integer> {
    @Query("Select distinct f from Film f join f.showTimeFilms s where DATE(s.time) = DATE(NOW())")
    List<Film> findAllShowingNow();

    @Query("Select distinct f from Film f join f.filmDescription d where DATE(d.premiere) > DATE(NOW())")
    List<Film> findComingSoonFilms();
}
