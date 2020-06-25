package com.movies.repository;

import com.movies.entity.dao.Seat;
import com.movies.entity.dao.ShowTimeFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Integer> {
    List<Seat> findAllByShowTimeFilmOrderByName(ShowTimeFilm showTimeFilm);

    Seat findSeatByNameAndShowTimeFilm(String name, ShowTimeFilm showTimeFilm);
}
