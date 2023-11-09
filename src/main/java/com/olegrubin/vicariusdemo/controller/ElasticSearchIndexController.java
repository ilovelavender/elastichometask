package com.olegrubin.vicariusdemo.controller;

import com.olegrubin.vicariusdemo.model.common.SuccessResponse;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateIndexRequest;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateIndexResponse;
import com.olegrubin.vicariusdemo.service.api.ElasticSearchService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/index")
public class ElasticSearchIndexController {

    private final ElasticSearchService elasticSearchService;

    public ElasticSearchIndexController(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @PutMapping
    public SuccessResponse<CreateIndexResponse> createIndex(@RequestBody final CreateIndexRequest request) {
        CreateIndexResponse response = elasticSearchService.createIndex(request);
        return new SuccessResponse<>(response);
    }
}
