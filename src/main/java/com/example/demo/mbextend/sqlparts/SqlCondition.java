package com.example.demo.mbextend.sqlparts;

import com.example.demo.mbextend.enums.ConditionCombineType;
import lombok.Data;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:08
 */
@Data
public class SqlCondition implements SqlField{
    private String condition;
    private String columnAlias;
    // 条件取反
    private boolean not = false;

    private List<Object> params;

    public SqlCondition(String condition,List<Object> params) {
        this.condition = condition;
        this.params = params;
    }

    // 字符串是否区分大小写
    public SqlCondition binary(){
        this.condition = "binary " + this.condition;
        return this;
    }

    public SqlCondition not(){
        this.not = true;
        return this;
    }

    @Override
    public String getColumn() {
        return condition;
    }

    @Override
    public String getQualifyField() {
        return condition;
    }

    public SqlCondition and(SqlCondition ... sqlConditions){
        combineCondition(ConditionCombineType.AND,sqlConditions);
        return this;
    }

    public SqlCondition or(SqlCondition ... sqlConditions){
        combineCondition(ConditionCombineType.OR,sqlConditions);
        return this;
    }

    public SqlCondition xor(SqlCondition ... sqlConditions){
        combineCondition(ConditionCombineType.XOR,sqlConditions);
        return this;
    }

    private void combineCondition(ConditionCombineType combineType, SqlCondition ... sqlConditions){

        if(this.condition.startsWith("(")){
            this.condition = this.condition.substring(1,this.condition.length()-1);
        }

        this.condition = "( " + this.condition;
        StringBuilder conditions = new StringBuilder();
        switch (combineType){
            case AND:
                conditions.append(" and ");
                break;
            case OR:
                conditions.append(" or ");
                break;
            case XOR:
                conditions.append(" xor ");
        }
        if(sqlConditions.length>1){
            conditions.append(" ( ");
        }
        conditions.append(
                Arrays.stream(sqlConditions)
                        .map(sc->{
                            this.params.addAll(sc.getParams());
                            String expr = sc.getCondition();
                            if(sc.isNot()){
                                expr = "not " + expr;
                            }
                            return expr;
                        }).collect(Collectors.joining(" and ")));
        if(sqlConditions.length>1){
            conditions.append(" )");
        }
        this.condition = this.condition + conditions + " )";
    }
}
