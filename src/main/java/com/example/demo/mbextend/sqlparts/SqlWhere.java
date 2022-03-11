package com.example.demo.mbextend.sqlparts;

import com.example.demo.mbextend.enums.ConditionCombineType;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:08
 */
@Data
public class SqlWhere {
    private List<SqlCondition> sqlCondition;
    private List<SqlWhere> sqlWheres;
    // where子句逻辑连接符：与、或、异或
    private ConditionCombineType combineType;
    // 是否取反
    private boolean not = false;

    public static SqlWhere custom(SqlCondition ... sqlCondition){
        SqlWhere sqlWhere = new SqlWhere();
        sqlWhere.setSqlCondition(Arrays.asList(sqlCondition));
        return sqlWhere;
    }

    // 逻辑非
    public SqlWhere not(){
        this.not = true;
        return this;
    }

    // 逻辑与
    public SqlWhere and(SqlWhere sqlWhere){
        if(sqlWheres == null){
            sqlWheres = new ArrayList<>(4);
        }
        sqlWhere.setCombineType(ConditionCombineType.AND);
        this.sqlWheres.add(sqlWhere);
        return this;
    }

    // 逻辑或
    public SqlWhere or(SqlWhere sqlWhere){
        if(sqlWheres == null){
            sqlWheres = new ArrayList<>(4);
        }
        sqlWhere.setCombineType(ConditionCombineType.OR);
        this.sqlWheres.add(sqlWhere);
        return this;
    }

    // 逻辑异或
    public SqlWhere xor(SqlWhere sqlWhere){
        if(sqlWheres == null){
            sqlWheres = new ArrayList<>(4);
        }
        sqlWhere.setCombineType(ConditionCombineType.XOR);
        this.sqlWheres.add(sqlWhere);
        return this;
    }

    public void addSqlWhere(SqlWhere sqlWhere, ConditionCombineType conbineType){
        switch (conbineType){
            case AND:
                and(sqlWhere);
                break;
            case OR:
                or(sqlWhere);
                break;
            case XOR:
                xor(sqlWhere);
                break;
        }
    }
}
