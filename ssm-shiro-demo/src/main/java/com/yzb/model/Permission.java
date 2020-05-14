package com.yzb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    private Integer id;
    private String permissionName;
    private String description;
    private Integer status = 0;
}
