package com.example.demo.mbextend;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/5/7 17:43
 */
public class MBUtil {
    public static String getTableName(SqlTaBle sqlTaBle) {
        return sqlTaBle.getTableName();
    }

    public static String getTableAlias(SqlTaBle sqlTaBle) {
        return sqlTaBle.getTableAlias();
    }

    public static List<SqlExpr> getQColumns(SqlTaBle sqlTaBle) {
        return sqlTaBle.getQColumns();
    }

    public static boolean isRecursive(SqlQuery sqlQuery) {
        return sqlQuery.isRecursive();
    }
}
