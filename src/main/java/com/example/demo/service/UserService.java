package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;
import com.example.demo.mbextend.sqlparts.SqlDelete;
import com.example.demo.mbextend.sqlparts.SqlQuery;
import com.example.demo.mbextend.sqlparts.SqlUpdate;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/20 9:56
 */
public interface UserService extends IService<User> {
    List<User> select(SqlQuery sqlQuery);
    Integer update(SqlUpdate sqlUpdate);
    Integer delete(SqlDelete sqlDelete);
}
