package com.example.demo.mbextend.sqlparts;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/24 16:06
 */
@Data
@AllArgsConstructor
public class GroupItem {
    private SqlField sqlField;
    private boolean asc;
}
