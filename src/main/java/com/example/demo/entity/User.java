package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/1/20 9:44
 */
@Data
@ToString
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Date birthday;
    @TableField(exist = false)
    private List<Pet> pets;
}
