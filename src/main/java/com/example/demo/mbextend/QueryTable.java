package com.example.demo.mbextend;

import com.example.demo.mbextend.enums.JoinType;
import lombok.Data;

import java.util.stream.Collectors;

/**
 * 查询、更新、删除的表
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 10:58
 */
public class QueryTable{
    private final SqlTaBle sqlTaBle;
    private final ConditionExpr joinCondition;
    private final JoinType joinType;

    public QueryTable(SqlTaBle sqlTaBle) {
        this(sqlTaBle,null,null);
    }

    public QueryTable(SqlTaBle sqlTaBle,JoinType joinType,ConditionExpr joinCondition) {
        this.sqlTaBle = sqlTaBle;
        this.joinType = joinType;
        this.joinCondition = joinCondition;
    }

    public SqlTaBle getSqlTaBle() {
        return sqlTaBle;
    }

    public ConditionExpr getJoinCondition() {
        return joinCondition;
    }

    public JoinType getJoinType() {
        return joinType;
    }
}
