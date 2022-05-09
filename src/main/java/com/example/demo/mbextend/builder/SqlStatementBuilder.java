package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.*;
import com.example.demo.mbextend.enums.JoinType;

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
        List<Object> cteParams = new ArrayList<>(10);
        // 构建cte
        String cteStatement = buildCte(queryBuilder,cteParams);
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
        return MBHelper.newSqlQuery(builder.toString(),cteStatement,params,cteParams,queryBuilder.getQueryColumns());
    }

    private static String buildCte(QueryBuilder queryBuilder, List<Object> params){
        List<SqlQuery> ctes = queryBuilder.getQueryTables().stream().filter(sft -> {
            SqlTaBle sqlTaBle = sft.getSqlTaBle();
            return sqlTaBle instanceof SqlQuery && MBHelper.isCte((SqlQuery)sqlTaBle);
        }).map(sft-> (SqlQuery)sft.getSqlTaBle()).collect(Collectors.toList());
        if(ctes.isEmpty()){
            return null;
        }
        return ctes.stream().map(cte -> {
            StringBuilder cteItem = new StringBuilder();
            if (MBHelper.isRecursive(cte)) {
                cteItem.append("recursive ");
            }
            params.addAll(MBHelper.getParams(cte));
            cteItem.append(MBHelper.getTableAlias(cte))
                    .append(" as ")
                    .append(MBHelper.getSqlStatement(cte));
            return cteItem;
        }).collect(Collectors.joining(","));
    }

    /** 构建select子句 */
    private static void buildSelect(StringBuilder builder, QueryBuilder queryBuilder, List<Object> params){
        builder.append("select");
        if(queryBuilder.isDistinct()){
            builder.append(" distinct");
        }
        queryBuilder.getQueryColumns().forEach( expr ->{
            params.addAll(expr.getParams());
            builder.append(" ").append(expr.getQualifyExpr());
            if(expr.getColumnAlias()!=null){
                builder.append(" as ").append(expr.getColumnAlias());
            }
            builder.append(",");
        });
        builder.deleteCharAt(builder.length()-1);
    }

    /** 构建from子句 */
    private static void buildFrom(StringBuilder builder, QueryBuilder queryBuilder,List<Object> params){
        if(!queryBuilder.getQueryTables().isEmpty()) {
            builder.append(" from");
            buildFromOrUpdate(builder, queryBuilder.getQueryTables(), params);
        }
    }

    private static void buildFromOrUpdate(StringBuilder builder, List<QueryTable> queryTables, List<Object> params){
        queryTables.forEach(qt -> {
            SqlTaBle sqlTaBle = qt.getSqlTaBle();
            JoinType joinType = qt.getJoinType();
            ConditionExpr joinCondition = qt.getJoinCondition();
            if(joinType!=null){
                builder.append(" ").append(joinType.value());
            }
            if(sqlTaBle instanceof SqlQuery && !MBHelper.isCte((SqlQuery)sqlTaBle)){
                params.addAll(MBHelper.getParams((SqlQuery)sqlTaBle));
            }
            builder.append(" ").append(MBHelper.getTableName(sqlTaBle));
            String tableAlias = MBHelper.getTableAlias(sqlTaBle);
            if(tableAlias!=null && (!(sqlTaBle instanceof SqlQuery) || !MBHelper.isCte((SqlQuery)sqlTaBle))){
                builder.append(" as ").append(tableAlias);
            }
            if(joinCondition!=null){
                params.addAll(MBHelper.getParams(joinCondition));
                builder.append(" on ").append(MBHelper.getConditionExpr(joinCondition));
            }
        });
    }

    /** 构建where子句 */
    private static void buildWhere(StringBuilder builder, List<ConditionExpr> conditionExprList, List<Object> params){
        if(conditionExprList!=null && !conditionExprList.isEmpty()){
            builder.append(" where ").append(
                    conditionExprList.stream().map(expr->{
                    params.addAll(MBHelper.getParams(expr));
                    if(conditionExprList.size()==1) {
                        return MBHelper.getConditionExpr(expr);
                    }else{
                        return MBHelper.getBracketExpression(expr);
                    }
                }).collect(Collectors.joining(" and ")));
        }
    }

    /** 构建group by子句 */
    private static void buildGroupBy(StringBuilder builder, List<GroupOrderExpr> queryGroups, List<Object> params){
        if(queryGroups!=null && !queryGroups.isEmpty()){
            builder.append(" group by ").append(
                    queryGroups.stream().map(expr->{
                        params.addAll(expr.getParams());
                        return expr.getExpression();
                    }).collect(Collectors.joining(" , ")));
        }
    }

    /** 构建order by子句 */
    private static void buildOrderBy(StringBuilder builder, List<GroupOrderExpr> queryOrders, List<Object> params){
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
}
