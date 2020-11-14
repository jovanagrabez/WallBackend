package com.example.wallbackend.dto;

import lombok.Data;

@Data
public class CommentDto {

    private String username;
    private String text;
    private Long postId;
    private Long id;
}
