package com.example.wallbackend.controller;


import com.example.wallbackend.model.User;
import com.example.wallbackend.repository.UserRepository;
import com.example.wallbackend.security.auth.JWTProvider;
import com.example.wallbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("api/users")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JWTProvider jwtProvider;


    @PostMapping("/login")
    public ResponseEntity<Object> signIn(@RequestBody User user) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                bCryptPasswordEncoder.encode(user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        HashMap<String, String> token = new HashMap<>();
        token.put("accessToken", jwt);
        token.put("username",user.getUsername());


        return ResponseEntity.ok(token);

    }

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestBody User userDto) {

        if (this.userRepository.findByUsername(userDto.getUsername()) == null) {
            User user = this.userService.registerUser(userDto);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

}
