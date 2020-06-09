package com.movies.service;

import com.movies.entity.dao.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(Integer id);

    List<Comment> getCommentsByFilm(Integer filmId);

    void save(Comment comment);
}
