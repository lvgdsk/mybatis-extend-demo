package com.example.demo.mbextend.sqlparts;

import lombok.Data;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/21 10:17
 */
@Data
public class SqlDelete implements SqlStatement{
    private String sqlStatement;
    private List<Object> params;

    public SqlDelete(String sqlStatement, List<Object> params) {
        this.sqlStatement = sqlStatement;
        this.params = params;
    }
}
