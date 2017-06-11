/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/11/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.services;

import edu.endrotech.semantictech.moviesearch.domain.Country;
import edu.endrotech.semantictech.moviesearch.domain.Genre;
import edu.endrotech.semantictech.moviesearch.domain.KnowledgeEntity;
import edu.endrotech.semantictech.moviesearch.repositories.ActorRepository;
import edu.endrotech.semantictech.moviesearch.repositories.CountryRepository;
import edu.endrotech.semantictech.moviesearch.repositories.GenreRepository;
import edu.endrotech.semantictech.moviesearch.repositories.KnowledgeEntityRepository;
import edu.endrotech.semantictech.moviesearch.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class KnowledgeEntitiesService {
    @Autowired
    private KnowledgeEntityRepository knowledgeEntityRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private CountryRepository countryRepository;


    public void createKnowledgeEntities() {
        KnowledgeEntity european = countryRelation("european", Arrays.asList("France", "Italy", "Germany"));
        KnowledgeEntity asian = countryRelation("asian", Arrays.asList("India", "Japan", "South Korea"));
        KnowledgeEntity familyFriendly = genreRelation("family-friendly", Arrays.asList("Comedy", "Animation", "Family"));
        KnowledgeEntity coupleFriendly = genreRelation("couple-friendly", Arrays.asList("Romance", "Comedy"));
        KnowledgeEntity blockbusters = genreRelation("blockbusters", Arrays.asList("Action", "Thriller", "Drama"));
        List<KnowledgeEntity> entities = Arrays.asList(european, asian, familyFriendly, coupleFriendly, blockbusters);
        entities.stream().forEach(entity -> knowledgeEntityRepository.save(entity));
    }

    private KnowledgeEntity countryRelation(String name, List<String> countryNames) {
        KnowledgeEntity entity = new KnowledgeEntity(name);
        countryNames.stream().forEach(countryName -> {
            Country country = countryRepository.findByName(countryName);
            entity.addCountry(country);
        });
        return entity;
    }

    private KnowledgeEntity genreRelation(String name, List<String> genreNames) {
        KnowledgeEntity entity = new KnowledgeEntity(name);
        genreNames.stream().forEach(genreName -> {
            Genre genre = genreRepository.findByName(genreName);
            entity.addGenre(genre);
        });
        return entity;
    }
}
