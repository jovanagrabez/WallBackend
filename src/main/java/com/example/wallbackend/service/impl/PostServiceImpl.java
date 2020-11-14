package com.example.wallbackend.service.impl;

import com.example.wallbackend.dto.PostDto;
import com.example.wallbackend.model.Post;
import com.example.wallbackend.model.User;
import com.example.wallbackend.repository.PostRepository;
import com.example.wallbackend.repository.UserRepository;
import com.example.wallbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepostiroy;
    @Override
    public void add(PostDto post) {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        Post p = new Post();
        p.setAuthor(this.userRepository.findByUsername(post.getUsername()));
        p.setText(post.getText());
        p.setRate(0);
        p.setDateAndTime(Timestamp.valueOf(formattedDate));

        this.postRepostiroy.save(p);


    }

    @Override
    public void edit(Post post) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        Post p = this.postRepostiroy.getOne(post.getId());
        p.setText(post.getText());
        p.setDateAndTime(Timestamp.valueOf(formattedDate));
        this.postRepostiroy.save(p);



    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = this.postRepostiroy.findAll();
        posts.sort(Comparator.comparing(o -> o.getDateAndTime()));
        Collections.reverse(posts);

        return posts;
    }

    @Override
    public List<Post> getAllByUser(String username) {
        User user = this.userRepository.findByUsername(username);
        List<Post> posts = this.postRepostiroy.findByAuthor(user);
        posts.sort(Comparator.comparing(o -> o.getDateAndTime()));
        Collections.reverse(posts);
        return posts;
    }
}
