package com.movies.service;

import com.movies.entity.Comment;

import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(Integer id);
}
