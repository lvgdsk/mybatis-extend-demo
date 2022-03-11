package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Pet;
import com.example.demo.mbextend.sqlartifact.SqlQuery;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/9 9:05
 */
public interface PetService  extends IService<Pet> {
    List<Pet> select(SqlQuery sqlQuery);
}
