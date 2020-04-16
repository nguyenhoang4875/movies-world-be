package com.movies.models;

import javax.persistence.*;
import java.io.Serializable;

import lombok.*;

@Data
@Entity
@Table(name = "film")
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "trailer")
    public String trailer;

    @Column(name = "id_desc")
    public int idDesc;
}
