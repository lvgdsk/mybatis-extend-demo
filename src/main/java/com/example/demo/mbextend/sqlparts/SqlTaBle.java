package com.example.demo.mbextend.sqlparts;

import com.example.demo.mbextend.QField;

import java.util.List;

/**
 * 表抽象接口，有两类表：1：真实的表，2：子查询
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 10:42
 */
public interface SqlTaBle {
    /**
     * 获取表名
     */
    String getTableName();

    /**
     * 获取表别名
     */
    String getTableAlias();

    List<QField> getQueryColumns();

    void setTableAlias(String tableAlias);
}
