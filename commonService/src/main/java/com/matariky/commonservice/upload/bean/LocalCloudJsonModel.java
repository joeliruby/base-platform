package com.matariky.commonservice.upload.bean;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.Data;
import com.matariky.commonservice.upload.validator.group.LocalGroup;

/**
  *本地存储配置 Information 
 *
 */
@Data
public class LocalCloudJsonModel {
	
    @NotBlank(message="{local.domain.require}", groups = LocalGroup.class)
    @URL(message = "{local.domain.url}", groups = LocalGroup.class)
    private String localDomain;

    private String localPrefix;

    @NotBlank(message="{local.path.url}", groups = LocalGroup.class)
    private String localPath;

}
