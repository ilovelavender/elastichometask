package com.olegrubin.vicariusdemo.controller;

import com.olegrubin.vicariusdemo.model.common.SuccessResponse;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateDocumentRequest;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateDocumentResponse;
import com.olegrubin.vicariusdemo.model.elasticsearch.GetDocumentResponse;
import com.olegrubin.vicariusdemo.service.api.ElasticSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/document")
public class ElasticSearchDocumentController {

    private final ElasticSearchService elasticSearchService;

    public ElasticSearchDocumentController(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    @PutMapping
    public SuccessResponse<CreateDocumentResponse> createDocument(@RequestBody final CreateDocumentRequest request) {
        CreateDocumentResponse response = elasticSearchService.createDocument(request);
        return new SuccessResponse<>(response);
    }

    @GetMapping("/{indexName}/{documentId}")
    public SuccessResponse<GetDocumentResponse> getDocument(
            @PathVariable(required = true) String indexName,
            @PathVariable(required = true) String documentId) {
        GetDocumentResponse response = elasticSearchService.getDocument(indexName, documentId);
        return new SuccessResponse<>(response);
    }
}
