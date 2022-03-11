package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.sqlartifact.SqlTaBle;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/21 10:49
 */
public class SqlBuilder {

    public  static QueryBuilder query(SqlTaBle sqlTaBle) {return QueryBuilder.from(sqlTaBle);}

    public static UpdateBuilder update(SqlTaBle sqlTaBle){
        return UpdateBuilder.update(sqlTaBle);
    }

    public static DeleteBuilder delete(SqlTaBle sqlTaBle){
        return DeleteBuilder.delete(sqlTaBle);
    }
}
