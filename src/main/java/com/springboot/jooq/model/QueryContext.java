package com.springboot.jooq.model;

import com.springboot.jooq.model.request.RequestMetadata;

import java.util.List;

public class QueryContext {

    private RequestMetadata metadata;
    private List<FilterContext> filterContext;
    private List<String> responseSchema;

    public RequestMetadata getMetadata() {
        return metadata;
    }

    public QueryContext setMetadata(RequestMetadata metadata) {
        this.metadata = metadata;
        return this;
    }

    public List<FilterContext> getFilterContext() {
        return filterContext;
    }

    public QueryContext setFilterContext(List<FilterContext> filterContext) {
        this.filterContext = filterContext;
        return this;
    }

    public List<String> getResponseSchema() {
        return responseSchema;
    }

    public QueryContext setResponseSchema(List<String> responseSchema) {
        this.responseSchema = responseSchema;
        return this;
    }
}
