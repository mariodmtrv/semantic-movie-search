package edu.endrotech.semantictech.moviesearch.repositories;

import java.util.Collection;

import edu.endrotech.semantictech.moviesearch.domain.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

    Movie findByTitle(@Param("title") String title);

    Collection<Movie> findByTitleContaining(String title);

//	@Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
//	Collection<Movie> graph(@Param("limit") int limit);

    @Query("MATCH (c: Country {name:{country}})-[r2:FROM_COUNTRY]->(m:Movie)-[r:HAS_GENRE]-(g:Genre {name: {genre}}) WHERE  m.released in range({startYear},{endYear}) RETURN m,r,g,c,r2 LIMIT 100")
    Collection<Movie> findByGenreAndCountry(@Param("genre") String genre, @Param("country") String country, @Param("startYear") Integer startYear, @Param("endYear") Integer endYear);

    @Query("MATCH (a: Actor {name: {actor}})-[r2:ACTED_IN]->(m:Movie)-[r:HAS_GENRE]-(g:Genre {name: {genre}}) WHERE  m.released in range({startYear},{endYear}) RETURN m,r,a,g,r2 LIMIT 100")
    Collection<Movie> findByActorAndGenre(@Param("actor") String actor, @Param("genre") String genre, @Param("startYear") Integer startYear, @Param("endYear") Integer endYear);

    @Query("MATCH (a: Actor {name: {actor}})-[r2:ACTED_IN]->(m:Movie) return a,m,r2 LIMIT 100")
    Collection<Movie> findByActor(@Param("actor") String actor);
}

