package com.springboot.jooq.model;

import java.util.List;

public class FilterContext {
    private ColumnSchema columnSchema;
    private QueryOperator queryOperator;
    private List<Object> filterValues;

    public ColumnSchema getColumnSchema() {
        return columnSchema;
    }

    public FilterContext setColumnSchema(ColumnSchema columnSchema) {
        this.columnSchema = columnSchema;
        return this;
    }

    public QueryOperator getQueryOperator() {
        return queryOperator;
    }

    public FilterContext setQueryOperator(QueryOperator queryOperator) {
        this.queryOperator = queryOperator;
        return this;
    }

    public List<Object> getFilterValues() {
        return filterValues;
    }

    public FilterContext setFilterValues(List<Object> filterValues) {
        this.filterValues = filterValues;
        return this;
    }
}
