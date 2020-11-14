package com.example.wallbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Comment {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    @Column
    private String text;

    @Column
    private Timestamp dateAndTime;

    @ManyToOne
    private User author;

}
