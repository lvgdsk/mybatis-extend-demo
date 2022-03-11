package com.example.demo.mbextend.sqlartifact;

import lombok.Data;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/21 10:16
 */
@Data
public class SqlUpdate implements SqlStatement{
    private String sqlStatement;
    private List<Object> params;

    public SqlUpdate(String sqlStatement, List<Object> params) {
        this.sqlStatement = sqlStatement;
        this.params = params;
    }
}
