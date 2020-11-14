package com.example.wallbackend.controller;

import com.example.wallbackend.dto.CommentDto;
import com.example.wallbackend.model.Comment;
import com.example.wallbackend.model.Post;
import com.example.wallbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;



    @GetMapping("/{id}")
    private ResponseEntity fingByPost(@PathVariable Long id){
        Set<Comment> comments = this.commentService.findByPost(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


    @PostMapping("/add")
    private ResponseEntity addComment(@RequestBody CommentDto commentDto){
        if (commentDto.getUsername() != null) {
            this.commentService.addComment(commentDto);

            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }


    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteComment(@RequestBody CommentDto comment) {

        this.commentService.delete(comment);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/edit")
    public ResponseEntity editPost(@RequestBody Comment comment) {
       this.commentService.edit(comment);
        return ResponseEntity.ok().build();
    }


}
