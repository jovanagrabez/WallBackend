package com.example.wallbackend.dto;

import com.example.wallbackend.model.Post;
import com.example.wallbackend.model.User;
import lombok.Data;

@Data
public class RatingDTO {

    private Long id;
    private String username;
    private Post post;
    private int rate;
}
