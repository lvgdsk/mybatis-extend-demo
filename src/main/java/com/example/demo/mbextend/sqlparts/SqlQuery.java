package com.example.demo.mbextend.sqlparts;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:06
 */
@Data
public class SqlQuery implements SqlTaBle, SqlField, SqlStatement {
    // sql语句
    private String sqlStatement;
    // cte子句, 目前只知道cte查询能放在from子句,别的用法有待探索
    private String cteStatement;
    private String tableAlias;
    private boolean isCte = false;
    private String cteName;
    private boolean isUnionALl = false;
    // 是否是递归cte
    private boolean isRecursive = false;
    private Set<SqlField> sqlFields;
    private String columnPrefix;
    private List<Object> params;

    public SqlQuery(String sqlStatement,List<Object> params) {
        this.sqlStatement = sqlStatement;
        this.cteStatement = "";
        this.params = params;
    }

    @Override
    public String getTableName() {
        if(isCte){
            return cteName;
        }
        return "(" + sqlStatement + ")";
    }

    @Override
    public String getColumnPrefix() {
        return columnPrefix;
    }

    @Override
    public String getColumn() {
        return "(" + sqlStatement + ")";
    }

    @Override
    public void setColumnAlias(String alias) {}

    @Override
    public String getColumnAlias() {
        return null;
    }

    @Override
    public String getQualifyField() {
        return "(" + sqlStatement + ")";
    }

    @Override
    public String getTableAlias() {
        return this.tableAlias;
    }

    public SqlField column(SqlField sqlField){
        String columnName = sqlField.getColumnAlias()==null?sqlField.getColumn():sqlField.getColumnAlias();
        for (SqlField field : sqlFields) {
            if(field.getColumn().equals(columnName)){
                return field;
            }
        }
        throw new RuntimeException(columnName+"字段不存在");
    }

    public void unionRecursive(SqlQuery sqlQuery){
        this.isCte = true;
        this.isRecursive = true;
        params.addAll(sqlQuery.getParams());
        this.sqlStatement += " union " + sqlQuery.getSqlStatement();
    }

    public String getFinalSqlStatement(){
        return cteStatement + sqlStatement;
    }


}
