package com.example.demo.mbextend.sqlparts;

import lombok.Data;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:08
 */
@Data
public class SqlCondition implements SqlField{
    // 字符串是否区分大小写
    private Boolean binary = false;
    private String condition;
    private String alias;

    private List<Object> params;

    public SqlCondition(String condition,List<Object> params) {
        this.condition = condition;
        this.params = params;
    }

    public SqlCondition binary(){
        this.binary = true;
        return this;
    }

    @Override
    public String getColumn() {
        return condition;
    }

    @Override
    public void setColumnAlias(String alias) {

    }

    @Override
    public String getColumnAlias() {
        return null;
    }

    @Override
    public String getQualifyField() {
        if(binary) {
            return "binary "+condition;
        }else{
            return condition;
        }
    }

    @Override
    public String getTableAlias() {
        return null;
    }
}
