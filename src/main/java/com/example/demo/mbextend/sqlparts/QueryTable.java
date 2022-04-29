package com.example.demo.mbextend.sqlparts;

import com.example.demo.mbextend.enums.JoinType;
import lombok.Data;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 10:58
 */
@Data
public class QueryTable{
    private SqlTaBle sqlTaBle;
    private ConditionExpr joinCondition;
    private JoinType joinType;

    public QueryTable(SqlTaBle sqlTaBle) {
        this.sqlTaBle = sqlTaBle;
    }

    public QueryTable(SqlTaBle sqlTaBle, ConditionExpr joinCondition, JoinType joinType) {
        this.sqlTaBle = sqlTaBle;
        this.joinCondition = joinCondition;
        this.joinType = joinType;
    }
}
