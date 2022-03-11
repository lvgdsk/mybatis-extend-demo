package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.QField;
import com.example.demo.mbextend.sqlartifact.*;
import lombok.Data;

import java.util.*;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/27 15:17
 * 定义更新语句
 */
@Data
public class UpdateBuilder {
    private List<FromTable> sqlFromTables;
    private Set<String> sqlSets;
    private SqlWhere sqlWhere;
    List<Object> params;

    private UpdateBuilder(SqlTaBle sqlTaBle) {
        sqlFromTables = new ArrayList<>(4);
        sqlFromTables.add(new FromTable(sqlTaBle));
        params = new ArrayList<>(16);
    }

    public static UpdateBuilder update(SqlTaBle sqlTaBle){
        return new UpdateBuilder(sqlTaBle);
    }

    public UpdateBuilder join(SqlTaBle sqlTaBle, SqlCondition joinCondition){
        sqlFromTables.add(new FromTable(sqlTaBle,joinCondition, JoinType.INNER_JOIN));
        return this;
    }

    public UpdateBuilder joinLeft(SqlTaBle sqlTaBle, SqlCondition joinCondition){
        sqlFromTables.add(new FromTable(sqlTaBle,joinCondition, JoinType.LEFT_JOIN));
        return this;
    }

    public UpdateBuilder joinRight(SqlTaBle sqlTaBle, SqlCondition joinCondition){
        sqlFromTables.add(new FromTable(sqlTaBle,joinCondition, JoinType.RIGHT_JOIN));
        return this;
    }

    public UpdateBuilder set(QField qField, Object value){
        if(this.sqlSets==null){
            this.sqlSets = new HashSet<>(10);
        }
        sqlSets.add(SqlExprBuilder.buildSetExpr(qField,value,this.params));
        return this;
    }

    public UpdateBuilder where(SqlWhere sqlWhere){
        this.sqlWhere = sqlWhere;
        return this;
    }

    public UpdateBuilder where(SqlCondition ... sqlCondition){
        this.sqlWhere = SqlWhere.custom(sqlCondition);
        return this;
    }

    public UpdateBuilder and(SqlCondition ... sqlCondition){
        SqlWhere sqlWhere = SqlWhere.custom(sqlCondition);
        this.sqlWhere.addSqlWhere(sqlWhere, ConditionCombineType.AND);
        return this;
    }

    public UpdateBuilder or(SqlCondition ... sqlCondition){
        SqlWhere sqlWhere = SqlWhere.custom(sqlCondition);
        this.sqlWhere.addSqlWhere(sqlWhere,ConditionCombineType.OR);
        return this;
    }

    public UpdateBuilder xor(SqlCondition ... sqlCondition){
        SqlWhere sqlWhere = SqlWhere.custom(sqlCondition);
        this.sqlWhere.addSqlWhere(sqlWhere,ConditionCombineType.XOR);
        return this;
    }

    public SqlUpdate build(){
        return SqlStatementBuilder.buildUpdate(this);
    }
}
