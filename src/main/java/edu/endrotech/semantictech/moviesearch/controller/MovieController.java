package edu.endrotech.semantictech.moviesearch.controller;

import edu.endrotech.semantictech.moviesearch.dto.QueryResponseDto;
import edu.endrotech.semantictech.moviesearch.services.MovieService;
import edu.endrotech.semantictech.moviesearch.services.QueryExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MovieController {

    final MovieService movieService;
    @Autowired
    QueryExecutionService queryExecutionService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

//	@RequestMapping("/graph")
//	public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
//		return movieService.graph(limit == null ? 100 : limit);
//	}

    @RequestMapping("/find-movies")
    public QueryResponseDto findMovies(@RequestParam(value = "query", required = true) String query) {
        return queryExecutionService.executeQuery(query);    }
}
