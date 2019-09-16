package com.movie.watcher.persistence;


import com.movie.watcher.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User,String> {}

