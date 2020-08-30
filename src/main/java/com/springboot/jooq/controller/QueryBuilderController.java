package com.springboot.jooq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.jooq.model.QueryContext;
import com.springboot.jooq.model.request.Request;
import com.springboot.jooq.services.JooqQueryBuilder;
import com.springboot.jooq.services.QueryContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryBuilderController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    QueryContextBuilder queryContextBuilder;

    @Autowired
    JooqQueryBuilder jooqQueryBuilder;

    @PostMapping(value = "/build")
    public String build(@RequestBody Request input){
        QueryContext context = queryContextBuilder.build(input);

        String query = jooqQueryBuilder.buildQuery(context);


        return query;

    }
}
