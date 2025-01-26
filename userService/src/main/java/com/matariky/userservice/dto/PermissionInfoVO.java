package com.matariky.userservice.dto;

import com.matariky.userservice.bean.Permission;
import lombok.Data;

@Data
public class PermissionInfoVO extends Permission{


    private String  parentPermissionName;



}
