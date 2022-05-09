package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.*;
import com.example.demo.mbextend.enums.JoinType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/21 10:08
 * 定义查询语句
 */
public class QueryBuilder {

    private final List<QueryTable> queryTables = new ArrayList<>(8);
    private List<SqlExpr> queryColumns;
    private List<ConditionExpr> whereConditions;
    private List<GroupOrderExpr> queryOrders;
    private List<GroupOrderExpr> queryGroups;
    private List<ConditionExpr> HavingConditions;
    private boolean distinct;
    private Integer selectCount;
    private Integer selectOffset;
    private boolean hadEndFrom;

    private QueryBuilder(SqlTaBle sqlTaBle) {
        if(sqlTaBle!=null) {
            queryTables.add(new QueryTable(sqlTaBle));
        }
    }

    static QueryBuilder from(SqlTaBle sqlTaBle){
        return new QueryBuilder(sqlTaBle);
    }

    public QueryBuilder innerJoin(SqlTaBle sqlTaBle,ConditionExpr joinCondition){
        return joinTable(sqlTaBle,JoinType.INNER_JOIN,joinCondition);
    }

    public QueryBuilder leftJoin(SqlTaBle sqlTaBle,ConditionExpr joinCondition){
        return joinTable(sqlTaBle,JoinType.LEFT_JOIN,joinCondition);
    }

    public QueryBuilder rightJoin(SqlTaBle sqlTaBle,ConditionExpr joinCondition){
        return joinTable(sqlTaBle,JoinType.RIGHT_JOIN,joinCondition);
    }

    private  QueryBuilder joinTable(SqlTaBle sqlTaBle,JoinType joinType,ConditionExpr joinCondition){
        if(hadEndFrom){
            throw new RuntimeException("join子句需紧随from子句后");
        }
        this.queryTables.add(new QueryTable(sqlTaBle,joinType,joinCondition));
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
            if(sqlTaBle instanceof SqlQuery){
                MBHelper.changeColumn((SqlQuery)sqlTaBle);
            }
            MBHelper.getQColumns(sqlTaBle).forEach(sf -> {
                if (!this.queryColumns.contains(sf)) {
                    this.queryColumns.add(sf);
                }
            });
        }
        this.hadEndFrom = true;
        return this;
    }

    public QueryBuilder select(SqlExpr... sqlExprs){
        if(this.queryColumns==null){
            this.queryColumns = new ArrayList<>(20);
        }
        for (SqlExpr sqlExpr : sqlExprs) {
            if(!this.queryColumns.contains(sqlExpr)){
                this.queryColumns.add(sqlExpr);
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

    public QueryBuilder orderBy(GroupOrderExpr ... orderExprList){
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

    public QueryBuilder groupBy(GroupOrderExpr... groupExprList){
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

    List<QueryTable> getQueryTables() {
        return queryTables;
    }

    List<SqlExpr> getQueryColumns() {
        return queryColumns;
    }

    List<ConditionExpr> getWhereConditions() {
        return whereConditions;
    }

    List<GroupOrderExpr> getQueryOrders() {
        return queryOrders;
    }

    List<GroupOrderExpr> getQueryGroups() {
        return queryGroups;
    }

    List<ConditionExpr> getHavingConditions() {
        return HavingConditions;
    }

    boolean isDistinct() {
        return distinct;
    }

    Integer getSelectCount() {
        return selectCount;
    }

    Integer getSelectOffset() {
        return selectOffset;
    }
}
