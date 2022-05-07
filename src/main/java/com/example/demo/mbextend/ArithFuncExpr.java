package com.example.demo.mbextend;

import java.util.List;

/**
 * 算术函数表达式
 * @author lvqi
 * @version 1.0.0
 * @since 2022/4/29 10:29
 */
public class ArithFuncExpr implements SqlExpr{
    private final String expression;
    private final List<Object> params;
    private String columnAlias;

    public ArithFuncExpr(String expression, List<Object> params) {
        this.expression = expression;
        this.params = params;
    }

    @Override
    public String getExpression() {
        return expression;
    }

    @Override
    public List<Object> getParams() {
        return params;
    }

    public String getColumnAlias() {
        return columnAlias;
    }

    @Override
    public String getQualifyExpr() {
        return expression;
    }

    public ArithFuncExpr column(QColumn qColumn){
        columnAlias = qColumn.getColumnAlias()!=null?qColumn.getColumnAlias():qColumn.getColumnName();
        return this;
    }
}
