package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.ConditionExpr;
import com.example.demo.mbextend.SqlDelete;
import com.example.demo.mbextend.SqlTaBle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/27 17:17
 * 定义删除语句
 */
public class DeleteBuilder {
    private SqlTaBle sqlTaBle;
    private List<ConditionExpr> whereConditions;

    private DeleteBuilder(SqlTaBle sqlTaBle) {
        this.sqlTaBle = sqlTaBle;
    }

    public static DeleteBuilder delete(SqlTaBle sqlTaBle){
        return new DeleteBuilder(sqlTaBle);
    }

    public DeleteBuilder where(ConditionExpr ... sqlCondition){
        if(this.whereConditions==null) {
            this.whereConditions = new ArrayList<>(Arrays.asList(sqlCondition));
        }else{
            this.whereConditions.addAll(new ArrayList<>(Arrays.asList(sqlCondition)));
        }
        return this;
    }

    public SqlDelete build(){
        return SqlStatementBuilder.buildDelete(this);
    }

    SqlTaBle getSqlTaBle() {
        return sqlTaBle;
    }

    List<ConditionExpr> getWhereConditions() {
        return whereConditions;
    }
}
