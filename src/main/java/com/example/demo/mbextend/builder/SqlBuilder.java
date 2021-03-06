package com.example.demo.mbextend.builder;

import com.example.demo.mbextend.SqlTaBle;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/21 10:49
 */
public class SqlBuilder {

    public static QueryBuilder query(SqlTaBle sqlTaBle) {return QueryBuilder.from(sqlTaBle);}

    public static UpdateBuilder update(SqlTaBle sqlTaBle){
        return UpdateBuilder.update(sqlTaBle);
    }
}
