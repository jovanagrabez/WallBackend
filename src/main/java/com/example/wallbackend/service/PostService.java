package com.example.wallbackend.service;

import com.example.wallbackend.dto.PostDto;
import com.example.wallbackend.model.Post;
import com.example.wallbackend.repository.PostRepository;
import com.example.wallbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {



    void add(PostDto postDto);
    void edit(Post post);
    List<Post> findAll();
    List<Post> getAllByUser(String username);
}
