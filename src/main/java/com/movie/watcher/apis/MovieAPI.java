package com.movie.watcher.apis;

import com.movie.watcher.controller.MovieController;
import com.movie.watcher.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieAPI {

    private final MovieController movieController;
    @Autowired
    public MovieAPI(MovieController movieController) {
        this.movieController = movieController;
    }

    @RequestMapping("/movies")
    public List<Movie> searchMoviesByTitle(@RequestParam(name = "s") String s, @RequestParam( value = "page", defaultValue = "1") String page) {
        if(page.equals("1")){
            return  movieController.searchMoviesByTitle(s);
        }else
         return movieController.getNextPage(s, page);
    }

    @PostMapping("/movies/")
    public List<Movie> addMovie(@RequestBody Movie movie) {
        movieController.addMovie(movie);
        return movieController.getAllMovies();
    }

    @RequestMapping("/movie/{id}")
    @ResponseBody
    public Movie getMovieById(@PathVariable String id) {
        return movieController.getExtendedInfo(id);
    }
    @PutMapping(path = "/movie/{id}")
    public Movie favoriteMovie(@PathVariable String id){
        return movieController.favoriteMovie(id);
    }
    @PutMapping(path = "/movie/{id}/unfavorite")
    public Movie unfavoriteMovie(@PathVariable String id){
        return movieController.unfavoriteMovie(id);
    }

    @RequestMapping("/movies/count")
    public Integer getResponseCount(@RequestParam(name = "s") String s) {
        return movieController.getMovieCount(s);
    }

    @RequestMapping("/favorites")
    public List<Movie> getAllFavoriteMovies(){
        return movieController.getAllFavoriteMovies();
    }
}
