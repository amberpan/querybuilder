package com.springboot.jooq.services;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class DSLContextFactory {

    public static DSLContext getDisconnectedDSLContext(){
        return DSL.using(SQLDialect.MYSQL);
    }
}
