package com.olegrubin.vicariusdemo.mapper;

import co.elastic.clients.elasticsearch.core.CreateRequest;
import co.elastic.clients.elasticsearch.core.CreateResponse;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateDocumentRequest;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateDocumentResponse;
import com.olegrubin.vicariusdemo.model.elasticsearch.CreateIndexResponse;
import com.olegrubin.vicariusdemo.model.elasticsearch.GetDocumentResponse;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchMapper {

    public CreateIndexRequest map(final com.olegrubin.vicariusdemo.model.elasticsearch.CreateIndexRequest request) {
        return new CreateIndexRequest.Builder()
                .index(request.indexName())
                .settings(request.indexSettings())
                .build();
    }

    public CreateIndexResponse map(final co.elastic.clients.elasticsearch.indices.CreateIndexResponse response) {
        return new CreateIndexResponse(response.index());
    }

    public CreateRequest<JsonNode> map(final CreateDocumentRequest request) {
        return new CreateRequest.Builder<JsonNode>()
            .index(request.indexName())
            .id(request.documentId())
            .document(request.document())
            .build();
    }

    public CreateDocumentResponse map(final CreateResponse response) {
        return new CreateDocumentResponse(response.id(), response.index());
    }

    public GetRequest map(final String indexName, final String documentId) {
        return new GetRequest.Builder()
            .index(indexName)
            .id(documentId)
            .build();
    }

    public GetDocumentResponse map(final GetResponse<JsonNode> response) {
        return new GetDocumentResponse(
            response.id(), response.index(), response.source());
    }
}
