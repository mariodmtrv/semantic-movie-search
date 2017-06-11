/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/10/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class QueryPreprocessingService {
    @Autowired
    NationalityDiscoveryService nationalityDiscoveryService;

    @Autowired
    GenreDiscoveryService genreDiscoveryService;

    public List<String> processQuery(String rawQuery) {
        List<String> tokenized = tokenizeEntity(rawQuery);
        List<String> result = new ArrayList<>();
        result.add(enrichEntity(tokenized.get(0)));

        for (int i = 1; i < tokenized.size(); i++) {
            result.add(enrichEntity(tokenized.get(i)));
            result.add(tokenized.get(i-1) + " " + tokenized.get(i));
        }
        return result;
    }

    private List<String> tokenizeEntity(String entity) {
        StringTokenizer st = new StringTokenizer(entity, " \t\n\r\f,.:;?![]'");
        List<String> tokens = new ArrayList<>();
        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
        return tokens;
    }

    private String findCountry(String token) {
        return nationalityDiscoveryService.getCountry(token);
    }

    private String findGenre(String token) {
        return genreDiscoveryService.getGenre(token);
    }

    private String enrichEntity(String token) {
        String enrichedEntity = findCountry(token);
        if (enrichedEntity == null) {
            enrichedEntity = findGenre(token);
        }
        if (enrichedEntity == null) {
            enrichedEntity = token;
        }
        return enrichedEntity;
    }

}
