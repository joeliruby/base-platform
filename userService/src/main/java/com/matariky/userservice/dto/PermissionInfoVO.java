package com.matariky.userservice.dto;

import com.matariky.userservice.bean.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionInfoVO extends Permission {

    private String parentPermissionName;

}
