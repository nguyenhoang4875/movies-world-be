package com.movies.controlller;

import com.movies.converter.bases.Converter;
import com.movies.entity.Comment;
import com.movies.entity.dto.CommentDTO;
import com.movies.service.CommentService;
import com.movies.service.FilmService;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private Converter<Comment, CommentDTO> commentCommentDTOConverter;

    @GetMapping("/film/{id}")
    public List<CommentDTO> getCommentsByFilm(@PathVariable Integer id) {
        List<Comment> comments = commentService.getCommentsByFilm(id);
        return commentCommentDTOConverter.convert(comments);
    }
    @PostMapping
    public CommentDTO addComment(@RequestBody @Valid Comment comment,
                                 @RequestParam Integer filmId , Principal principal) {
        comment.setStatus(true);
        System.out.println(LocalDateTime.now());
        System.out.println(new Date());
        comment.setTimeCreate(new Date());
        comment.setUser(userService.findOneByUsername(principal.getName()));
        comment.setFilm(filmService.getFilmById(filmId).get());
        commentService.save(comment);
        return commentCommentDTOConverter.convert(comment);
    }
}
