/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/7/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.repositories;

import edu.endrotech.semantictech.moviesearch.domain.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {
    Country findByName(@Param("name") String name);
}
