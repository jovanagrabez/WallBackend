package com.example.wallbackend.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String username;

    @Column
    private String password;


}
