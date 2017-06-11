/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/11/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.services;

import edu.endrotech.semantictech.moviesearch.domain.Country;
import edu.endrotech.semantictech.moviesearch.domain.Genre;
import edu.endrotech.semantictech.moviesearch.domain.Movie;
import edu.endrotech.semantictech.moviesearch.entities.ResolvedQuery;
import edu.endrotech.semantictech.moviesearch.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class QueryCompositionService {
    @Autowired
    MovieRepository movieRepository;

    public Collection<Movie> findMoviesForQuery(ResolvedQuery resolvedQuery) {
        List<Function> queryExecutors = Arrays.asList(this.tryParseActorGenreQuery, this.tryParseGenreCountryQuery, this.tryParseActorQuery);

        for (Function queryExecutor : queryExecutors) {
            Optional<Collection<Movie>> result = (Optional<Collection<Movie>>) queryExecutor.apply(resolvedQuery);
            if (result.isPresent()) {
                return result.get();
            }
        }
        return null;
    }

    private Function<ResolvedQuery, Optional<Collection<Movie>>> tryParseActorGenreQuery = (ResolvedQuery resolvedQuery) -> {
        if (resolvedQuery.getActor() != null && resolvedQuery.getGenres().size() > 0) {
            Collection<Movie> allMovies = new ArrayList<>();
            List<Genre> genres = resolvedQuery.getGenres();
            for (Genre genre : genres) {
                Collection<Movie> movies = movieRepository.findByActorAndGenre(resolvedQuery.getActor().getName(), genre.getName(), resolvedQuery.getStartYear(), resolvedQuery.getEndYear());
                allMovies.addAll(movies);
            }
            return Optional.of(allMovies);
        }
        return Optional.empty();
    };

    private Function<ResolvedQuery, Optional<Collection<Movie>>> tryParseGenreCountryQuery = (ResolvedQuery resolvedQuery) -> {
        if (resolvedQuery.getGenres().size() > 0 && resolvedQuery.getCountries().size() > 0) {
            Collection<Movie> allMovies = new ArrayList<>();
            for (Genre genre : resolvedQuery.getGenres()) {
                for (Country country : resolvedQuery.getCountries()) {
                    Collection<Movie> movies = movieRepository.findByGenreAndCountry(genre.getName(), country.getName(), resolvedQuery.getStartYear(), resolvedQuery.getEndYear());
                    allMovies.addAll(movies);
                }
            }
            return Optional.of(allMovies);
        }
        return Optional.empty();
    };

    private Function<ResolvedQuery, Optional<Collection<Movie>>> tryParseActorQuery = (ResolvedQuery resolvedQuery) -> {
        if (resolvedQuery.getActor() != null) {
            Collection<Movie> movies = movieRepository.findByActor(resolvedQuery.getActor().getName());
            return Optional.of(movies);
        }
        return Optional.empty();
    };
}
