package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.QField;
import com.example.demo.mbextend.enums.JoinType;
import com.example.demo.mbextend.sqlparts.*;
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
    private List<QueryTable> updateTables;
    private Set<String> sqlSets;
    private List<ConditionExpr> whereConditions;
    List<Object> params;
    private int index;
    private boolean hadEndFrom;

    private UpdateBuilder(SqlTaBle sqlTaBle) {
        this.index = 1;
        updateTables = new ArrayList<>(8);
        sqlTaBle.setTableAlias("t"+this.index);
        updateTables.add(new QueryTable(sqlTaBle));
        params = new ArrayList<>(16);
    }

    public static UpdateBuilder update(SqlTaBle sqlTaBle){
        return new UpdateBuilder(sqlTaBle);
    }

    public UpdateBuilder join(SqlTaBle sqlTaBle, ConditionExpr joinCondition){
        if(hadEndFrom){
            throw new RuntimeException("join子句需紧随from子句后");
        }
        sqlTaBle.setTableAlias("t"+(++this.index));
        this.updateTables.add(new QueryTable(sqlTaBle,joinCondition, JoinType.INNER_JOIN));
        return this;
    }

    public UpdateBuilder leftjoin(SqlTaBle sqlTaBle, ConditionExpr joinCondition){
        if(hadEndFrom){
            throw new RuntimeException("join子句需紧随from子句后");
        }
        sqlTaBle.setTableAlias("t"+(++this.index));
        this.updateTables.add(new QueryTable(sqlTaBle,joinCondition,JoinType.LEFT_JOIN));
        return this;
    }

    public UpdateBuilder rightjoin(SqlTaBle sqlTaBle, ConditionExpr joinCondition){
        if(hadEndFrom){
            throw new RuntimeException("join子句需紧随from子句后");
        }
        sqlTaBle.setTableAlias("t"+(++this.index));
        this.updateTables.add(new QueryTable(sqlTaBle,joinCondition,JoinType.RIGHT_JOIN));
        return this;
    }

    public UpdateBuilder set(QField qField, Object value){
        if(sqlSets==null){
            sqlSets = new HashSet<>(10);
        }
        sqlSets.add(SqlExprBuilder.buildSetExpr(qField,value,this.params));
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
}
