package com.example.demo.mbextend.sqlparts;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:09
 */
@Data
@AllArgsConstructor
public class OrderItem {
    private SqlField sqlField;
    private boolean asc;
}
