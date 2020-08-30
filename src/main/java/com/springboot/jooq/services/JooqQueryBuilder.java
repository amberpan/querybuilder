package com.springboot.jooq.services;

import com.springboot.jooq.ColumnSchema;
import com.springboot.jooq.model.QueryContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.jooq.conf.ParamType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Component
public class JooqQueryBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(JooqQueryBuilder.class);

    @Autowired
    Map<String, ColumnSchema> dataDictionary;

    @Value("${db.source.table.qualified.name:[OPM_R_10].[dbo].report_index}")
    private String sourceTable;

    public String buildQuery(QueryContext context){

        SelectQuery<Record> selectQuery = DSLContextFactory.getDisconnectedDSLContext().selectQuery();

        addSelectCriteria(context,selectQuery);
        addFromCriteria(context, selectQuery);
        addFilterCriteria(context,selectQuery);

        return selectQuery.getSQL(ParamType.INLINED);
    }

    private void addFilterCriteria(QueryContext context, SelectQuery<Record> selectQuery) {
        LOGGER.info("Sorting filter criteria as per filter precedence.");
        Collections.sort(context.getFilterContext(), Comparator.comparing(t -> t.getColumnSchema().getFilterPrecedence()));

        context.getFilterContext().forEach(f->f.getQueryOperator().apply(f,selectQuery));
    }

    private void addSelectCriteria(QueryContext context, SelectQuery<Record> selectQuery) {
        if(ObjectUtils.isEmpty(context.getResponseSchema()))
            selectQuery.addSelect(field("*"));
        else
            context.getResponseSchema().stream().forEach(s->selectQuery.addSelect(field(dataDictionary.get(s).getColumnName())));
    }

    private void addFromCriteria(QueryContext context, SelectQuery<Record> selectQuery) {
        selectQuery.addFrom(table(sourceTable));
    }
}
