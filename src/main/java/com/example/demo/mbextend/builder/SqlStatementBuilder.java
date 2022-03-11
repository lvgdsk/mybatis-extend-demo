package com.example.demo.mbextend.builder;

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
        // 构建cte子句
        String cteStatement = buildCte(queryBuilder.getSqlFromTables(),params);
        // 构建select子句
        buildSelect(builder, queryBuilder.getSqlSelectFields(), queryBuilder.isDistinct(),params);
        // 构建from子句
        buildFrom(builder, queryBuilder.getSqlFromTables(),params);
        // 构建where子句
        buildWhere(builder, queryBuilder.getSqlWhere(),params);
        // 构建group by子句
        buildGroupBy(builder, queryBuilder.getSqlGroups(),params);
        // 构建having子句
        buildWhere(builder, queryBuilder.getSqlHaving(),params);
        // 构建order by
        buildOrderBy(builder, queryBuilder.getSqlOrders(),params);
        // 构建limit子句
        buildLimit(builder, queryBuilder);
        // 构建union查询
        buildUnion(builder, queryBuilder.getUnions(),params);

        SqlQuery sqlQuery = new SqlQuery(builder.toString(),params);
        sqlQuery.setCteStatement(cteStatement);

        return sqlQuery;
    }

    private static String buildCte(List<FromTable> sqlFromTables, List<Object> params){
        List<SqlQuery> ctes = sqlFromTables.stream().filter(sft -> {
            SqlTaBle sqlTaBle = sft.getSqlTaBle();
            return sqlTaBle instanceof SqlQuery && ((SqlQuery) sqlTaBle).isCte();
        }).map(sft-> (SqlQuery)sft.getSqlTaBle()).collect(Collectors.toList());
        if(ctes.isEmpty()){
            return "";
        }
        String cteStatement = ctes.stream().map(cte -> {
            String cteStr;
            if (cte.isRecursive()) {
                cteStr = "recursive ";
            } else {
                cteStr = "";
            }
            params.addAll(cte.getParams());
            cteStr += cte.getCteName() + " as " + cte.getQualifyField();
            return cteStr;
        }).collect(Collectors.joining(","));
        return "with "+cteStatement;
    }

    /** 构建select子句 */
    private static void buildSelect(StringBuilder builder, List<SqlField> sqlSelectFields, boolean distinct, List<Object> params){
        builder.append("select ");
        if(distinct){
            builder.append("distinct ");
        }
        sqlSelectFields.forEach( sf ->{
            params.addAll(sf.getParams());
            builder.append(sf.getQualifyField());
            if(sf.getColumnAlias()!=null){
                builder.append(" as ").append(sf.getColumnAlias());
            }
            builder.append(",");
        });
        builder.deleteCharAt(builder.length()-1);
    }

    /** 构建from子句 */
    private static void buildFrom(StringBuilder builder, List<FromTable> sqlFromTables,List<Object> params){
        builder.append(" from ");
        buildFromOrUpdate(builder,sqlFromTables,params);
    }

    private static void buildFromOrUpdate(StringBuilder builder,List<FromTable> sqlFromTables,List<Object> params){
        sqlFromTables.forEach(table -> {
            if(table.getJoinType()!=null){
                builder.append(" ")
                        .append(table.getJoinType().value())
                        .append(" ");
            }
            SqlTaBle sqlTaBle = table.getSqlTaBle();
            if(sqlTaBle instanceof SqlQuery){
                params.addAll(((SqlQuery)sqlTaBle).getParams());
            }
            builder.append(table.getSqlTaBle().getTableName());
            if(table.getSqlTaBle().getTableAlias()!=null){
                builder.append(" as ").append(table.getSqlTaBle().getTableAlias());
            }
            builder.append(" ");
            if(table.getJoinType()!=null){
                params.addAll(table.getSqlCondition().getParams());
                builder.append(" on ");
                if(table.getSqlCondition().isNot()){
                    builder.append(" not ");
                }
                builder.append(table.getSqlCondition().getCondition());
            }
        });
    }

    /** 构建where子句 */
    private static void buildWhere(StringBuilder builder, List<SqlCondition> sqlConditions, List<Object> params){
//        if(sqlWhere!=null) {
//            builder.append(" where ");
//            buildWhereItem(builder, sqlWhere, alias,params);
//            List<SqlWhere> sqlWheres = sqlWhere.getSqlWheres();
//            if(sqlWheres!=null) {
//                sqlWheres.forEach(where -> {
//                    builder.append(where.getCombineType().getSymbol())
//                            .append(" ( ");
//                    buildWhereItem(builder, where, alias ,params);
//                    builder.append(" ) ");
//                });
//            }
//        }
        if(sqlConditions!=null && !sqlConditions.isEmpty()){
            builder.append(" where ").append(
                sqlConditions.stream().map(sc->{
                    params.addAll(sc.getParams());
                    String expr = sc.getCondition();
                    if(sc.isNot()){
                        expr = "not " + expr;
                    }
                    return expr;
                }).collect(Collectors.joining(" and ")));
        }
    }

    /** 构建group by子句 */
    private static void buildGroupBy(StringBuilder builder,List<GroupItem> groupItems,List<Object> params){
        if(groupItems!=null){
            builder.append(" group by ");
            groupItems.forEach(gi -> {
                params.addAll(gi.getSqlField().getParams());
                builder.append(gi.getSqlField().getQualifyField())
                        .append(gi.isAsc()?",":" desc,");
            });
            builder.deleteCharAt(builder.length()-1);
        }
    }

    /** 构建order by子句 */
    private static void buildOrderBy(StringBuilder builder,List<OrderItem> sqlOrders,List<Object> params){
        if(sqlOrders!=null){
            builder.append(" order by ");
            sqlOrders.forEach(item -> {
                params.addAll(item.getSqlField().getParams());
                builder.append(item.getSqlField().getQualifyField())
                        .append(item.isAsc()?",":" desc,");
            });
            builder.deleteCharAt(builder.length()-1);
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

    private static void buildUnion(StringBuilder builder,List<SqlQuery> sqlQueries,List<Object> params){
        if(sqlQueries!=null && !sqlQueries.isEmpty()) {
            sqlQueries.forEach(sq -> {
                builder.append(" union ");
                if (sq.isUnionALl()) {
                    builder.append("all ");
                }
                params.addAll(sq.getParams());
                builder.append(sq.getQualifyField());
            });
        }
    }

    /** 构建update sql语句 */
    public static SqlUpdate buildUpdate(UpdateBuilder updateBuilder){
        StringBuilder builder = new StringBuilder();
        List<Object> params = new ArrayList<>(25);
        builder.append("update ");
        // 构建update子句
        buildFromOrUpdate(builder,updateBuilder.getSqlFromTables(),params);
        // 构建set子句
        buildSet(builder, updateBuilder,params);
        // 构建where子句
        buildWhere(builder, updateBuilder.getSqlWhere(),params);
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
        buildWhere(builder, deleteBuilder.getSqlWhere(),params);
        return new SqlDelete(builder.toString(),params);
    }
}
