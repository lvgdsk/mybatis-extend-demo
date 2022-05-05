package com.example.demo.mbextend.sqlparts;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/5/5 9:23
 */
public interface Expression {
    String getExpression();
    List<Object> getParams();
}
