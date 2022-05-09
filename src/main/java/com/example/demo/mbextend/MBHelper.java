package com.example.demo.mbextend;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/5/7 17:43
 */
public class MBHelper {
    public static String getTableName(SqlTaBle sqlTaBle) {
        return sqlTaBle.getTableName();
    }

    public static String getTableAlias(SqlTaBle sqlTaBle) {
        return sqlTaBle.getTableAlias();
    }

    public static List<SqlExpr> getQColumns(SqlTaBle sqlTaBle) {
        return sqlTaBle.getQColumns();
    }

    public static boolean isRecursive(SqlQuery sqlQuery) {
        return sqlQuery.isRecursive();
    }

    public static void changeColumn(SqlQuery sqlQuery){
        sqlQuery.changeColumn();
    }

    public static List<Object> getParams(SqlQuery sqlQuery) {
        return sqlQuery.getParams();
    }

    public static String getSqlStatement(SqlQuery sqlQuery){
        return sqlQuery.getSqlStatement();
    }

    public static boolean isCte(SqlQuery sqlQuery) {
        return sqlQuery.isCte();
    }

    public static SqlQuery newSqlQuery(String sqlStatement,String cteStatement,List<Object> params,List<Object> cteParams,List<SqlExpr> queryColumns){
        return new SqlQuery(sqlStatement,cteStatement,params,cteParams,queryColumns);
    }

    public static List<Object> getParams(ConditionExpr expr) {
        return expr.getParams();
    }

    public static String getConditionExpr(ConditionExpr expr) {
        return expr.getExpression();
    }

    public static String getBracketExpression(ConditionExpr expr) {
        return expr.getBracketExpression();
    }

    public static ConditionExpr newConditionExpr(String condition,List<Object> params){
        return new ConditionExpr(condition,params);
    }
}
