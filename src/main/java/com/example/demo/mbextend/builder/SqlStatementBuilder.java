package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.enums.JoinType;
import com.example.demo.mbextend.sqlparts.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/21 10:20
 */
public class SqlStatementBuilder {
    public static SqlQuery buildQuery(QueryBuilder queryBuilder){
        StringBuilder builder = new StringBuilder();
        List<Object> params = new ArrayList<>(25);
        // 构建cte
        buildCte(builder,queryBuilder,params);
        // 构建select子句
        buildSelect(builder, queryBuilder ,params);
        // 构建from子句
        buildFrom(builder, queryBuilder,params);
        // 构建where子句
        buildWhere(builder, queryBuilder.getWhereConditions(),params);
        // 构建group by子句
        buildGroupBy(builder, queryBuilder.getQueryGroups(),params);
        // 构建having子句
        buildWhere(builder, queryBuilder.getHavingConditions(),params);
        // 构建order by
        buildOrderBy(builder, queryBuilder.getQueryOrders(),params);
        // 构建limit子句
        buildLimit(builder, queryBuilder);

        return new SqlQuery(builder.toString(),params,queryBuilder.getQueryColumns());
    }

    private static void buildCte(StringBuilder builder, QueryBuilder queryBuilder, List<Object> params){
        List<SqlQuery> ctes = queryBuilder.getQueryTables().stream().filter(sft -> {
            SqlTaBle sqlTaBle = sft.getSqlTaBle();
            return sqlTaBle instanceof SqlQuery && ((SqlQuery) sqlTaBle).isCte();
        }).map(sft-> (SqlQuery)sft.getSqlTaBle()).collect(Collectors.toList());
        if(ctes.isEmpty()){
            return;
        }
        String cteStatement = ctes.stream().map(cte -> {

            String cteStr;
            if (cte.isRecursive()) {
                cteStr = "recursive ";
            } else {
                cteStr = "";
            }
            params.addAll(cte.getParams());
            cteStr += cte.getTableAlias() + " as ";
            return cteStr;
        }).collect(Collectors.joining(","));
        builder.append("with ").append(cteStatement);
    }

    /** 构建select子句 */
    private static void buildSelect(StringBuilder builder, QueryBuilder queryBuilder, List<Object> params){
        builder.append("select");
        if(queryBuilder.isDistinct()){
            builder.append(" distinct");
        }
        queryBuilder.getQueryColumns().forEach( sf ->{
            params.addAll(sf.getParams());
            builder.append(" ").append(sf.getExpression());
            if(sf.getColumnAlias()!=null){
                builder.append(" as ").append(sf.getColumnAlias());
            }
            builder.append(",");
        });
        builder.deleteCharAt(builder.length()-1);
    }

    /** 构建from子句 */
    private static void buildFrom(StringBuilder builder, QueryBuilder queryBuilder,List<Object> params){
        builder.append(" from");
        buildFromOrUpdate(builder,queryBuilder.getQueryTables(),params);
    }

    private static void buildFromOrUpdate(StringBuilder builder,List<QueryTable> queryTables,List<Object> params){
        queryTables.forEach(qt -> {
            SqlTaBle sqlTaBle = qt.getSqlTaBle();
            JoinType joinType = qt.getJoinType();
            ConditionExpr joinCondition = qt.getJoinCondition();
            if(joinType!=null){
                builder.append(" ").append(joinType.value());
            }
            if(sqlTaBle instanceof SqlQuery){
                params.addAll(((SqlQuery)sqlTaBle).getParams());
            }
            builder.append(" ").append(sqlTaBle.getTableName());
            if(sqlTaBle.getTableAlias()!=null){
                builder.append(" as ").append(sqlTaBle.getTableAlias());
            }
            if(joinCondition!=null){
                params.addAll(joinCondition.getParams());
                builder.append(" on ").append(joinCondition.getExpression());
            }
        });
    }

    /** 构建where子句 */
    private static void buildWhere(StringBuilder builder, List<ConditionExpr> conditionExprList, List<Object> params){
        if(conditionExprList!=null && !conditionExprList.isEmpty()){
            builder.append(" where ").append(
                    conditionExprList.stream().map(expr->{
                    params.addAll(expr.getParams());
                    return expr.getExpression();
                }).collect(Collectors.joining(" and ")));
        }
    }

    /** 构建group by子句 */
    private static void buildGroupBy(StringBuilder builder,List<GroupExpr> queryGroups,List<Object> params){
        if(queryGroups!=null && !queryGroups.isEmpty()){
            builder.append(" group by ").append(
                    queryGroups.stream().map(expr->{
                        params.addAll(expr.getParams());
                        return expr.getExpression();
                    }).collect(Collectors.joining(" , ")));
        }
    }

    /** 构建order by子句 */
    private static void buildOrderBy(StringBuilder builder,List<OrderExpr> queryOrders,List<Object> params){
        if(queryOrders!=null && !queryOrders.isEmpty()){
            builder.append(" order by ").append(
                    queryOrders.stream().map(expr->{
                        params.addAll(expr.getParams());
                        return expr.getExpression();
                    }).collect(Collectors.joining(" , ")));
        }
    }

    /** 构建limit子句 */
    private static void buildLimit(StringBuilder builder, QueryBuilder queryBuilder){
        if(queryBuilder.getSelectCount()!=null){
            builder.append(" limit ")
                    .append(queryBuilder.getSelectCount())
                    .append(" offset ")
                    .append(queryBuilder.getSelectOffset());
        }
    }

    /** 构建update sql语句 */
    public static SqlUpdate buildUpdate(UpdateBuilder updateBuilder){
        StringBuilder builder = new StringBuilder();
        List<Object> params = new ArrayList<>(25);
        builder.append("update");
        // 构建update子句
        buildFromOrUpdate(builder,updateBuilder.getUpdateTables(),params);
        // 构建set子句
        buildSet(builder, updateBuilder,params);
        // 构建where子句
        buildWhere(builder, updateBuilder.getWhereConditions(),params);
        return new SqlUpdate(builder.toString(),params);
    }

    /** 构建set子句 */
    private static void buildSet(StringBuilder builder,UpdateBuilder updateBuilder,List<Object> params){
        params.addAll(updateBuilder.getParams());
        builder.append(" set ")
                .append(String.join(",",updateBuilder.getSqlSets()));
    }

    /** 构建delete sql语句 */
    public static SqlDelete buildDelete(DeleteBuilder deleteBuilder){
        StringBuilder builder = new StringBuilder();
        List<Object> params = new ArrayList<>(10);
        builder.append("delete from ")
                .append(deleteBuilder.getSqlTaBle().getTableName());
        // 构建where子句
        buildWhere(builder, deleteBuilder.getWhereConditions(),params);
        return new SqlDelete(builder.toString(),params);
    }
}
