package com.example.demo.mbextend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 代表一条查询sql语句
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:06
 */
public class SqlQuery extends SqlTaBle{
    private final String cteStatement;
    // sql语句
    private final String sqlStatement;
    private String tableAlias;
    // 是否是cte查询子句
    private boolean isCte;
    private boolean isRecursive;
    private List<SqlExpr> queryColumns;
    boolean hadChangColumn = false;
    private final List<Object> params;
    private final List<Object> cteParams;
    private List<Object> sqlParams;

    public SqlQuery(String sqlStatement,String cteStatement,List<Object> params,List<Object> cteParams,List<SqlExpr> queryColumns) {
        this.tableAlias = AliasWorker.getAlias();
        this.sqlStatement = sqlStatement;
        this.cteStatement = cteStatement;
        this.params = params;
        this.cteParams = cteParams;
        this.queryColumns = queryColumns;
    }

    @Override
    public String getTableName() {
        if(isCte){
            return String.valueOf(tableAlias);
        }
        return "(" + sqlStatement + ")";
    }

    @Override
    public String getTableAlias() {
        return String.valueOf(tableAlias);
    }

    public void setQColumns(List<SqlExpr> queryColumns) {
        this.queryColumns = queryColumns;
    }

    @Override
    public List<SqlExpr> getQColumns() {
        return queryColumns;
    }

    public SqlExpr column(SqlExpr sqlExpr){
        changeColumn();
        String columnName = sqlExpr.getColumnAlias()==null?sqlExpr.getExpression():sqlExpr.getColumnAlias();
        for (SqlExpr expr : queryColumns) {
            if(expr.getExpression().equals(columnName)){
                return expr;
            }
        }
        throw new RuntimeException(String.format("子查询内不存在%s查询列",columnName));
    }

    public SqlQuery unionRecursiveCte(SqlQuery query){
        if(!this.isCte){
            throw new RuntimeException("不是CTE查询");
        }
        String sqlStatement = this.sqlStatement + " union " + query.sqlStatement;
        List<Object> params = new ArrayList<>(this.params.size()+query.params.size());
        params.addAll(this.params);
        params.addAll(query.params);
        SqlQuery sqlQuery = new SqlQuery(sqlStatement, null, params, Collections.emptyList(), this.queryColumns);
        sqlQuery.isCte = true;
        sqlQuery.isRecursive = true;
        sqlQuery.tableAlias = this.tableAlias;
        return sqlQuery;
    }

    public SqlQuery union(SqlQuery other){
        return union(other,false);
    }

    public SqlQuery unionAll(SqlQuery other){
        return union(other,true);
    }

    private SqlQuery union(SqlQuery other,boolean isAll){
        String operator = isAll?" union all ":" union ";
        String sqlStatement = this.sqlStatement + operator + other.sqlStatement;
        List<Object> params = new ArrayList<>(this.params.size()+other.params.size());
        params.addAll(this.params);
        params.addAll(other.params);
        List<Object> cteParams = new ArrayList<>(16);
        String cteStatement = null;
        if(this.cteStatement!=null){
            cteStatement = this.cteStatement;
            cteParams.add(this.cteParams);
        }
        if(other.cteStatement!=null){
            if(cteStatement==null){
                cteStatement = "";
            }else{
                cteStatement += ",";
            }
            cteStatement += other.cteStatement;
            cteParams.add(other.cteParams);
        }
        return new SqlQuery(sqlStatement ,cteStatement, params,cteParams, this.queryColumns);
    }

    public void setIsCte(){
        this.isCte = true;
    }

    public boolean isCte() {
        return isCte;
    }

    public String getSqlStatement() {
        return "("+sqlStatement+")";
    }

    public List<Object> getParams() {
        return params;
    }

    public String getFinalSqlStatement(){
        if(cteStatement!=null){
            return "with "+cteStatement + sqlStatement;
        }else{
            return sqlStatement;
        }
    }

    public void changeColumn(){
        if(!hadChangColumn){
            queryColumns = queryColumns.stream().map(expr ->
                    new QColumn(tableAlias, expr.getColumnAlias() == null ?
                            expr.getExpression() : expr.getColumnAlias(), null)
            ).collect(Collectors.toList());
            hadChangColumn = true;
        }
    }

    boolean isRecursive() {
        return isRecursive;
    }

    List<Object> getSqlParams() {
        if(sqlParams==null) {
            sqlParams = new ArrayList<>(this.params.size() + this.cteParams.size());
            sqlParams.addAll(this.cteParams);
            sqlParams.addAll(this.params);
        }
        return sqlParams;
    }
}
