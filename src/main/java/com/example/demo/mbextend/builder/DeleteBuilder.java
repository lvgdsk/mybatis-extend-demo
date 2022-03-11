package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.sqlparts.*;
import lombok.Data;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/27 17:17
 * 定义删除语句
 */
@Data
public class DeleteBuilder {
    private SqlTaBle sqlTaBle;
    private SqlWhere sqlWhere;

    private DeleteBuilder(SqlTaBle sqlTaBle) {
        this.sqlTaBle = sqlTaBle;
    }

    public static DeleteBuilder delete(SqlTaBle sqlTaBle){
        return new DeleteBuilder(sqlTaBle);
    }

    public DeleteBuilder where(SqlWhere sqlWhere){
        this.sqlWhere = sqlWhere;
        return this;
    }

    public DeleteBuilder where(SqlCondition... sqlCondition){
        this.sqlWhere = SqlWhere.custom(sqlCondition);
        return this;
    }

    public DeleteBuilder and(SqlCondition ... sqlCondition){
        SqlWhere sqlWhere = SqlWhere.custom(sqlCondition);
        this.sqlWhere.addSqlWhere(sqlWhere, ConditionCombineType.AND);
        return this;
    }

    public DeleteBuilder or(SqlCondition ... sqlCondition){
        SqlWhere sqlWhere = SqlWhere.custom(sqlCondition);
        this.sqlWhere.addSqlWhere(sqlWhere,ConditionCombineType.OR);
        return this;
    }

    public DeleteBuilder xor(SqlCondition ... sqlCondition){
        SqlWhere sqlWhere = SqlWhere.custom(sqlCondition);
        this.sqlWhere.addSqlWhere(sqlWhere,ConditionCombineType.XOR);
        return this;
    }

    public SqlDelete build(){
        return SqlStatementBuilder.buildDelete(this);
    }
}
