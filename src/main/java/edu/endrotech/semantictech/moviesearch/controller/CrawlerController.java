/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/4/2017
 * Author     :  Mario Dimitrov
 */

package edu.endrotech.semantictech.moviesearch.controller;

import com.omertron.themoviedbapi.model.movie.MovieInfo;
import edu.endrotech.semantictech.moviesearch.services.CrawlerService;
import edu.endrotech.semantictech.moviesearch.services.KnowledgeEntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/crawler")
public class CrawlerController {
    private final CrawlerService crawlerService;
    private final KnowledgeEntitiesService knowledgeEntitiesService;

    @Autowired
    private CrawlerController(CrawlerService crawlerService, KnowledgeEntitiesService knowledgeEntitiesService) {
        this.crawlerService = crawlerService;
        this.knowledgeEntitiesService = knowledgeEntitiesService;
    }


    @RequestMapping("/crawl-movies")
    @ResponseBody
    public List<MovieInfo> collectMovies() {
        return crawlerService.crawl();
    }

    @RequestMapping("/create-knowledge-entities")
    @ResponseBody
    public void createKnowledgeEntities() {
        knowledgeEntitiesService.createKnowledgeEntities();
    }

}
