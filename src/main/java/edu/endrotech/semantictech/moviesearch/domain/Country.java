/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/7/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.domain;

import org.neo4j.ogm.annotation.GraphId;

public class Country {

    @GraphId
    private Long id;

    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
