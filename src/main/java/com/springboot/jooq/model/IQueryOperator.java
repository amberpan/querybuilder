package com.springboot.jooq.model;

import org.jooq.Record;
import org.jooq.SelectQuery;

public interface IQueryOperator {
    void validate(FilterContext input);
    void apply(FilterContext input, SelectQuery<Record> selectQuery);
}
