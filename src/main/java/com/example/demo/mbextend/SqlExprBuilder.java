package com.example.demo.mbextend;

import com.example.demo.mbextend.enums.ExprEnum;
import com.example.demo.mbextend.enums.SqlOperator;
import com.example.demo.mbextend.enums.TimeField;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表达式构建类
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/18 15:23
 */
public class SqlExprBuilder {
    /** 参数解析 */
    private static String[] parseColumn(List<Object> sqlParams,Object ... params){
        String[] actParams = new String[params.length];
        int index = 0;
        for (Object param : params) {
            if(param==null){
                actParams[index] = "";
            }else if(param instanceof SqlExpr) {
                SqlExpr sqlExpr = (SqlExpr)param;
                sqlParams.addAll(sqlExpr.getParams());
                actParams[index] = sqlExpr.getQualifyExpr();
            }else if(param instanceof ConditionExpr){
                ConditionExpr expr = (ConditionExpr)param;
                sqlParams.addAll(expr.getParams());
                actParams[index] = expr.getExpression();
            } else if(param instanceof TimeField) {
                actParams[index] = ((TimeField)param).name();
            }else if(param instanceof Collection){
                Collection<?> collection = (Collection<?>) param;
                sqlParams.addAll(collection);
                actParams[index] = collection.stream().map(e->"${param}").collect(Collectors.joining(","));
            }else{
                sqlParams.add(param);
                actParams[index] = "${param}";
            }
            index++;
        }
        return actParams;
    }

    /** 构建函数、算式表达式 */
    public static ArithFuncExpr build(ExprEnum exprEnum, Object ... params) {
        String expression = exprEnum.expr();
        List<Object> sqlParams = new ArrayList<>(10);
        if (params.length > 0) {
            String[] actParams = parseColumn(sqlParams,params);
            if (exprEnum == ExprEnum.CASE_SWITCH || exprEnum == ExprEnum.CASE_CONDITION) {
                int starIndex = 0;
                if (exprEnum == ExprEnum.CASE_SWITCH) {
                    expression = expression.replace("${field}", actParams[0]);
                    starIndex++;
                }
                StringBuilder builder = new StringBuilder();
                String template = "when %s then %s ";
                int endIndex = params.length-1;
                if( (params.length - starIndex)%2 ==1 ){
                    endIndex--;
                }
                for (int i = starIndex; i <= endIndex; i += 2) {
                    builder.append(String.format(template, actParams[i], actParams[i + 1]));
                }
                if(endIndex!=params.length-1){
                    builder.append("else ").append(actParams[params.length - 1]);
                }
                expression = expression.replace("${}", builder);
            }
            else {
                if (exprEnum.count() > 0) {
                    expression = String.format(expression, (Object[])actParams);
                }
                if (expression.contains("${}")) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = exprEnum.count(); i < actParams.length; i++) {
                        builder.append(actParams[i]).append(",");
                    }
                    builder.deleteCharAt(builder.length() - 1);
                    expression = expression.replace("${}", builder);
                }
            }
        }
        return new ArithFuncExpr(expression, sqlParams);
    }

    /** 构建窗口函数表达式 */
    public static ArithFuncExpr buildWindowFunctionExpr(ExprEnum exprEnum,
                                                  List<GroupOrderExpr> partitions,
                                                  List<GroupOrderExpr> orders,
                                                  Object ... params){
        List<Object> sqlParams = new ArrayList<>(10);
        String expression = exprEnum.expr();
        if(exprEnum.count()>0 && params.length>0){
            String[] actParams = parseColumn(sqlParams,params);
            expression = String.format(expression,(Object[])actParams);
        }
        String groupby = "partition by ";
        String orderby = "order by ";
        if(!CollectionUtils.isEmpty(partitions)){
            groupby += partitions.stream().map(GroupOrderExpr::getExpression).collect(Collectors.joining(","));
        }else{
            groupby = "";
        }
        orderby += orders.stream().map(GroupOrderExpr::getExpression).collect(Collectors.joining(","));
        expression = expression.replace("${group}",groupby);
        expression = expression.replace("${order}",orderby);

        return new ArithFuncExpr(expression, sqlParams);
    }

    /** 构建条表达式 */
    public static ConditionExpr buildConditionExpr(SqlExpr sqlExpr, SqlOperator sqlOperator, Object param){
        List<Object> params = new ArrayList<>(10);
        params.addAll(sqlExpr.getParams());

        StringBuilder builder = new StringBuilder(sqlExpr.getQualifyExpr());
        builder.append(" ");

        String strParam = null;
        if(param!=null) {
            if (param instanceof SqlExpr) {
                SqlExpr expr = (SqlExpr) param;
                params.addAll(expr.getParams());
                strParam = expr.getQualifyExpr();
            }else if(param instanceof SqlQuery){
                SqlQuery sqlQuery = (SqlQuery)param;
                params.addAll(sqlQuery.getParams());
                strParam = sqlQuery.getSqlStatement();
            }
        }
        String operator = sqlOperator.operator();
        switch (sqlOperator.dist()){
            case 2:
            case 3:
                if(strParam==null){
                    params.add(param);
                    strParam = "${param}";
                }
                switch (sqlOperator){
                    case SW:
                    case NSW:
                        strParam += "%";
                        break;
                    case EW:
                    case NEW:
                        strParam = "%"+strParam;
                        break;
                    case CT:
                    case NCT:
                        strParam = "%"+strParam+"%";
                        break;
                }
                break;
            case 4:
                if(!(param instanceof Collection)){
                    throw new IllegalArgumentException("in 或 not in 的参数必须是集合");
                }
                Collection<?> collection = (Collection<?>) param;
                params.addAll(collection);
                strParam = collection.stream().map(e->"${param}").collect(Collectors.joining(","));
                break;
            case 5:
                if(!(param instanceof Collection)){
                    throw new IllegalArgumentException("between 或 not between 的参数必须是集合");
                }
                collection = (Collection<?>) param;
                params.addAll(collection);
                strParam = "${param} and ${param}";
        }
        if(sqlOperator.dist()!=1) {
            assert strParam != null;
            builder.append(operator.replace("{}", strParam));
        }else{
            builder.append(operator);
        }
        return new ConditionExpr(builder.toString(),params);
    }

    /** 构建update的赋值set表达式 */
    public static String buildSetExpr(QColumn qColumn, Object param, List<Object> params){
        StringBuilder builder = new StringBuilder(qColumn.getQualifyExpr());
        builder.append("=");
        String strParam;
        if(param instanceof SqlExpr){
            params.addAll(((SqlExpr)param).getParams());
            strParam = ((SqlExpr)param).getQualifyExpr();
        }else{
            params.add(param);
            strParam = "${param}";
        }
        builder.append(strParam);
        return builder.toString();
    }
}