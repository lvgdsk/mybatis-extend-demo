package com.example.demo.mbextend.sqlparts;

import com.example.demo.mbextend.enums.SqlOperator;

import java.util.Arrays;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 10:56
 */

public interface SqlField {
    /** 获取表字段名 */
    String getColumn();
    /** 设置表字段别名 */
    void setColumnAlias(String alias);
    /** 获取表字段别名 */
    String getColumnAlias();
    /** 获取限定表字段名,形式为:[表别名_表字段名] */
    String getQualifyField();
    /** 获取表别名 */
    String getTableAlias();
    List<Object> getParams();

    default SqlCondition isNull(){
        return SqlExprBuilder.buildCompareExpr(this, SqlOperator.ISN,null);
    }

    default SqlCondition isNotNull(){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.ISNN,null);
    }

    default SqlCondition eq(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.EQ,param);
    }

    default SqlCondition nq(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.NQ,param);
    }

    default SqlCondition gt(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.GT,param);
    }

    default SqlCondition lt(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.LT,param);
    }

    default SqlCondition ge(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.GE,param);
    }

    default SqlCondition le(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.LE,param);
    }

    default SqlCondition in(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.IN,param);
    }

    default SqlCondition notIn(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.NIN,param);
    }

    default SqlCondition exists(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.ES,param);
    }

    default SqlCondition notExists(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.NES,param);
    }

    default SqlCondition between(Object begin,Object end){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.BT, Arrays.asList(begin,end));
    }

    default SqlCondition notBetween(Object begin,Object end){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.NBT,Arrays.asList(begin,end));
    }

    default SqlCondition startWith(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.SW,param);
    }

    default SqlCondition notStartWith(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.NSW,param);
    }

    default SqlCondition endWith(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.EW,param);
    }

    default SqlCondition notEndWith(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.NEW,param);
    }

    default SqlCondition contain(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.CT,param);
    }

    default SqlCondition notContain(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.NCT,param);
    }

    default SqlCondition like(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.LK,param);
    }

    default SqlCondition notLike(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.NLK,param);
    }

    default SqlCondition regexp(Object param){
        return SqlExprBuilder.buildCompareExpr(this,SqlOperator.REG,param);
    }

    default OrderItem orderAsc(){
        return new OrderItem(this,true);
    }

    default OrderItem orderDesc(){
        return new OrderItem(this,false);
    }

    default GroupItem groupAsc(){
        return new GroupItem(this,true);
    }

    default GroupItem groupDesc(){
        return new GroupItem(this,false);
    }
}
