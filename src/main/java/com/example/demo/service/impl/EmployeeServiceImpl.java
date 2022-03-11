package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Employee;
import com.example.demo.mbextend.sqlartifact.SqlQuery;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/28 17:44
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }


    @Override
    public List<Employee> select(SqlQuery sqlQuery) {
        return employeeMapper.select(sqlQuery);
    }
}
