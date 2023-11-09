package com.olegrubin.vicariusdemo.model.elasticsearch;

import co.elastic.clients.elasticsearch.indices.IndexSettings;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateIndexRequest(
        @JsonProperty(required = true) String indexName,
        IndexSettings indexSettings) {

    @JsonCreator
    public CreateIndexRequest {}
}
