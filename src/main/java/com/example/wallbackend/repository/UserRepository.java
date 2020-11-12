package com.example.wallbackend.repository;

import com.example.wallbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}
