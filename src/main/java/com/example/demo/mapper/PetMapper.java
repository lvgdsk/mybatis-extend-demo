package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Pet;
import com.example.demo.mbextend.BaseSqlBuilder;
import com.example.demo.mbextend.sqlartifact.SqlQuery;
import org.apache.ibatis.annotations.Mapper;
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

    @SelectProvider(type = BaseSqlBuilder.class, method = "select")
    @ResultMap("petResultMap")
    List<Pet> select(SqlQuery sqlQuery);
}