package com.movies.repository;

import com.movies.entity.dao.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    @Query("Select distinct f from Film f join f.showTimeFilms s where DATE(s.time) = DATE(NOW()) and f.status = true")
    List<Film> findAllShowingNow();

    @Query("Select distinct f from Film f join f.filmDescription d where DATE(d.premiere) >= DATE(NOW()) and f.status = true")
    List<Film> findComingSoonFilms();

    @Query("Select distinct f from Film f join f.filmDescription d left join f.showTimeFilms s " +
            "where (DATE(d.premiere) >= DATE(NOW()) or DATE(s.time) = DATE(NOW())) and (f.status = true) " )

    List<Film> findAllFilmsForCustomer();

    @Query("Select distinct f from Film f join f.filmDescription d left join f.showTimeFilms s " +
            "where (DATE(d.premiere) >= DATE(NOW()) or DATE(s.time) = DATE(NOW())) and f.status = true " +
            "and f.name LIKE %:name%")
    List<Film> findAllFilmsByNameForCustomer(@Param("name") String name);

}
