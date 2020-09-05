package com.springboot.jooq.model;

import java.util.StringJoiner;

public class JoinSchema {

    private String sourceTable;
    private String sourceColumn;
    private String targetTable;
    private String targetColumn;
    private String joinType;
    private Integer joinPrecedence;

    public String getSourceTable() {
        return sourceTable;
    }

    public JoinSchema setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
        return this;
    }

    public String getSourceColumn() {
        return sourceColumn;
    }

    public JoinSchema setSourceColumn(String sourceColumn) {
        this.sourceColumn = sourceColumn;
        return this;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public JoinSchema setTargetTable(String targetTable) {
        this.targetTable = targetTable;
        return this;
    }

    public String getTargetColumn() {
        return targetColumn;
    }

    public JoinSchema setTargetColumn(String targetColumn) {
        this.targetColumn = targetColumn;
        return this;
    }

    public String getJoinType() {
        return joinType;
    }

    public JoinSchema setJoinType(String joinType) {
        this.joinType = joinType;
        return this;
    }

    public Integer getJoinPrecedence() {
        return joinPrecedence;
    }

    public JoinSchema setJoinPrecedence(Integer joinPrecedence) {
        this.joinPrecedence = joinPrecedence;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JoinSchema.class.getSimpleName() + "[", "]")
                .add("sourceTable='" + sourceTable + "'")
                .add("sourceColumn='" + sourceColumn + "'")
                .add("targetTable='" + targetTable + "'")
                .add("targetColumn='" + targetColumn + "'")
                .add("joinType='" + joinType + "'")
                .add("joinPrecedence=" + joinPrecedence)
                .toString();
    }
}
