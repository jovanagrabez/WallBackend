package com.example.wallbackend.controller;

import com.example.wallbackend.dto.PostDto;
import com.example.wallbackend.dto.RatingDTO;
import com.example.wallbackend.model.Post;
import com.example.wallbackend.model.Rating;
import com.example.wallbackend.model.User;
import com.example.wallbackend.repository.PostRepository;
import com.example.wallbackend.repository.RatingRepository;
import com.example.wallbackend.repository.UserRepository;
import com.example.wallbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepositroy;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;


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


    @RequestMapping(value="ratePost/{id}", method=RequestMethod.POST,consumes="application/json")
    public ResponseEntity rate(@RequestBody RatingDTO ratingDTO, @PathVariable("id") Long id)throws Exception {

        List<Rating> allRatings = ratingRepository.findAll();
        Post rated = new Post();
        rated = postRepositroy.getOne(id);

        for (Rating o : allRatings) {
            if (o.getUser().getUsername() == ratingDTO.getUsername() && o.getPost().getId() == id) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }


        User u = userRepository.findByUsername(ratingDTO.getUsername());


        Rating add = new Rating();
        add.setUser(u);
        add.setPost(rated);
        add.setRate(ratingDTO.getRate());


        ratingRepository.save(add);

        List<Rating> noveOcene = ratingRepository.findAll();
        int br = 0;
        int rating = 0;
        for (Rating ov : noveOcene) {
            if (ov.getPost().getId() == rated.getId()) {
                br++;
                rating += ov.getRate();
            }
        }


      //  double newRate = ratingDTO.getRate();
        double newRating = (rating) / br;
        rated.setNumberR(br);
        rated.setAverage(newRating);

        try {
            postRepositroy.save(rated);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok().build();



    }

    }
