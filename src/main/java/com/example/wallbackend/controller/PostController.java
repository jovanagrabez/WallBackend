package com.example.wallbackend.controller;

import com.example.wallbackend.dto.PostDto;
import com.example.wallbackend.model.Post;
import com.example.wallbackend.repository.PostRepository;
import com.example.wallbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepositroy;


    @PostMapping(value = "/add")
    public ResponseEntity registerUser(@RequestBody PostDto post) {

        if (post.getUsername() != null) {
            this.postService.add(post);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @GetMapping()
    public ResponseEntity getAll() {
        List<Post> posts = this.postService.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity getAllByUser(@PathVariable String username) {
        List<Post> posts = this.postService.getAllByUser(username);
        return new ResponseEntity<>(posts, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePost(@PathVariable Long id) {
        Post post = this.postRepositroy.getOne(id);
        this.postRepositroy.delete(post);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/edit")
    public ResponseEntity editPost(@RequestBody Post post) {

           this.postService.edit(post);
            return ResponseEntity.ok().build();
    }


}
