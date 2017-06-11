/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/11/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.dto;

import edu.endrotech.semantictech.moviesearch.domain.Movie;
import edu.endrotech.semantictech.moviesearch.entities.ResolvedQuery;

import java.util.List;
import java.util.stream.Collectors;

public class QueryResponseDto {
    private ResolvedQueryDto query;
    private List<MovieDto> movies;


    public QueryResponseDto() {
    }

    public QueryResponseDto(ResolvedQuery inputQuery, List<Movie> inputMovies) {
        this.movies = inputMovies.stream().map(movie -> new MovieDto(movie)).collect(Collectors.toList());

        this.query = new ResolvedQueryDto(inputQuery);
    }

    public List<MovieDto> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDto> movies) {
        this.movies = movies;
    }

    public ResolvedQueryDto getQuery() {
        return query;
    }

    public void setQuery(ResolvedQueryDto query) {
        this.query = query;
    }
}
