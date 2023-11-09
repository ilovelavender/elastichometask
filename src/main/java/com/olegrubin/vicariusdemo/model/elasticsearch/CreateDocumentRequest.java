package com.olegrubin.vicariusdemo.model.elasticsearch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public record CreateDocumentRequest(
        @JsonProperty(required = true) String indexName,
        @JsonProperty(required = true) JsonNode document,
        @JsonProperty(required = true) String documentId) {

    @JsonCreator
    public CreateDocumentRequest {}
}
