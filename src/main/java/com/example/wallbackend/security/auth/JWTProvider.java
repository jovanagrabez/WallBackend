package com.example.wallbackend.security.auth;

import com.auth0.jwt.JWT;
import com.example.wallbackend.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.example.wallbackend.security.auth.SecurityConstants.*;
@Component
public class JWTProvider {


    public String generateJwtToken(Authentication authentication) {

        return JWT.create()
                .withSubject(authentication.getPrincipal().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
    }



}
