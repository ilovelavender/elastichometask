package com.olegrubin.vicariusdemo.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.olegrubin.vicariusdemo.mapper.ElasticSearchMapper;
import com.olegrubin.vicariusdemo.model.InternalException;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateDocumentRequest;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateDocumentResponse;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateIndexRequest;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateIndexResponse;
import com.olegrubin.vicariusdemo.model.elasticsearch.GetDocumentResponse;
import com.olegrubin.vicariusdemo.service.api.ElasticSearchService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {
    private final ElasticsearchClient elasticsearchClient;
    private final ElasticSearchMapper elasticSearchMapper;

    public ElasticSearchServiceImpl(ElasticsearchClient elasticsearchClient,
                                    ElasticSearchMapper elasticSearchMapper) {
        this.elasticsearchClient = elasticsearchClient;
        this.elasticSearchMapper = elasticSearchMapper;
    }

    @Override
    public CreateIndexResponse createIndex(CreateIndexRequest request) {
        var mappedRequest = elasticSearchMapper.map(request);
        try {
            var response = elasticsearchClient.indices().create(mappedRequest);
            return elasticSearchMapper.map(response);
        } catch (IOException | RuntimeException exe) {
            throw new InternalException(
                "Exception creating index " + request.indexName()
                    + ". Details - " + exe.getMessage(), exe);
        }
    }

    @Override
    public CreateDocumentResponse createDocument(CreateDocumentRequest request) {
        var mappedRequest = elasticSearchMapper.map(request);
        try {
            var response = elasticsearchClient.create(mappedRequest);
            return elasticSearchMapper.map(response);
        } catch (IOException | RuntimeException exe) {
            throw new InternalException(
                "Exception creating document on index " + request.indexName()
                    + (request.documentId() != null ? " with id " + request.documentId() : "")
                    + ". Details - " + exe.getMessage(), exe);
        }
    }

    @Override
    public GetDocumentResponse getDocument(String indexName, String documentId) {
        var mappedRequest = elasticSearchMapper.map(indexName, documentId);
        try {
            var response = elasticsearchClient.get(mappedRequest, JsonNode.class);
            return elasticSearchMapper.map(response);
        } catch (IOException | RuntimeException exe) {
            throw new InternalException(
                "Exception fetching document from index " + indexName
                    + " by id " + documentId
                    + ". Details - " + exe.getMessage(), exe);
        }
    }
}
