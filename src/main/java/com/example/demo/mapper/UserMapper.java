package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import com.example.demo.mbextend.BaseSqlProvider;
import com.example.demo.mbextend.sqlparts.SqlDelete;
import com.example.demo.mbextend.sqlparts.SqlQuery;
import com.example.demo.mbextend.sqlparts.SqlUpdate;
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

    @SelectProvider(type = BaseSqlProvider.class, method = "select")
    @ResultMap("userResultMap")
    List<User> select(@Param("sqlQuery") SqlQuery sqlQuery);

    @UpdateProvider(type = BaseSqlProvider.class, method = "update")
    @ResultType(Integer.class)
    Integer update(@Param("sqlUpdate")SqlUpdate sqlUpdate);

    @UpdateProvider(type = BaseSqlProvider.class, method = "delete")
    @ResultType(Integer.class)
    Integer delete(@Param("sqlDelete") SqlDelete sqlDelete);

    List<User> fildUser(@Param("birthday") Date birthday);

//    @SelectProvider(type = BaseSqlBuilder.class, method = "test")
//    List<User> test(@Param("sqlQuery") SqlQuery sqlQuery);
}
