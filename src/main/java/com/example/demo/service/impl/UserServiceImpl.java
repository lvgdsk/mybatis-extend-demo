package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mbextend.sqlparts.SqlDelete;
import com.example.demo.mbextend.sqlparts.SqlQuery;
import com.example.demo.mbextend.sqlparts.SqlUpdate;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/20 9:57
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> select(SqlQuery sqlQuery) {
        return userMapper.select(sqlQuery);
    }

    @Override
    public Integer update(SqlUpdate sqlUpdate) {
        return userMapper.update(sqlUpdate);
    }

    @Override
    public Integer delete(SqlDelete sqlDelete) {
        return userMapper.delete(sqlDelete);
    }
}
