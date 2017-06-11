package edu.endrotech.semantictech.moviesearch.repositories;

import edu.endrotech.semantictech.moviesearch.domain.Actor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends PagingAndSortingRepository<Actor, Long> {
    Actor findByName(@Param("name") String name);
}
