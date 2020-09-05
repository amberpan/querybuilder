package com.springboot.jooq.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.jooq.model.ColumnSchema;
import com.springboot.jooq.model.JoinSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class AppConfig {

    @Bean("dataDictionary")
    public Map<String, ColumnSchema> dataDictionary(){
        return new HashMap<>();
    }

    @Bean("joinSchema")
    public List<JoinSchema> joinSchema(){
        return new ArrayList<>();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean("dataConverter")
    public Map<String, Function<String,Object>> dataConverter(){
        Map<String, Function<String,Object>> dataConverter = new HashMap<>();

        dataConverter.put("STRING", (i)->String.valueOf(i));
        dataConverter.put("INTEGER", (i)->Integer.parseInt(i));
        dataConverter.put("DECIMAL", (i)-> new BigDecimal(i));
        dataConverter.put("DATE", (i)-> LocalDate.parse(i).format(DateTimeFormatter.ISO_LOCAL_DATE));
        dataConverter.put("TIMESTAMP", (i)-> i);

        return dataConverter;
    }
}
