package com.springboot.jooq.config;

import com.springboot.jooq.model.ColumnSchema;
import com.springboot.jooq.model.JoinSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@Component
public class JoinSchemaInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JoinSchemaInitializer.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("joinSchema")
    List<JoinSchema> joinSchema;

    @PostConstruct
    public void init(){
        LOGGER.info("Initializing join schema from database");
        jdbcTemplate.execute(con -> con.prepareStatement("select * from join_schema"), (PreparedStatementCallback<Void>) ps -> {
            ResultSet rs =  ps.executeQuery();
            JoinSchema schema;
            while(rs.next()){
                schema = new JoinSchema();
                schema.setSourceTable(rs.getString("source_table"))
                        .setSourceColumn(rs.getString("source_column"))
                        .setTargetTable(rs.getString("target_table"))
                        .setTargetColumn(rs.getString("target_column"))
                        .setJoinType(rs.getString("join_type"))
                        .setJoinPrecedence(rs.getInt("join_precedence"));
                LOGGER.info("Initializing join schema with={}",schema);
                joinSchema.add(schema);
            }
            return null;
        });
    }
}
