package com.movies.repository;

import com.movies.entity.dao.FilmDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmDescriptionRepository extends JpaRepository<FilmDescription, Integer> {
}
