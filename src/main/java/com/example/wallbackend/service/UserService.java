package com.example.wallbackend.service;

import com.example.wallbackend.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User registerUser(User userDto);
}
