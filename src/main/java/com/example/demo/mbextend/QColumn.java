package com.example.demo.mbextend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 代表一个表列
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:08
 */
public class QColumn implements SqlExpr {

    // 表字段对应的表
    private final String tableAlias;
    // 表字段名
    private final String columnName;
    // 表字段别名
    private final String columnAlias;

    private final List<Object> params;

    public QColumn(String tableAlias, String columnName, String columnAlias) {
        this.tableAlias = tableAlias;
        this.columnName = columnName;
        this.columnAlias = columnAlias;
        params = Collections.emptyList();
    }

    private QColumn(String columnName, String columnAlias, List<Object> params) {
        this.tableAlias = null;
        this.columnName = columnName;
        this.columnAlias = columnAlias;
        this.params = params;
    }

    public static QColumn column(Object columnName){
        return column(columnName,null);
    }

    public static QColumn column(Object columnName, String columnAlias){
        String columnExpr;
        List<Object> params = new ArrayList<>(1);
        if(columnName instanceof String){
            columnExpr = (String) columnName;
        }else{
            columnExpr = "${param}";
            params.add(columnName);
        }
        return new QColumn(columnExpr,columnAlias,params);
    }

    public String getColumnName() {
        return columnName;
    }

    @Override
    public String getExpression() {
        return columnName;
    }

    @Override
    public String getQualifyExpr() {
        if(tableAlias!=null) {
            return tableAlias + "." + columnName;
        }else{
            return columnName;
        }
    }

    @Override
    public List<Object> getParams() {
        return params;
    }

    public String getColumnAlias() {
        return columnAlias;
    }
}
