package com.movie.watcher.service;

import com.movie.watcher.model.User;
import com.movie.watcher.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public List<User> getAllUsers(){
        List<User> allUsers = new ArrayList<>();
        userDAO.findAll().forEach(allUsers::add);
        return allUsers;
    }
    public void addMovie(User user){
        userDAO.save(user);
    }

    public User getUser(String id){
        return userDAO.findById(id).get();
    }

    public void updateUser(String id, User user){
        userDAO.save(user);
    }

    public void deleteUser(User user){
        userDAO.delete(user);
    }

}
