package com.example.wallbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String text;

    @Column
    private Timestamp dateAndTime;

    @ManyToOne
    private User author;

    @Column
    private double rate;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},fetch=FetchType.LAZY, orphanRemoval = true)
    private Set<Comment> comments;


}
