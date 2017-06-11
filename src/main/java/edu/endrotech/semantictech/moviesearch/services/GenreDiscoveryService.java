/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/10/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GenreDiscoveryService {
    private static final Map<String, String> genresMapping;

    static {
        genresMapping = new HashMap<>();
        genresMapping.put("comedies", "Comedy");
        genresMapping.put("dramas", "Drama");
        genresMapping.put("thrillers", "Thriller");
        genresMapping.put("romantic", "Romance");
    }

    //TODO improve to support all genre naming variations
    static String getGenre(String genre) {
        return genresMapping.get(genre.toLowerCase());
    }
}
