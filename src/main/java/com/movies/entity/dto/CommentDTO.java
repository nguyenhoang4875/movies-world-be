package com.movies.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private int id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timeCreate;

    private Boolean status;

    @NotBlank(message = "Content is mandatory")
    private String content;

    private String fullNameUser;

    private String avatarUser;

    @NotNull(message = "Film Id is mandatory")
    private Integer filmId;

    private String filmName;
}
