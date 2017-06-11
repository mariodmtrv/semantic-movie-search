/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/11/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.dto;

import edu.endrotech.semantictech.moviesearch.domain.Country;
import edu.endrotech.semantictech.moviesearch.domain.Genre;
import edu.endrotech.semantictech.moviesearch.domain.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieDto {
    private String title;
    private List<String> actors;
    private Integer released;
    private List<String> genres;
    private List<String> countries;

    public MovieDto(Movie movie) {
        this.title = movie.getTitle();
        this.released = movie.getReleased();
        this.actors = movie.getActors().stream().map(actor -> actor.getName()).collect(Collectors.toList());
        this.genres = movie.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toList());
        this.countries = movie.getCountries().stream().map(country -> country.getName()).collect(Collectors.toList());
    }

    public MovieDto() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public Integer getReleased() {
        return released;
    }

    public void setReleased(Integer released) {
        this.released = released;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }
}
