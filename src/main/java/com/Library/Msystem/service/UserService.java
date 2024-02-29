package com.Library.Msystem.service;

import com.Library.Msystem.Auth.JwtAuthenticationFilter;
import com.Library.Msystem.Auth.JwtService;
import com.Library.Msystem.model.User;
import com.Library.Msystem.repository.UserRepository;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}