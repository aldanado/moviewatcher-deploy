package com.movie.watcher.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String imdbID;
    @NotBlank
    private String Title;
    private String Year;
    @NotBlank
    private String Type;
    private String Poster;
    private boolean favorite;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Actors;
    private String Plot;
    private String Awards;

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public Movie() {
    }

    public Movie(Long id, @NotBlank String imdbID, @NotBlank String title, String year, @NotBlank String type, String poster, boolean favorite, String rated, String released, String runtime, String genre, String director, String actors, String plot, String awards) {
        this.imdbID = imdbID;
        this.id = id;
        this.Title = title;
        this.Year = year;
        this.Type = type;
        this.Poster = poster;
        this.favorite = favorite;
        this.Rated = rated;
        this.Released = released;
        this.Runtime = runtime;
        this.Genre = genre;
        this.Director = director;
        this.Actors = actors;
        this.Plot = plot;
        this.Awards = awards;
    }

    public Movie(Long id, String imdbID, String Title, String Year, String type, String posterLink, Boolean favorite) {
        super();
        this.id = id;
        this.imdbID = imdbID;
        this.Title = Title;
        this.Year = Year;
        this.Type = type;
        this.Poster = posterLink;
        this.favorite = favorite;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setImdbID(String id) {
        this.imdbID = imdbID;
    }

    public String getImdbID() {
        return this.imdbID;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        this.Poster = poster;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}

