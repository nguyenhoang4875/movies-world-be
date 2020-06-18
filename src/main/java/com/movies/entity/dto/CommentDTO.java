package com.movies.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date timeCreate;

    private Boolean status;

    @NotBlank(message = "Content is mandatory")
    private String content;

    private String fullNameUser;

    private String avatarUser;

    @NotNull(message = "Film Id is mandatory")
    private Integer filmId;

    private String filmName;
}
