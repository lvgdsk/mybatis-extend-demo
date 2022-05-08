package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.*;
import com.example.demo.mbextend.enums.JoinType;

import java.util.*;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/27 15:17
 * 定义更新语句
 */
public class UpdateBuilder {
    private final List<QueryTable> updateTables = new ArrayList<>(8);
    private Set<String> sqlSets;
    private List<ConditionExpr> whereConditions;
    List<Object> params = new ArrayList<>(16);
    private boolean hadEndFrom;

    private UpdateBuilder(SqlTaBle sqlTaBle) {
        updateTables.add(new QueryTable(sqlTaBle));
    }

    public static UpdateBuilder update(SqlTaBle sqlTaBle){
        return new UpdateBuilder(sqlTaBle);
    }

    public UpdateBuilder innerJoin(SqlTaBle sqlTaBle,ConditionExpr joinCondition){
        return joinTable(sqlTaBle,JoinType.INNER_JOIN,joinCondition);
    }

    public UpdateBuilder leftJoin(SqlTaBle sqlTaBle,ConditionExpr joinCondition){
        return joinTable(sqlTaBle,JoinType.LEFT_JOIN,joinCondition);
    }

    public UpdateBuilder rightJoin(SqlTaBle sqlTaBle,ConditionExpr joinCondition){
        return joinTable(sqlTaBle,JoinType.RIGHT_JOIN,joinCondition);
    }

    private UpdateBuilder joinTable(SqlTaBle sqlTaBle,JoinType joinType,ConditionExpr joinCondition){
        if(hadEndFrom){
            throw new RuntimeException("join子句需紧随from子句后");
        }
        this.updateTables.add(new QueryTable(sqlTaBle,joinType,joinCondition));
        return this;
    }

    public UpdateBuilder set(QColumn qColumn, Object value){
        if(sqlSets==null){
            sqlSets = new HashSet<>(10);
        }
        sqlSets.add(SqlExprBuilder.buildSetExpr(qColumn,value,this.params));
        hadEndFrom = true;
        return this;
    }

    public UpdateBuilder where(ConditionExpr ... sqlCondition){
        if(whereConditions==null) {
            whereConditions = new ArrayList<>(Arrays.asList(sqlCondition));
        }else{
            whereConditions.addAll(new ArrayList<>(Arrays.asList(sqlCondition)));
        }
        hadEndFrom = true;
        return this;
    }

    public SqlUpdate build(){
        return SqlStatementBuilder.buildUpdate(this);
    }

    List<QueryTable> getUpdateTables() {
        return updateTables;
    }

    Set<String> getSqlSets() {
        return sqlSets;
    }

    List<ConditionExpr> getWhereConditions() {
        return whereConditions;
    }

    List<Object> getParams() {
        return params;
    }
}
