package com.springboot.jooq.config;

import com.springboot.jooq.model.ColumnSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.util.Map;

@Component
public class DataDictionaryInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataDictionaryInitializer.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataDictionary")
    Map<String, ColumnSchema> dataDictionary;

    @PostConstruct
    public void init(){

        LOGGER.info("Initializing data dictionary from database");
        jdbcTemplate.execute(con -> con.prepareStatement("select * from data_dictionary"), (PreparedStatementCallback<Void>) ps -> {
            ResultSet rs =  ps.executeQuery();
            ColumnSchema schema;
            while(rs.next()){
                schema = new ColumnSchema();
                schema.setAlias(rs.getString("column_alias"))
                        .setColumnName(rs.getString("column_name"))
                        .setDataType(rs.getString("data_type"))
                        .setSchema(rs.getString("schema_name"))
                        .setTableName(rs.getString("table_name"))
                        .setDbName(rs.getString("database_name"))
                        .setFilterPrecedence(rs.getInt("filter_precedence"));
                LOGGER.info("Initializing data dictionary with columnSchema={}",schema);
                dataDictionary.put(schema.getAlias(),schema);
            }
            return null;
        });
    }
}
