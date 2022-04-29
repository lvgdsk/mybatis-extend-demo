package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.QField;
import com.example.demo.mbextend.enums.JoinType;
import com.example.demo.mbextend.sqlparts.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/21 10:08
 * 定义查询语句
 */
@Data
public class QueryBuilder {

    private List<QueryTable> queryTables;
    private List<QField> queryColumns;
    private List<ConditionExpr> whereConditions;
    private List<OrderExpr> queryOrders;
    private List<GroupExpr> queryGroups;
    private List<ConditionExpr> HavingConditions;
    private boolean distinct;
    private Integer selectCount;
    private Integer selectOffset;
    private int index;
    private boolean hadEndFrom;

    private QueryBuilder(SqlTaBle sqlTaBle) {
        this.index = 1;
        this.queryTables = new ArrayList<>(8);
        sqlTaBle.setTableAlias("t"+this.index);
        this.queryTables.add(new QueryTable(sqlTaBle));
    }

    static QueryBuilder from(SqlTaBle sqlTaBle){
        return new QueryBuilder(sqlTaBle);
    }

    public QueryBuilder join(SqlTaBle sqlTaBle, ConditionExpr joinCondition){
        if(hadEndFrom){
            throw new RuntimeException("join子句需紧随from子句后");
        }
        sqlTaBle.setTableAlias("t"+(++this.index));
        this.queryTables.add(new QueryTable(sqlTaBle,joinCondition, JoinType.INNER_JOIN));
        return this;
    }

    public QueryBuilder leftjoin(SqlTaBle sqlTaBle, ConditionExpr joinCondition){
        if(hadEndFrom){
            throw new RuntimeException("join子句需紧随from子句后");
        }
        sqlTaBle.setTableAlias("t"+(++this.index));
        this.queryTables.add(new QueryTable(sqlTaBle,joinCondition,JoinType.LEFT_JOIN));
        return this;
    }

    public QueryBuilder rightjoin(SqlTaBle sqlTaBle, ConditionExpr joinCondition){
        if(hadEndFrom){
            throw new RuntimeException("join子句需紧随from子句后");
        }
        sqlTaBle.setTableAlias("t"+(++this.index));
        this.queryTables.add(new QueryTable(sqlTaBle,joinCondition,JoinType.RIGHT_JOIN));
        return this;
    }

    public QueryBuilder distinct(){
        this.distinct = true;
        this.hadEndFrom = true;
        return this;
    }

    public QueryBuilder select(SqlTaBle ... sqlTaBles){
        if(this.queryColumns==null){
            this.queryColumns = new ArrayList<>(20);
        }
        for (SqlTaBle sqlTaBle : sqlTaBles) {
            sqlTaBle.getQueryColumns().forEach(sf->{
                if(!this.queryColumns.contains(sf)){
                    this.queryColumns.add(sf);
                }
            });
        }
        this.hadEndFrom = true;
        return this;
    }

    public QueryBuilder select(QField ... qFields){
        if(this.queryColumns==null){
            this.queryColumns = new ArrayList<>(20);
        }
        for (QField qField : qFields) {
            if(!this.queryColumns.contains(qField)){
                this.queryColumns.add(qField);
            }
        }
        this.hadEndFrom = true;
        return this;
    }

    public QueryBuilder where(ConditionExpr ... sqlCondition){
        if(this.whereConditions==null) {
            this.whereConditions = new ArrayList<>(Arrays.asList(sqlCondition));
        }else{
            this.whereConditions.addAll(new ArrayList<>(Arrays.asList(sqlCondition)));
        }
        this.hadEndFrom = true;
        return this;
    }

    public QueryBuilder orderBy(OrderExpr ... orderExprList){
        this.queryOrders = new ArrayList<>(Arrays.asList(orderExprList));
        this.hadEndFrom = true;
        return this;
    }

    public QueryBuilder page(int page, int pageSize){
        if(page<=0){
            throw new IllegalArgumentException("参数page需大于0");
        }
        if(pageSize<=0){
            throw new IllegalArgumentException("参数pageSize需大于0");
        }
        this.selectOffset = (page-1)*pageSize;
        this.selectCount = pageSize;
        this.hadEndFrom = true;
        return this;
    }

    public QueryBuilder limit(int offset, int count){
        this.selectCount = count;
        this.selectOffset = offset;
        this.hadEndFrom = true;
        return this;
    }

    public QueryBuilder groupBy(GroupExpr ... groupExprList){
        this.queryGroups = new ArrayList<>(Arrays.asList(groupExprList));
        this.hadEndFrom = true;
        return this;
    }

    public QueryBuilder having(ConditionExpr ... sqlCondition){
        this.HavingConditions = new ArrayList<>(Arrays.asList(sqlCondition));
        this.hadEndFrom = true;
        return this;
    }

    public SqlQuery build(){
        if(queryColumns==null){
            queryTables.forEach(sft-> select(sft.getSqlTaBle()));
        }
        return SqlStatementBuilder.buildQuery(this);
    }
}
