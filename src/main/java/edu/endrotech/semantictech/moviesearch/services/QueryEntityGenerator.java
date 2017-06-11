/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/10/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.services;

import edu.endrotech.semantictech.moviesearch.domain.Actor;
import edu.endrotech.semantictech.moviesearch.domain.Country;
import edu.endrotech.semantictech.moviesearch.domain.Genre;
import edu.endrotech.semantictech.moviesearch.domain.KnowledgeEntity;
import edu.endrotech.semantictech.moviesearch.entities.ResolvedQuery;
import edu.endrotech.semantictech.moviesearch.repositories.ActorRepository;
import edu.endrotech.semantictech.moviesearch.repositories.CountryRepository;
import edu.endrotech.semantictech.moviesearch.repositories.GenreRepository;
import edu.endrotech.semantictech.moviesearch.repositories.KnowledgeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryEntityGenerator {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private KnowledgeEntityRepository knowledgeEntityRepository;

    public ResolvedQuery extractQueryEntities(List<String> tokens) {
        ResolvedQuery resolvedQuery = new ResolvedQuery();
        for (String token : tokens) {
            findNamedEntity(token, resolvedQuery);
        }
        return resolvedQuery;
    }

    private Country findCountry(String token) {
        return countryRepository.findByName(token);

    }

    private Genre findGenre(String token) {
        if (token.contains(" ")) {
            return null;
        }
        return genreRepository.findByName(token);
    }

    private Actor findActor(String token) {
        if (!token.contains(" ")) {
            return null;
        }
        return actorRepository.findByName(token);
    }

    private KnowledgeEntity findKnowledgeEntity(String token) {
        if (token.contains(" ")) {
            return null;
        }
        return knowledgeEntityRepository.findByName(token);
    }


    private void findNamedEntity(String token, ResolvedQuery resolvedQuery) {
        Country country;
        Actor actor;
        Genre genre;
        KnowledgeEntity knowledgeEntity;
        if ((country = findCountry(token)) != null) {
            resolvedQuery.addCountry(country);
        } else if ((actor = findActor(token)) != null) {
            resolvedQuery.setActor(actor);
        } else if ((genre = findGenre(token)) != null) {
            resolvedQuery.addGenre(genre);
        } else if ((knowledgeEntity = findKnowledgeEntity(token)) != null) {
            if (knowledgeEntity.getGenres().size() > 0) {
                knowledgeEntity.getGenres().stream().forEach(genreEntity -> resolvedQuery.addGenre(genreEntity));
            }
            if (knowledgeEntity.getCountries().size() > 0) {
                knowledgeEntity.getCountries().stream().forEach(countryEntity -> resolvedQuery.addCountry(countryEntity));
            }
        } else {
            resolvedQuery.setPeriod(token);
        }
    }
}
