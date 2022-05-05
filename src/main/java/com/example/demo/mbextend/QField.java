package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlExpr;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:08
 */
@Data
public class QField implements SqlExpr {
    // 表字段对应的表
    private String tableAlias;
    // 表字段名
    private String columnName;
    // 表字段别名
    private String columnAlias;

    private List<Object> params;

    public QField(String tableAlias,String columnName) {
        this.tableAlias = tableAlias;
        this.columnName = columnName;
        params = Collections.emptyList();
    }

    private QField(String columnName, String columnAlias, List<Object> params) {
        this.columnName = columnName;
        this.columnAlias = columnAlias;
        this.params = params;
    }

    public static QField column(Object columnName){
        return column(columnName,null);
    }

    public static QField column(Object columnName,String columnAlias){
        String columnExpr;
        List<Object> params = new ArrayList<>(1);
        if(columnName instanceof String){
            columnExpr = (String) columnName;
        }else{
            columnExpr = "${param}";
            params.add(columnName);
        }
        return new QField(columnExpr,columnAlias,params);
    }

    public String getColumnName() {
        return columnName;
    }

    @Override
    public String getExpression() {
        if(this.tableAlias!=null) {
            return this.tableAlias + "." + this.columnName;
        }else{
            return this.columnName;
        }
    }
}
