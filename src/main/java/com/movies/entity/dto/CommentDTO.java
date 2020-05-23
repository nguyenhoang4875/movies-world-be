package com.movies.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class CommentDTO {
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date timeCreate;

    private Boolean status;

    @NotBlank(message = "Name is mandatory")
    private String content;

    private String userName;

    private String filmName;
}
