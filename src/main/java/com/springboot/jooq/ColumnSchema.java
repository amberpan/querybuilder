package com.springboot.jooq;

import java.util.StringJoiner;

public class ColumnSchema {
    private String alias;
    private String columnName;
    private String dataType;
    private String schema;
    private String tableName;
    private String dbName;
    private Integer filterPrecedence=1000;

    public String getAlias() {
        return alias;
    }

    public ColumnSchema setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public String getColumnName() {
        return columnName;
    }

    public ColumnSchema setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public ColumnSchema setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public String getSchema() {
        return schema;
    }

    public ColumnSchema setSchema(String schema) {
        this.schema = schema;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public ColumnSchema setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getDbName() {
        return dbName;
    }

    public ColumnSchema setDbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public Integer getFilterPrecedence() {
        return filterPrecedence;
    }

    public ColumnSchema setFilterPrecedence(Integer filterPrecedence) {
        this.filterPrecedence = filterPrecedence;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ColumnSchema.class.getSimpleName() + "[", "]")
                .add("alias='" + alias + "'")
                .add("columnName='" + columnName + "'")
                .add("dataType='" + dataType + "'")
                .add("schema='" + schema + "'")
                .add("tableName='" + tableName + "'")
                .add("dbName='" + dbName + "'")
                .add("filterPrecedence=" + filterPrecedence)
                .toString();
    }
}
