/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/11/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NodeEntity
public class KnowledgeEntity {
    @GraphId
    private Long id;

    private String name;

    @Relationship(type = "IS_REFERRED_BY", direction = Relationship.INCOMING)
    private List<Country> countries = new ArrayList<>();

    @Relationship(type = "IS_REFERRED_BY", direction = Relationship.INCOMING)
    private List<Genre> genres = new ArrayList<>();

    public KnowledgeEntity() {
    }

    public KnowledgeEntity(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;

    }

    public void addCountry(Country country) {
        this.countries.add(country);
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }
}
