package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.Comment;
import com.movies.entity.dto.CommentDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentDTOConverter extends Converter<Comment, CommentDTO> {
    @Override
    public CommentDTO convert(Comment source) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(source.getId());
        commentDTO.setContent(source.getContent());
        commentDTO.setStatus(source.getStatus());
        commentDTO.setTimeCreate(source.getTimeCreate());
        commentDTO.setFullNameUser(source.getUser().getFullName());
        commentDTO.setAvatarUser(source.getUser().getAvatar());
        commentDTO.setFilmId(source.getFilm().getId());
        commentDTO.setFilmName(source.getFilm().getName());
        return commentDTO;
    }
}
