package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Pet;
import com.example.demo.mbextend.sqlparts.SqlQuery;
import com.example.demo.mapper.PetMapper;
import com.example.demo.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/9 9:05
 */
@Service
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements PetService {
    private final PetMapper petMapper;

    public PetServiceImpl(PetMapper petMapper) {
        this.petMapper = petMapper;
    }


    @Override
    public List<Pet> select(SqlQuery sqlQuery) {
        return petMapper.select(sqlQuery);
    }
}
