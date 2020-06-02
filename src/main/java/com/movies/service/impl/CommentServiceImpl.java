package com.movies.service.impl;

import com.movies.entity.dao.Comment;
import com.movies.repository.CommentRepository;
import com.movies.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> getCommentsByFilm(Integer filmId) {
        return commentRepository.getAllByFilm_IdAndStatusTrue(filmId);
    }
}
