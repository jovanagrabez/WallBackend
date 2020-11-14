package com.example.wallbackend.service;


import com.example.wallbackend.dto.CommentDto;
import com.example.wallbackend.model.Comment;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CommentService {

    Set<Comment> findByPost(Long id);
    void addComment(CommentDto commentDto);
    void delete(CommentDto comment);
    void edit(Comment comment);
}
