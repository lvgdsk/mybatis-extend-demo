package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlDelete;
import com.example.demo.mbextend.sqlparts.SqlQuery;
import com.example.demo.mbextend.sqlparts.SqlUpdate;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/18 15:44
 */
public class BaseSqlBuilder {
    /** 构建select sql语句 */
    public static String select(SqlQuery sqlQuery){
        return fillParams(sqlQuery.getFinalSqlStatement(),sqlQuery.getParams());
    }

    /** 构建update sql语句 */
    public static String update(SqlUpdate sqlUpdate){
        return fillParams(sqlUpdate.getSqlStatement(),sqlUpdate.getParams());
    }


    /** 构建delete sql语句 */
    public static String delete(SqlDelete sqlDelete){
        return fillParams(sqlDelete.getSqlStatement(),sqlDelete.getParams());
    }

    private static String fillParams(String sqlStatement,List<Object> params){
        for (int i = 0; i <params.size(); i++) {
            sqlStatement = sqlStatement.replaceFirst("\\$\\{param\\}",String.format("#{sqlQuery.params[%d]}",i));
        }
        System.out.println(sqlStatement);
        params.forEach(e->{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String paramStr;
            if(e instanceof Date){
                paramStr = format.format(e);
            }else{
                paramStr = e.toString();
            }
            System.out.println(paramStr);
        });
        return sqlStatement;
    }
}
