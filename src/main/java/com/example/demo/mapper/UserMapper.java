package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import com.example.demo.mbextend.BaseSqlBuilder;
import com.example.demo.mbextend.sqlartifact.SqlDelete;
import com.example.demo.mbextend.sqlartifact.SqlQuery;
import com.example.demo.mbextend.sqlartifact.SqlUpdate;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/20 9:42
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @SelectProvider(type = BaseSqlBuilder.class, method = "select")
    @ResultMap("userResultMap")
    List<User> select(@Param("sqlQuery") SqlQuery sqlQuery);

    @UpdateProvider(type = BaseSqlBuilder.class, method = "update")
    @ResultType(Integer.class)
    Integer update(SqlUpdate sqlUpdate);

    @UpdateProvider(type = BaseSqlBuilder.class, method = "delete")
    @ResultType(Integer.class)
    Integer delete(SqlDelete sqlDelete);

    List<User> fildUser(@Param("birthday") Date birthday);

//    @SelectProvider(type = BaseSqlBuilder.class, method = "test")
//    List<User> test(@Param("sqlQuery") SqlQuery sqlQuery);
}