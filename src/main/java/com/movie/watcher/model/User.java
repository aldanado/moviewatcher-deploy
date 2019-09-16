package com.movie.watcher.model;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn
    private Movie movieId;

    public User(){

    }
    public User(Movie movieId) {
        this.movieId = movieId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getmovieId() {
        return movieId;
    }

    public void setmovieId(Movie movieId) {
        this.movieId = movieId;
    }
}
