package com.springboot.jooq.services;

import com.springboot.jooq.ColumnSchema;
import com.springboot.jooq.model.FilterContext;
import com.springboot.jooq.model.QueryContext;
import com.springboot.jooq.model.QueryOperator;
import com.springboot.jooq.model.request.FilterCriteria;
import com.springboot.jooq.model.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class QueryContextBuilder {

    @Autowired
    Map<String, ColumnSchema> dataDictionary;

    @Autowired
    Map<String, Function<String,Object>> dataConverter;

    public QueryContext build(Request input){
        QueryContext context = new QueryContext().setResponseSchema(input.getResponseSchema()).setMetadata(input.getMetadata());

        context.setFilterContext(buildFilterContext(input.getFilterCriteria()));

        return context;
    }

    private List<FilterContext> buildFilterContext(List<FilterCriteria> filterCriteria) {
        List<FilterContext> filterContextList = new ArrayList<>();

        filterCriteria.forEach(f->{
            FilterContext filterContext = new FilterContext();
            filterContext.setColumnSchema(dataDictionary.get(f.getFilterName()))
            .setFilterValues(f.getValues().stream()
                    .map(v->dataConverter.get(filterContext.getColumnSchema().getDataType()).apply(v)).collect(Collectors.toList()))
            .setQueryOperator(QueryOperator.valueOf(f.getOperator()));

            filterContextList.add(filterContext);
        });


        return filterContextList;
    }
}
