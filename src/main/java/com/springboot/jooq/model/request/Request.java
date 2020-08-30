package com.springboot.jooq.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {
    private RequestMetadata metadata;
    private List<FilterCriteria> filterCriteria;
    private List<String> responseSchema;

    public RequestMetadata getMetadata() {
        return metadata;
    }

    public Request setMetadata(RequestMetadata metadata) {
        this.metadata = metadata;
        return this;
    }

    public List<FilterCriteria> getFilterCriteria() {
        return filterCriteria;
    }

    public Request setFilterCriteria(List<FilterCriteria> filterCriteria) {
        this.filterCriteria = filterCriteria;
        return this;
    }

    public List<String> getResponseSchema() {
        return responseSchema;
    }

    public Request setResponseSchema(List<String> responseSchema) {
        this.responseSchema = responseSchema;
        return this;
    }
}
