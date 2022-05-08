package com.example.demo.mbextend;

import java.util.List;

/**
 * 代表一个group项
 * @author lvqi
 * @version 1.0.0
 * @since 2022/4/29 17:02
 */
public class GroupOrderExpr {
    private final String expression;
    private final List<Object> params;

    GroupOrderExpr(String expression, List<Object> params) {
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
