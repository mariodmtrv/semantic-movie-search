/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/11/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.services;

import edu.endrotech.semantictech.moviesearch.domain.Movie;
import edu.endrotech.semantictech.moviesearch.dto.QueryResponseDto;
import edu.endrotech.semantictech.moviesearch.entities.ResolvedQuery;
import edu.endrotech.semantictech.moviesearch.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryExecutionService {
    @Autowired
    QueryPreprocessingService queryPreprocessingService;
    @Autowired
    QueryCompositionService queryCompositionService;

    @Autowired
    QueryEntityGenerator queryEntityGenerator;

    @Autowired
    MovieRepository movieRepository;
    public QueryResponseDto executeQuery(String query) {
        List<String> tokens = queryPreprocessingService.processQuery(query);
        ResolvedQuery resolvedQuery = queryEntityGenerator.extractQueryEntities(tokens);
        List<Movie> resultMovies = (List<Movie>) queryCompositionService.findMoviesForQuery(resolvedQuery);
        List<Movie> reconstructedMovies = resultMovies.stream().map(movie -> movieRepository.findByTitle(movie.getTitle())).collect(Collectors.toList());
        QueryResponseDto response = new QueryResponseDto(resolvedQuery, reconstructedMovies);
        return response;
    }
}
