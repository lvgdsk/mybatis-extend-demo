package com.example.demo.mbextend;

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
    String getColumnAlias();
    String getQualifyExpr();

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
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NE,param);
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

    default ConditionExpr between(Object begin,Object end){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.BT, Arrays.asList(begin,end));
    }

    default ConditionExpr notBetween(Object begin,Object end){
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NBT,Arrays.asList(begin,end));
    }

    default ConditionExpr startWith(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.SW,param);
    }

    default ConditionExpr notStartWith(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NSW,param);
    }

    default ConditionExpr endWith(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.EW,param);
    }

    default ConditionExpr notEndWith(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NEW,param);
    }

    default ConditionExpr contain(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.CT,param);
    }

    default ConditionExpr notContain(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NCT,param);
    }

    default ConditionExpr like(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.LK,param);
    }

    default ConditionExpr notLike(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.NLK,param);
    }

    default ConditionExpr regexp(Object param){
        checkLikeParam(param);
        return SqlExprBuilder.buildConditionExpr(this,SqlOperator.REG,param);
    }

    default GroupOrderExpr orderAsc(){
        return new GroupOrderExpr(getQualifyExpr(),getParams());
    }

    default GroupOrderExpr orderDesc(){
        return new GroupOrderExpr(getQualifyExpr()+ " desc",getParams());
    }

    default GroupOrderExpr groupAsc(){
        return new GroupOrderExpr(getQualifyExpr(),getParams());
    }

    default GroupOrderExpr groupDesc(){
        return new GroupOrderExpr(getQualifyExpr()+ " desc",getParams());
    }

    default void checkLikeParam(Object param){
        if(!(param instanceof QColumn || param instanceof String)){
            throw new IllegalArgumentException("不支持的参数类型");
        }
    }
}
