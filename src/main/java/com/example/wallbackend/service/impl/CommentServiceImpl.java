package com.example.wallbackend.service.impl;

import com.example.wallbackend.dto.CommentDto;
import com.example.wallbackend.helper.DateTimeHelper;
import com.example.wallbackend.model.Comment;
import com.example.wallbackend.model.Post;
import com.example.wallbackend.repository.CommentRepository;
import com.example.wallbackend.repository.PostRepository;
import com.example.wallbackend.repository.UserRepository;
import com.example.wallbackend.service.CommentService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {


    private DateTimeHelper dateTimeHelper = new DateTimeHelper();

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Set<Comment> findByPost(Long id) {
        Post post = this.postRepository.getOne(id);

        return post.getComments();
    }



    @Override
    public void addComment(CommentDto commentDto){
        Comment comment = new Comment();

        String formattedDate = dateTimeHelper.dateFormater();
        comment.setAuthor(this.userRepository.findByUsername(commentDto.getUsername()));
        comment.setText(commentDto.getText());
        comment.setDateAndTime(Timestamp.valueOf(formattedDate));

        this.commentRepository.save(comment);

        Post p = this.postRepository.getOne(commentDto.getPostId());
        p.getComments().add(comment);
        this.postRepository.save(p);



    }

    @Override
    public void delete(CommentDto comment) {

        System.out.println("lalalalala" + comment.getText());
        System.out.println("bbbbbbbbbbbb" + comment.getPostId());
        System.out.println("ccccccccc" + comment.getId());



        Post p = this.postRepository.getOne(comment.getPostId());
        Comment comm = this.commentRepository.getOne(comment.getId());
        for(Comment c : p.getComments()){
            if(c.getId().equals(comm.getId())){
                System.out.println("lalalalala" + c.getText());
                p.getComments().remove(c);
                this.postRepository.save(p);
                this.commentRepository.delete(comm);
            }
        }

    }
    @Override
    public void edit(Comment comment) {

        String formattedDate = dateTimeHelper.dateFormater();

        Comment c = this.commentRepository.getOne(comment.getId());
        c.setText(comment.getText());
        c.setDateAndTime(Timestamp.valueOf(formattedDate));
        this.commentRepository.save(c);



    }
}
