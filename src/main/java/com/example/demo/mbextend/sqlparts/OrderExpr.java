package com.example.demo.mbextend.sqlparts;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/4/29 16:54
 */
public class OrderExpr {
    private String expression;
    private List<Object> params;

    public OrderExpr(String expression, List<Object> params) {
        this.expression = expression;
        this.params = params;
    }
    public String getExpression() {
        return expression;
    }
    public List<Object> getParams() {
        return params;
    }
}
