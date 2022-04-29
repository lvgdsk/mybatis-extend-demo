package com.example.demo.mbextend.sqlparts;

import com.example.demo.mbextend.enums.SqlOperator;

import java.util.Arrays;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:10
 */
public interface SqlExpr{
    String getExpression();
    List<Object> getParams();

    default ConditionExpr isNull(){
        return SqlExprBuilder.buildConditionExpr(this, SqlOperator.ISN,null);
    }

    default ConditionExpr isNotNull(){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.ISNN,null);
    }

    default ConditionExpr eq(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.EQ,param);
    }

    default ConditionExpr nq(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NQ,param);
    }

    default ConditionExpr gt(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.GT,param);
    }

    default ConditionExpr lt(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.LT,param);
    }

    default ConditionExpr ge(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.GE,param);
    }

    default ConditionExpr le(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.LE,param);
    }

    default ConditionExpr in(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.IN,param);
    }

    default ConditionExpr notIn(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NIN,param);
    }

    default ConditionExpr exists(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.ES,param);
    }

    default ConditionExpr notExists(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NES,param);
    }

    default ConditionExpr between(Object begin,Object end){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.BT, Arrays.asList(begin,end));
    }

    default ConditionExpr notBetween(Object begin,Object end){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NBT,Arrays.asList(begin,end));
    }

    default ConditionExpr startWith(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.SW,param);
    }

    default ConditionExpr notStartWith(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NSW,param);
    }

    default ConditionExpr endWith(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.EW,param);
    }

    default ConditionExpr notEndWith(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NEW,param);
    }

    default ConditionExpr contain(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.CT,param);
    }

    default ConditionExpr notContain(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NCT,param);
    }

    default ConditionExpr like(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.LK,param);
    }

    default ConditionExpr notLike(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NLK,param);
    }

    default ConditionExpr regexp(Object param){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.REG,param);
    }

    default OrderExpr orderAsc(){
        return new OrderExpr(getExpression(),getParams());
    }

    default OrderExpr orderDesc(){
        return new OrderExpr(getExpression()+ " desc",getParams());
    }

    default GroupExpr groupAsc(){
        return new GroupExpr(getExpression(),getParams());
    }

    default GroupExpr groupDesc(){
        return new GroupExpr(getExpression()+ " desc",getParams());
    }
}
