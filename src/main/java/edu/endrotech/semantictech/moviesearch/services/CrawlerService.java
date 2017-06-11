/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/4/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.services;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.model.Genre;
import com.omertron.themoviedbapi.model.media.MediaCreditList;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.model.movie.ProductionCountry;
import com.omertron.themoviedbapi.results.ResultList;
import edu.endrotech.semantictech.moviesearch.domain.Actor;
import edu.endrotech.semantictech.moviesearch.domain.Country;
import edu.endrotech.semantictech.moviesearch.domain.Movie;
import edu.endrotech.semantictech.moviesearch.repositories.ActorRepository;
import edu.endrotech.semantictech.moviesearch.repositories.CountryRepository;
import edu.endrotech.semantictech.moviesearch.repositories.GenreRepository;
import edu.endrotech.semantictech.moviesearch.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2017 (C) Endrotech
 * Created on :  5/28/2017
 * Author     :  Mario Dimitrov
 */
@Service
public class CrawlerService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CountryRepository countryRepository;
    private TheMovieDbApi tmdb = null;

    public List<MovieInfo> crawl() {
        try {
            tmdb = new TheMovieDbApi("7c7f2117013c6fe3d11b8dd743ee7567");
            for (int page = 1; page <= 5; page++) {
                ResultList<MovieInfo> topRated = tmdb.getTopRatedMovies(page, "en");
                topRated.getResults().stream().forEach(movie -> addMovie(movie));
            }
        } catch (MovieDbException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addMovie(MovieInfo movieInfo) {
        Movie movie = null;
        try {
            int releasedYear = Integer.parseInt(movieInfo.getReleaseDate().subSequence(0, 4).toString());
            movie = new Movie(movieInfo.getTitle(), releasedYear);
            setActors(movieInfo, movie);
            movieInfo = tmdb.getMovieInfo(movieInfo.getId(), "en");
            List<ProductionCountry> productionCountries = movieInfo.getProductionCountries();
            List<Country> countries = productionCountries.stream().map(productionCountry -> {
                String countryName = productionCountry.getName();
                Country foundCountry = countryRepository.findByName(countryName);
                return foundCountry == null ? new Country(countryName) : foundCountry;
            }).collect(Collectors.toList());
            for (Country country : countries) {
                movie.addCountry(country);
            }
            setGenres(movieInfo, movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        movieRepository.save(movie);
    }

    private void setActors(MovieInfo movieInfo, Movie movie) throws MovieDbException {
        MediaCreditList credits = tmdb.getMovieCredits(movieInfo.getId());

        List<Actor> actors = credits.getCast().stream().limit(3).map(castMember -> {
            String actorName = castMember.getName();
            Actor foundActor = actorRepository.findByName(actorName);
            return foundActor == null ? new Actor(actorName) : foundActor;

        }).collect(Collectors.toList());
        for (Actor actor : actors) {
            movie.addActor(actor);
        }
    }

    private void setGenres(MovieInfo movieInfo, Movie movie) {
        List<Genre> genres = movieInfo.getGenres();
        List<edu.endrotech.semantictech.moviesearch.domain.Genre> genreEntities = genres.stream().map(genre -> {
            String genreName = genre.getName();
            edu.endrotech.semantictech.moviesearch.domain.Genre foundGenre = genreRepository.findByName(genreName);
            return foundGenre == null ? new edu.endrotech.semantictech.moviesearch.domain.Genre(genreName) : foundGenre;
        }).collect(Collectors.toList());
        for (edu.endrotech.semantictech.moviesearch.domain.Genre genre : genreEntities) {
            movie.addGenre(genre);
        }
    }
}
