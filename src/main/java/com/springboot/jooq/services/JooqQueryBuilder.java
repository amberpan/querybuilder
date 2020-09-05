package com.springboot.jooq.services;

import com.springboot.jooq.model.ColumnSchema;
import com.springboot.jooq.model.JoinSchema;
import com.springboot.jooq.model.QueryContext;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.impl.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

@Component
public class JooqQueryBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(JooqQueryBuilder.class);

    @Autowired
    Map<String, ColumnSchema> dataDictionary;

    @Autowired
    List<JoinSchema> joinSchema;

    @Value("${db.source.table.qualified.name:[OPM_R_10].[dbo].report_index}")
    private String sourceTable;

    public String buildQuery(QueryContext context){

        SelectQuery<Record> selectQuery = DSLContextFactory.getDisconnectedDSLContext().selectQuery();

        addSelectCriteria(context,selectQuery);
        addFromCriteria(context, selectQuery);
        addFilterCriteria(context,selectQuery);
        addJoinCriteria(context, selectQuery);
        Query query = null;
        try {
            query = DSLContextFactory.getDisconnectedDSLContext().parser().parseQuery(selectQuery.getSQL(ParamType.INLINED));
        }catch (ParserException ex ){
            throw new RuntimeException("Generated query is invalid. Please evaluate criteria.\n"+selectQuery.getSQL(ParamType.INLINED),ex);
        }catch (Exception e){
            throw new RuntimeException("Unknown exception happened.", e);
        }

        return query.getSQL();
    }

    private void addJoinCriteria(QueryContext context, SelectQuery<Record> selectQuery) {

        Set<String> tableSet = context.getFilterContext().stream().map(f->f.getColumnSchema().getTableName()).collect(Collectors.toSet());

        List<JoinSchema> joinCriteria = Optional.ofNullable(joinSchema).orElse(new ArrayList<>())
                .stream().filter(s->tableSet.contains(s.getSourceTable()) && tableSet.contains(s.getTargetTable()))
                .collect(Collectors.toList());


        List<Condition> conditions = Optional.ofNullable(joinCriteria).orElse(new ArrayList<>()).stream()
                .map(j->field(j.getSourceColumn()).equal(field(j.getTargetColumn()))).collect(Collectors.toList());
        selectQuery.addJoin(table(joinCriteria.get(0).getTargetTable()), JoinType.valueOf(joinCriteria.get(0).getJoinType()),conditions.toArray(new Condition[]{}));
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
