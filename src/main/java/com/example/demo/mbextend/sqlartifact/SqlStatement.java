package com.example.demo.mbextend.sqlartifact;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/18 17:45
 */
public interface SqlStatement {
    String getSqlStatement();
    List<Object> getParams();
}
