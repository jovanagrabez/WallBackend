package com.example.wallbackend.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    @Column()
    private int rate;
}
