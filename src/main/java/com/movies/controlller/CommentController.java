package com.movies.controlller;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Comment;
import com.movies.entity.dto.CommentDTO;
import com.movies.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private Converter<Comment, CommentDTO> commentCommentDTOConverter;

    @Autowired
    private CommentService commentService;

    @GetMapping("/films/{id}")
    public List<CommentDTO> getCommentsOfFilm(@PathVariable("id") Integer filmId) {
        List<Comment> comments = commentService.getCommentsByFilm(filmId);
        return commentCommentDTOConverter.convert(comments);
    }
}
