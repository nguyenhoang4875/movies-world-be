package com.movies.repository;

import com.movies.entity.dao.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Integer> {
}
