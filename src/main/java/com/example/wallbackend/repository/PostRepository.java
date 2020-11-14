package com.example.wallbackend.repository;

import com.example.wallbackend.model.Comment;
import com.example.wallbackend.model.Post;
import com.example.wallbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByAuthor(User user);
}
