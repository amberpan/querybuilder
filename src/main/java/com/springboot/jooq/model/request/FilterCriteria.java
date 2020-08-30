package com.springboot.jooq.model.request;

import java.util.List;
import java.util.StringJoiner;

public class FilterCriteria {
    private String filterName;
    private String operator;
    private List<String> values;

    public String getFilterName() {
        return filterName;
    }

    public FilterCriteria setFilterName(String filterName) {
        this.filterName = filterName;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public FilterCriteria setOperator(String operator) {
        this.operator = operator;
        return this;
    }

    public List<String> getValues() {
        return values;
    }

    public FilterCriteria setValues(List<String> values) {
        this.values = values;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FilterCriteria.class.getSimpleName() + "[", "]")
                .add("filterName='" + filterName + "'")
                .add("operator='" + operator + "'")
                .add("values=" + values)
                .toString();
    }
}
