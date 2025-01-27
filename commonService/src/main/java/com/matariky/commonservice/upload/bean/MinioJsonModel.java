package com.matariky.commonservice.upload.bean;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import com.matariky.commonservice.upload.validator.group.MinioGroup;

/**
 * Minio Cloud Storage ConfigurationInformation
 *
 */
@Data
public class MinioJsonModel {

    @NotBlank(message = "{minio.endPoint.require}", groups = MinioGroup.class)
    private String minioEndPoint;

    @NotBlank(message = "{minio.accesskey.require}", groups = MinioGroup.class)
    private String minioAccessKey;

    @NotBlank(message = "{minio.secretkey.require}", groups = MinioGroup.class)
    private String minioSecretKey;

    @NotBlank(message = "{minio.bucketName.require}", groups = MinioGroup.class)
    private String minioBucketName;

    @NotBlank(message = "{minio.prefix.require}", groups = MinioGroup.class)
    private String minioPrefix;

}
