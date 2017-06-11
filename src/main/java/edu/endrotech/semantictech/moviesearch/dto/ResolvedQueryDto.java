/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/11/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.dto;

import edu.endrotech.semantictech.moviesearch.entities.ResolvedQuery;

import java.util.List;
import java.util.stream.Collectors;

public class ResolvedQueryDto {
    private String actor;
    private List<String> countries;
    private String releasedRange;
    private List<String> genres;

    public ResolvedQueryDto(ResolvedQuery resolvedQuery) {
        if (resolvedQuery.getActor() != null) {
            this.actor = resolvedQuery.getActor().getName();
        }
        if (resolvedQuery.getGenres() != null) {
            this.genres = resolvedQuery.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toList());
        }
        if (resolvedQuery.getCountries() != null) {
            this.countries = resolvedQuery.getCountries().stream().map(country -> country.getName()).collect(Collectors.toList());
        }

        this.releasedRange = String.format("%d-%d", resolvedQuery.getStartYear(), resolvedQuery.getEndYear());
    }

    public ResolvedQueryDto() {
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public String getReleasedRange() {
        return releasedRange;
    }

    public void setReleasedRange(String releasedRange) {
        this.releasedRange = releasedRange;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
