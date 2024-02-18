package com.Library.Msystem.controller;

import com.Library.Msystem.model.User;
import com.Library.Msystem.repository.UserRepository;
import com.Library.Msystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String viewHomepage(){
        return "browse_books";
    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "Signup_form";
    }

    @PostMapping("/Process_register")
    public String saveUser(User user){
        userService.saveUser(user);
        return "browse_books";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers(){

        return userService.findAllUsers();
    }

}