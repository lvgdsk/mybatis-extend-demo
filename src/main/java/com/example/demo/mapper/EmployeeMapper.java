package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Employee;
import com.example.demo.mbextend.BaseSqlBuilder;
import com.example.demo.mbextend.sqlartifact.SqlQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/28 17:31
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    @SelectProvider(type = BaseSqlBuilder.class, method = "select")
    @ResultMap("employeeResultMap")
    List<Employee> select(SqlQuery sqlQuery);
}
