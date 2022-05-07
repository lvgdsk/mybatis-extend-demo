package com.example.demo.mbextend;

import java.util.List;

/**
 * 代表一个delete语句
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/21 10:17
 */
public class SqlDelete{
    private final String sqlStatement;
    private final List<Object> params;

    public SqlDelete(String sqlStatement, List<Object> params) {
        this.sqlStatement = sqlStatement;
        this.params = params;
    }
    public String getSqlStatement() {
        return sqlStatement;
    }

    public List<Object> getParams() {
        return params;
    }
}
