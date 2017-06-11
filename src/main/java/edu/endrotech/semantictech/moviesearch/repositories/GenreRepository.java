/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/4/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.repositories;

import edu.endrotech.semantictech.moviesearch.domain.Genre;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {
    Genre findByName(@Param("name") String name);
}
