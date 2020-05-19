package com.movies.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class CommentDTO {
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreate;

    private Boolean status;

    @NotBlank(message = "Name is mandatory")
    private String content;

    private String userName;

    private String filmName;
}
