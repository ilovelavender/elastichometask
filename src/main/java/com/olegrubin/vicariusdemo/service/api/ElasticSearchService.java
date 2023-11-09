package com.olegrubin.vicariusdemo.service.api;

import com.olegrubin.vicariusdemo.model.elasticsearch.CreateDocumentRequest;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateDocumentResponse;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateIndexRequest;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateIndexResponse;
import com.olegrubin.vicariusdemo.model.elasticsearch.GetDocumentResponse;

public interface ElasticSearchService {

    CreateIndexResponse createIndex(CreateIndexRequest request);

    CreateDocumentResponse createDocument(CreateDocumentRequest request);

    GetDocumentResponse getDocument(String indexName, String documentId);
}
