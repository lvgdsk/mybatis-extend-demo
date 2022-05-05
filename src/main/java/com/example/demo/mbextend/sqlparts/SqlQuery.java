package com.example.demo.mbextend.sqlparts;

import com.example.demo.mbextend.QField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:06
 */
public class SqlQuery implements SqlTaBle, SqlExpr, SqlStatement {
    // sql语句
    private String sqlStatement;
    private String tableAlias;
    // 是否是cte查询子句
    private boolean isCte;
    // 是否是递归cte查询子句
    private boolean isRecursive;
    private List<QField> queryColumns;
    private List<Object> params;
    private String columnAlias;

    public SqlQuery(String sqlStatement,List<Object> params,List<QField> queryColumns) {
        this.sqlStatement = sqlStatement;
        this.params = params;
        this.queryColumns = queryColumns;
    }

    @Override
    public String getTableName() {
        if(isCte){
            return tableAlias;
        }
        return "(" + sqlStatement + ")";
    }

    public void setTableAlias(String tableAlias) {
        queryColumns = queryColumns.stream().map(column->{
            String columnName = column.getColumnAlias()==null?column.getColumnName():column.getColumnAlias();
            return new QField(tableAlias,columnName);
        }).collect(Collectors.toList());
        this.tableAlias = tableAlias;
    }

    @Override
    public String getTableAlias() {
        return this.tableAlias;
    }

    @Override
    public List<QField> getQueryColumns() {
        return null;
    }

    public QField column(QField qField){
        String columnName = qField.getColumnAlias()==null?qField.getColumnName():qField.getColumnAlias();
        for (QField field : queryColumns) {
            if(field.getColumnName().equals(columnName)){
                return field;
            }
        }
        throw new RuntimeException(columnName+"字段不存在");
    }

    public SqlQuery union(SqlQuery other){
        return union(other,false);
    }

    public SqlQuery unionAll(SqlQuery other){
        return union(other,true);
    }

    private SqlQuery union(SqlQuery other,boolean isAll){
        String operator = isAll?"union all":"union";
        String sqlStatement = this.getSqlStatement() + operator + other.getSqlStatement();
        List<Object> params = new ArrayList<>(this.params.size()+other.getParams().size());
        params.addAll(this.params);
        params.addAll(other.getParams());
        return new SqlQuery(sqlStatement , params, this.queryColumns);
    }

    public SqlQuery setIsCte(boolean isRecursive){
        this.isCte = true;
        this.isRecursive = isRecursive;
        return this;
    }

    @Override
    public String getSqlStatement() {
        return sqlStatement;
    }

    public boolean isCte() {
        return isCte;
    }

    public boolean isRecursive() {
        return isRecursive;
    }

    @Override
    public String getExpression() {
        return sqlStatement;
    }

    @Override
    public List<Object> getParams() {
        return params;
    }
}
