package com.movie.watcher.controller;

import com.movie.watcher.model.Movie;
import com.movie.watcher.persistence.MovieDAO;
import com.movie.watcher.service.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("MovieController")
public class MovieController {
    @Autowired
    private final MovieDAO movieDAO;
    private final OmdbService omdbService;


    @Autowired
    public MovieController(MovieDAO movieDAO, OmdbService omdbService) {
        this.movieDAO = movieDAO;
        this.omdbService = omdbService;
    }

    public List<Movie> getAllMovies() {
        List<Movie> allMovies = new ArrayList<>();
        movieDAO.findAll().forEach(allMovies::add);
        return allMovies;
    }

    public void addMovie(Movie movie) {
        Movie persistedMovie = movieDAO.findByImdbID(movie.getImdbID());
        if (persistedMovie == null) {
            movieDAO.save(movie);
        }
    }

    public void addMovies(List<Movie> movieList) {
    }

    public List<Movie> searchMoviesByTitle(String id) {
        List<Movie> movieList = omdbService.getMovies(id);
        return getMovies(movieList);
    }

    public int getMovieCount(String s) {
        return omdbService.getMovieCount(s);
    }

    public List<Movie> getNextPage(String id, String page) {
        List<Movie> movieList = omdbService.getNextPage(id, page);
        return getMovies(movieList);
    }

    private List<Movie> getMovies(List<Movie> movieList) {
        List<Movie> listToReturn = new ArrayList<Movie>();
        for (Movie mov : movieList) {
            Movie persistedMovie = movieDAO.findByImdbID(mov.getImdbID());
            if (persistedMovie == null) {
                movieDAO.save(mov);
                persistedMovie = movieDAO.findByImdbID(mov.getImdbID());
                listToReturn.add(persistedMovie);
            } else {
                listToReturn.add(persistedMovie);
            }
        }
        return listToReturn;
    }

    public void updateMovie(String id, Movie movie) {
        movieDAO.save(movie);
    }

    public void deleteMovie(Movie movie) {
        movieDAO.delete(movie);
    }

    public Optional<Movie> getMovieById(Long movieID) {
        return movieDAO.findById(movieID);
    }

    public Movie favoriteMovie(String id) {
        Movie movieToFavorite = movieDAO.findById(Long.parseLong(id)).orElseThrow();
        if (movieToFavorite.getTitle() == null) {
            return movieToFavorite;
        } else
            movieToFavorite.setFavorite(true);
        movieDAO.save(movieToFavorite);
        return movieToFavorite;
    }

    public List<Movie> getAllFavoriteMovies(){
        return movieDAO.findAllByFavoriteIsTrue();
    }

    public Movie unfavoriteMovie(String id) {
        Movie movieToFavorite = movieDAO.findById(Long.parseLong(id)).orElseThrow();
        if (movieToFavorite.getTitle() == null) {
            return movieToFavorite;
        } else
            movieToFavorite.setFavorite(false);
        movieDAO.save(movieToFavorite);
        return movieToFavorite;
    }

    public Movie getExtendedInfo(String id){
        Movie mov = movieDAO.findById(Long.parseLong(id)).orElseThrow();
        Movie extendedMov = omdbService.getExtendedInfo(mov.getImdbID());
        if (mov.getGenre() == null) {
            extendedMov.setId(mov.getId());
            movieDAO.save(extendedMov);
            return extendedMov;
        } else
            extendedMov = movieDAO.findById(Long.parseLong(id)).orElseThrow();
            return extendedMov;
    }
}

