package com.springboot.jooq.model;

import org.jooq.Operator;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.util.ObjectUtils;

public enum QueryOperator implements IQueryOperator {
    EQUALS{
        @Override
        public void apply(FilterContext input, SelectQuery<Record> selectQuery) {
            selectQuery.addConditions(Operator.AND, DSL.field(input.getColumnSchema().getColumnName()).equal(input.getFilterValues().get(0)));
        }
    },
    LT {

        @Override
        public void apply(FilterContext input, SelectQuery<Record> selectQuery) {
            selectQuery.addConditions(Operator.AND, DSL.field(input.getColumnSchema().getColumnName()).lessThan(input.getFilterValues().get(0)));
        }
    },
    GT {

        @Override
        public void apply(FilterContext input, SelectQuery<Record> selectQuery) {
            selectQuery.addConditions(Operator.AND, DSL.field(input.getColumnSchema().getColumnName()).greaterThan(input.getFilterValues().get(0)));
        }
    },
    LTE {

        @Override
        public void apply(FilterContext input, SelectQuery<Record> selectQuery) {
            selectQuery.addConditions(Operator.AND, DSL.field(input.getColumnSchema().getColumnName()).lessOrEqual(input.getFilterValues().get(0)));
        }
    },
    GTE {

        @Override
        public void apply(FilterContext input, SelectQuery<Record> selectQuery) {
            selectQuery.addConditions(Operator.AND, DSL.field(input.getColumnSchema().getColumnName()).greaterOrEqual(input.getFilterValues().get(0)));
        }
    },
    IN {

        @Override
        public void apply(FilterContext input, SelectQuery<Record> selectQuery) {
            selectQuery.addConditions(Operator.AND, DSL.field(input.getColumnSchema().getColumnName()).in(input.getFilterValues()));
        }
    },
    NOT_IN {
        @Override
        public void apply(FilterContext input, SelectQuery<Record> selectQuery) {
            selectQuery.addConditions(Operator.AND, DSL.field(input.getColumnSchema().getColumnName()).notIn(input.getFilterValues()));
        }
    },
    BETWEEN {
        @Override
        public void apply(FilterContext input, SelectQuery<Record> selectQuery) {
            selectQuery.addConditions(Operator.AND, DSL.field(input.getColumnSchema().getColumnName()).between(input.getFilterValues().get(0),input.getFilterValues().get(1)));
        }
    },
    NOT_BETWEEN {
        @Override
        public void apply(FilterContext input, SelectQuery<Record> selectQuery) {
            selectQuery.addConditions(Operator.AND, DSL.field(input.getColumnSchema().getColumnName()).notBetween(input.getFilterValues().get(0),input.getFilterValues().get(1)));
        }
    },NOT_EQUALS {
        @Override
        public void apply(FilterContext input, SelectQuery<Record> selectQuery) {
            selectQuery.addConditions(Operator.AND, DSL.field(input.getColumnSchema().getColumnName()).notEqual(input.getFilterValues().get(0)));
        }
    };

    @Override
    public void validate(FilterContext input) {
        if(ObjectUtils.isEmpty(input.getFilterValues())){
            throw new RuntimeException("Please provide values to apply filter="+input.getColumnSchema().getAlias());
        }
    }
}
