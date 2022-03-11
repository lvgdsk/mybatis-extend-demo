package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/28 17:26
 */
@Data
@TableName("employee")
public class Employee {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String empNumber;
    private String leaderNumber;
    private String empName;
    @TableField(exist = false)
    private Integer lvl;
}
