package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Comment;
import com.movies.entity.dto.CommentDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentDTOConverter extends Converter<Comment, CommentDTO> {
    @Override
    public CommentDTO convert(Comment source) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(source.getId());
        commentDTO.setTimeCreate(source.getTimeCreate());
        commentDTO.setStatus(source.getStatus());
        commentDTO.setContent(source.getContent());
        commentDTO.setFilmName(source.getFilm().getName());
        commentDTO.setUserName(source.getUser().getUsername());

        return commentDTO;
    }
}
