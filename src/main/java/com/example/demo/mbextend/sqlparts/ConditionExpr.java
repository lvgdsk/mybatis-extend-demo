package com.example.demo.mbextend.sqlparts;

import com.example.demo.mbextend.QField;
import com.example.demo.mbextend.enums.ConditionCombineType;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:08
 */
public class ConditionExpr implements SqlExpr{
    private String expression;
    private boolean isNeedBracket;
    private List<Object> params;

    public ConditionExpr(String condition,List<Object> params) {
        this.expression = condition;
        this.params = params;
    }

    // 字符串是否区分大小写
    public ConditionExpr binary(){
        if(this.expression.startsWith("not")){
            this.expression = this.expression.replace("not","not binary");
        }else{
            this.expression = "binary " + this.expression;
        }
        return this;
    }

    public ConditionExpr not(){
        expression = "not "+expression;
        return this;
    }

    public ConditionExpr and(ConditionExpr sqlCondition){
        return combineCondition(ConditionCombineType.AND,sqlCondition);
    }

    public ConditionExpr or(ConditionExpr sqlCondition){
        return combineCondition(ConditionCombineType.OR,sqlCondition);
    }

    public ConditionExpr xor(ConditionExpr sqlCondition){
        return combineCondition(ConditionCombineType.XOR,sqlCondition);
    }

    private ConditionExpr combineCondition(ConditionCombineType combineType, ConditionExpr sqlCondition){
        StringBuilder ConditionBuilder = new StringBuilder();
        String condition1 = this.expression;
        String condition2 = sqlCondition.getExpression();
        if(this.isNeedBracket){
            condition1 = "( " + condition1 + " )";
        }
        if(this.isNeedBracket){
            condition2 = "( " + condition2 + " )";
        }
        String logicSymbol;
        boolean isNeedBracket = true;
        switch (combineType){
            case AND:
                logicSymbol = " and ";
                isNeedBracket = false;
                break;
            case OR:
                logicSymbol = " or ";
                break;
            case XOR:
                logicSymbol = " xor ";
                break;
            default:
                throw new IllegalArgumentException("不支持的条件结合类型");
        }
        List<Object> params = new ArrayList<>(this.params.size()+sqlCondition.getParams().size());
        params.addAll(this.params);
        params.addAll(sqlCondition.getParams());
        ConditionExpr conditionExpr = new ConditionExpr(condition1 + logicSymbol + condition2, params);
        conditionExpr.setNeedBracket(isNeedBracket);
        return conditionExpr;
    }

    @Override
    public String getExpression() {
        return expression;
    }

    public void setNeedBracket(boolean needBracket) {
        isNeedBracket = needBracket;
    }

    @Override
    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    public QField toQueryColumn(String columnAlias){
        return new QField(this.expression,columnAlias,this.params);
    }
}
