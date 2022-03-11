package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/20 9:45
 */
@Data
@ToString
public class Pet {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Long uid;
    private String species;
}
