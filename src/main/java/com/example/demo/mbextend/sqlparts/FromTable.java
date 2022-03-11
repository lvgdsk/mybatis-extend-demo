package com.example.demo.mbextend.sqlparts;

import com.example.demo.mbextend.enums.JoinType;
import lombok.Data;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 10:58
 */
@Data
public class FromTable{
    private SqlTaBle sqlTaBle;
    private SqlCondition sqlCondition;
    private JoinType joinType;

    public FromTable(SqlTaBle sqlTaBle) {
        this.sqlTaBle = sqlTaBle;
    }

    public FromTable(SqlTaBle sqlTaBle, SqlCondition sqlCondition, JoinType joinType) {
        this.sqlTaBle = sqlTaBle;
        this.sqlCondition = sqlCondition;
        this.joinType = joinType;
    }
}
