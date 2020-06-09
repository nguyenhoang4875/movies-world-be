package com.movies.entity.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_create")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeCreate;

    private Boolean status;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Content is mandatory")
    private String content;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "film_id")
    @JsonIgnore
    private Film film;
}
