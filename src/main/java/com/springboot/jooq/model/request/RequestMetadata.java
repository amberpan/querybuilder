package com.springboot.jooq.model.request;

import java.util.StringJoiner;

public class RequestMetadata {
    private String description;

    public String getDescription() {
        return description;
    }

    public RequestMetadata setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RequestMetadata.class.getSimpleName() + "[", "]")
                .add("description='" + description + "'")
                .toString();
    }
}
