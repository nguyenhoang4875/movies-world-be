package com.movies.entity.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "show_time_film")
public class ShowTimeFilm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="E yyyy-MM-dd HH:mm:ss")
    private Date time;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "showTimeFilm")
    private List<Seat> seats;
}
