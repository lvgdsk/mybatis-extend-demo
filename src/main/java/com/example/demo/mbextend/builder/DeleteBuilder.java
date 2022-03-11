package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.sqlparts.SqlCondition;
import com.example.demo.mbextend.sqlparts.SqlDelete;
import com.example.demo.mbextend.sqlparts.SqlTaBle;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/27 17:17
 * 定义删除语句
 */
@Data
public class DeleteBuilder {
    private SqlTaBle sqlTaBle;
    private List<SqlCondition> sqlWhere;

    private DeleteBuilder(SqlTaBle sqlTaBle) {
        this.sqlTaBle = sqlTaBle;
    }

    public static DeleteBuilder delete(SqlTaBle sqlTaBle){
        return new DeleteBuilder(sqlTaBle);
    }

    public DeleteBuilder where(SqlCondition... sqlCondition){
        this.sqlWhere = new ArrayList<>(Arrays.asList(sqlCondition));
        return this;
    }

    public SqlDelete build(){
        return SqlStatementBuilder.buildDelete(this);
    }
}
