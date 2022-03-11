package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Pet;
import com.example.demo.mbextend.BaseSqlProvider;
import com.example.demo.mbextend.sqlparts.SqlQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/21 9:11
 */
@Mapper
public interface PetMapper extends BaseMapper<Pet> {

    @SelectProvider(type = BaseSqlProvider.class, method = "select")
    @ResultMap("petResultMap")
    List<Pet> select(@Param("sqlQuery") SqlQuery sqlQuery);
}
