package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Employee;
import com.example.demo.mbextend.sqlartifact.SqlQuery;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/28 17:43
 */
public interface EmployeeService extends IService<Employee> {
    List<Employee> select(SqlQuery sqlQuery);
}
