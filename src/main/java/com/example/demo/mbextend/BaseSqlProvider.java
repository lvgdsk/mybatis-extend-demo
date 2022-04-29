package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlDelete;
import com.example.demo.mbextend.sqlparts.SqlQuery;
import com.example.demo.mbextend.sqlparts.SqlUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/18 15:44
 */
public class BaseSqlProvider {

    private static final Logger logger = LoggerFactory.getLogger(BaseSqlProvider.class);

    /** 构建select sql语句 */
    public static String select(SqlQuery sqlQuery){
        return fillParams(sqlQuery.getSqlStatement(),sqlQuery.getParams(),"sqlQuery");
    }

    /** 构建update sql语句 */
    public static String update(SqlUpdate sqlUpdate){
        return fillParams(sqlUpdate.getSqlStatement(),sqlUpdate.getParams(),"sqlUpdate");
    }


    /** 构建delete sql语句 */
    public static String delete(SqlDelete sqlDelete){
        return fillParams(sqlDelete.getSqlStatement(),sqlDelete.getParams(),"sqlDelete");
    }

    private static String fillParams(String sqlStatement,List<Object> params,String paramName){
        for (int i = 0; i <params.size(); i++) {
            sqlStatement = sqlStatement.replaceFirst("\\$\\{param}",String.format("#{%s.params[%d]}",paramName,i));
        }
        if(logger.isDebugEnabled()) {
            // 打印 sql 语句
            logger.debug(sqlStatement);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 打印参数
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                String paramStr;
                if (param instanceof Date) {
                    paramStr = format.format(param);
                } else if(param instanceof ZonedDateTime ){
                    paramStr = ((ZonedDateTime)param).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                } else if(param instanceof OffsetDateTime){
                    paramStr = ((OffsetDateTime)param).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }else if(param instanceof OffsetTime){
                    paramStr = ((OffsetTime)param).format(DateTimeFormatter.ISO_LOCAL_TIME);
                }else{
                    paramStr = param.toString();
                }
                logger.debug(i+" : "+paramStr);
            }
        }
        return sqlStatement;
    }
}
