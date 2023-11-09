package com.olegrubin.vicariusdemo.model.elasticsearch;

import com.fasterxml.jackson.databind.JsonNode;

public record GetDocumentResponse(String id, String index, JsonNode document) {
}
