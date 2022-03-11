package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlartifact.SqlField;
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
public class QField implements SqlField {
    // 表字段对应的表
    private String tableAlias;
    // 表字段
    private String column;
    // 表字段别名
    private String columnAlias;

    private List<Object> params;

    public QField(String tableAlias, String column,String columnAlias,String columnPrefix) {
        this.tableAlias = tableAlias;
        this.column = column;
        if(columnAlias!=null){
            this.columnAlias = columnAlias;
        }else if(columnPrefix!=null){
            this.columnAlias = columnPrefix+"_"+column;
        }
        params = Collections.emptyList();
    }

    public QField(String column, String columnAlias, List<Object> params) {
        this.column = column;
        this.columnAlias = columnAlias;
        this.params = params;
    }

    @Override
    public String getQualifyField() {
        if(this.tableAlias!=null) {
            return this.tableAlias + "." + this.column;
        }else{
            return this.column;
        }
    }

    public static QField column(Object column){
        return column(column,null);
    }

    public static QField column(Object column,String columnAlias){
        String columnStr;
        List<Object> params = new ArrayList<>(1);
        if(column instanceof String){
            columnStr = (String) column;
        }else{
            columnStr = "${param}";
            params.add(column);
        }
        return new QField(columnStr,columnAlias,params);
    }

    public static QField column(String tableAlias,String column,String columnAlias){
        return new QField(tableAlias,column,columnAlias,null);
    }
}
