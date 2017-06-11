/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/10/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.entities;

import edu.endrotech.semantictech.moviesearch.domain.Actor;
import edu.endrotech.semantictech.moviesearch.domain.Country;
import edu.endrotech.semantictech.moviesearch.domain.Genre;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ResolvedQuery {
    private Actor actor;
    private List<Country> countries;
    private Integer startYear;
    private Integer endYear;
    private List<Genre> genres;

    public ResolvedQuery() {
        this.countries = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.startYear = 1900;
        this.endYear = 2017;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }

    public void setPeriod(String period) {
        switch (period) {
            case "old": {
                startYear = 1900;
                endYear = 1950;
                return;
            }
            case "recent": {
                startYear = 2016;
                endYear = 2017;
                return;
            }
        }
    }

    public void addCountry(Country country) {
        this.countries.add(country);
    }

}
