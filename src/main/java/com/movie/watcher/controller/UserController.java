package com.movie.watcher.controller;

import com.movie.watcher.model.User;
import com.movie.watcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/hello/user")
    public String hello(){
        return "Hello!";
    }

    @RequestMapping("/movies/user")
    public List<User> getAllMovies(){

        return userService.getAllUsers();
    }
}
