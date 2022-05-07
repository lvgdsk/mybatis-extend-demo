package com.example.demo.mapper;

import com.example.demo.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.mbextend.BaseSqlProvider;
import com.example.demo.mbextend.SqlDelete;
import com.example.demo.mbextend.SqlQuery;
import com.example.demo.mbextend.SqlUpdate;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lvqi
 * @since 2022-05-05
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    @SelectProvider(type = BaseSqlProvider.class, method = "select")
    @ResultMap("BaseResultMap")
    List<Product> select(@Param("sqlQuery") SqlQuery sqlQuery);

    @UpdateProvider(type = BaseSqlProvider.class, method = "update")
    @ResultType(Integer.class)
    Integer update(@Param("sqlUpdate") SqlUpdate sqlUpdate);

    @UpdateProvider(type = BaseSqlProvider.class, method = "delete")
    @ResultType(Integer.class)
    Integer delete(@Param("sqlDelete") SqlDelete sqlDelete);
}
