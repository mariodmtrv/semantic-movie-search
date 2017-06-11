/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/11/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.repositories;

import edu.endrotech.semantictech.moviesearch.domain.KnowledgeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface KnowledgeEntityRepository extends PagingAndSortingRepository<KnowledgeEntity, Long> {
    KnowledgeEntity findByName(@Param("name") String name);
}
