package com.example.demo.mbextend.utils;

import com.example.demo.mbextend.QColumn;

public class ColUtil {
    public static QColumn column(Object columnName){
        return QColumn.column(columnName);
    }

    public static QColumn column(Object columnName, String columnAlias){
        return QColumn.column(columnName,columnAlias);
    }
}
