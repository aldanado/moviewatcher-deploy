package com.movie.watcher.service;

import com.google.gson.*;
import com.movie.watcher.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class OmdbService {
    private static final Gson gson = new Gson();
    private static final JsonParser jsonParser = new JsonParser();
    private final static String URI = "http://www.omdbapi.com/?apikey=22e6d02f&s=";
    private final static String URIEXTENDED = "http://www.omdbapi.com/?apikey=22e6d02f&i=";
    private final static String filterForMovies = "&type=movie";

    /**
     * This method will return a list of movies that contains the movieName passed as input
     * @param movieName the movie title that will be used to search on Omdb
     * @return
     */
    public List<Movie> getMovies(String movieName) {
        RestTemplate restTemplate = new RestTemplate();
        String uriToSend = URI.concat(movieName) + filterForMovies;
        String receivedMovies = restTemplate.getForObject(uriToSend, String.class);
        return parseResponse(receivedMovies);
    }

    private List<Movie> parseResponse(String response) {
        JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
        String noMovieFound = "";
        try {
           noMovieFound = jsonObject.get("Error").getAsString();
        }catch (NullPointerException npe){
            noMovieFound = "Some movie found";
        }
        if (noMovieFound.equals("Movie not found!") || noMovieFound.equals("Too many results.") ) {
            List<Movie> emptyList = new ArrayList<Movie>();
            return emptyList;
        } else {
            JsonArray movieJsonArray = jsonObject.get("Search").getAsJsonArray(); //Assert if the response is null
            return Arrays.asList(gson.fromJson(movieJsonArray.toString(), Movie[].class));
        }
    }

    public List<Movie> getNextPage(String lastSearch, String page) {
        RestTemplate restTemplate = new RestTemplate();
        String forNextPageURI = URI.concat(lastSearch);
        forNextPageURI = forNextPageURI.concat("&page=") + page + filterForMovies;
        String receivedMovies = restTemplate.getForObject(forNextPageURI, String.class);
        return parseResponse(receivedMovies);
    }

    public Integer getMovieCount(String movieTitle) {
        RestTemplate restTemplate = new RestTemplate();
        String receivedMovies = restTemplate.getForObject(URI.concat(movieTitle), String.class);
        JsonObject jsonObject = jsonParser.parse(receivedMovies).getAsJsonObject();
        Integer totalResults = 0;
        try{
            totalResults = jsonObject.get("totalResults").getAsInt();
        }catch (NullPointerException npe){
            return totalResults;
        }
        return jsonObject.get("totalResults").getAsInt();
    }

    public Movie getExtendedInfo(String imdbId){
        RestTemplate restTemplate = new RestTemplate();
        String uriToSend = URIEXTENDED.concat(imdbId);
        String receivedMovie = restTemplate.getForObject(uriToSend, String.class);
        JsonObject jsonObject = jsonParser.parse(receivedMovie).getAsJsonObject();
        return gson.fromJson(jsonObject .toString(), Movie.class);
    }
}

