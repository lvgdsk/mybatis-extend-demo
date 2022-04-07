package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.QField;
import com.example.demo.mbextend.enums.JoinType;
import com.example.demo.mbextend.sqlparts.*;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/21 10:08
 * 定义查询语句
 */
@Data
public class QueryBuilder {

    private List<FromTable> sqlFromTables;
    private List<SqlField> sqlSelectFields;
    private List<SqlCondition> sqlWhere;
    private List<OrderItem> sqlOrders;
    private List<GroupItem> sqlGroups;
    private List<SqlCondition> sqlHaving;
    private boolean distinct;
    private Integer selectCount;
    private Integer selectOffset;
    private List<SqlQuery> unions;
    private String columnPrefix;
    private String tableAlias;

    private QueryBuilder(SqlTaBle sqlTaBle) {
        sqlFromTables = new ArrayList<>(4);
        sqlFromTables.add(new FromTable(sqlTaBle));
    }

    public QueryBuilder distinct(){
        distinct = true;
        return this;
    }

    public QueryBuilder select(SqlTaBle ... sqlTaBles){
        if(this.sqlSelectFields==null){
            this.sqlSelectFields = new ArrayList<>(20);
        }
        for (SqlTaBle sqlTaBle : sqlTaBles) {
            if(sqlTaBle instanceof SqlQuery){
                SqlQuery sqlQuery = (SqlQuery)sqlTaBle;
                for(SqlField sqlField : sqlQuery.getSqlFields()){
                    if(!sqlSelectFields.contains(sqlField)){
                        sqlSelectFields.add(sqlField);
                    }
                }
            }else {
                Field[] fields = sqlTaBle.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.getType() != QField.class) {
                        continue;
                    }
                    try {
                        QField qField = (QField) field.get(sqlTaBle);
                        this.sqlSelectFields.add(qField);
                    } catch (IllegalAccessException illegalAccessException) {
                        throw new RuntimeException(String.format("获取%s实例的%s字段值失败",
                                sqlTaBle.getClass().getSimpleName(), field.getName()));
                    }
                }
            }
        }
        return this;
    }

    public QueryBuilder select(SqlField ... sqlFields){
        if(this.sqlSelectFields==null){
            this.sqlSelectFields = new ArrayList<>(20);
        }
        for (SqlField sqlField : sqlFields) {
            if(!sqlSelectFields.contains(sqlField)){
                sqlSelectFields.add(sqlField);
            }
        }
        return this;
    }

    public static QueryBuilder from(SqlTaBle sqlTaBle){
        return new QueryBuilder(sqlTaBle);
    }

    public QueryBuilder join(SqlTaBle sqlTaBle, SqlCondition joinCondition){
        sqlFromTables.add(new FromTable(sqlTaBle,joinCondition, JoinType.INNER_JOIN));
        return this;
    }

    public QueryBuilder leftjoin(SqlTaBle sqlTaBle, SqlCondition joinCondition){
        sqlFromTables.add(new FromTable(sqlTaBle,joinCondition,JoinType.LEFT_JOIN));
        return this;
    }

    public QueryBuilder rightjoin(SqlTaBle sqlTaBle, SqlCondition joinCondition){
        sqlFromTables.add(new FromTable(sqlTaBle,joinCondition,JoinType.RIGHT_JOIN));
        return this;
    }

    public QueryBuilder where(SqlCondition ... sqlCondition){
        if(this.sqlWhere==null) {
            this.sqlWhere = new ArrayList<>(Arrays.asList(sqlCondition));
        }else{
            this.sqlWhere.addAll(new ArrayList<>(Arrays.asList(sqlCondition)));
        }
        return this;
    }

    public QueryBuilder orderBy(OrderItem ... orderItems){
        this.sqlOrders = new ArrayList<>(Arrays.asList(orderItems));
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
        return this;
    }

    public QueryBuilder limit(int offset, int count){
        this.selectCount = count;
        this.selectOffset = offset;
        return this;
    }

    public QueryBuilder groupBy(GroupItem ... groupItems){
        this.sqlGroups = new ArrayList<>(Arrays.asList(groupItems));
        return this;
    }

    public QueryBuilder having(SqlCondition ... sqlCondition){
        this.sqlHaving = new ArrayList<>(Arrays.asList(sqlCondition));
        return this;
    }

    public QueryBuilder union(SqlQuery ... sqlQueries){
        if (unions == null) {
            unions = new ArrayList<>(5);
        }
        unions.addAll(Arrays.stream(sqlQueries).peek(sq -> sq.setUnionALl(false)).collect(Collectors.toList()));
        return this;
    }

    public QueryBuilder unionALL(SqlQuery ... sqlQueries){
        if (unions == null) {
            unions = new ArrayList<>(5);
        }
        unions.addAll(Arrays.asList(sqlQueries));
        return this;
    }

    public SqlQuery build(){
        return build(null);
    }

    public SqlQuery build(String cteName){
        if(sqlSelectFields==null){
            sqlFromTables.forEach(sft-> select(sft.getSqlTaBle()));
        }
        SqlQuery sqlQuery = SqlStatementBuilder.buildQuery(this);
        if(tableAlias!=null){
            sqlQuery.setTableAlias(tableAlias);
        }else {
            sqlQuery.setTableAlias("t" + UUID.randomUUID().toString().substring(0, 8));
        }
        if(cteName!=null){
            sqlQuery.setCte(true);
            sqlQuery.setCteName(cteName);
        }
        Set<SqlField> sqlFields = sqlSelectFields.stream().map(sf ->
             new QField(sqlQuery.getTableAlias(), sf.getColumnAlias()!=null?sf.getColumnAlias():sf.getColumn(), null,columnPrefix)
        ).collect(Collectors.toSet());
        sqlQuery.setSqlFields(sqlFields);

        return sqlQuery;
    }

    public QueryBuilder columnPrefix(String columnPrefix){
        this.columnPrefix = columnPrefix;
        return this;
    }

    public QueryBuilder tableAlias(String tableAlias){
        this.tableAlias = tableAlias;
        return this;
    }
}
