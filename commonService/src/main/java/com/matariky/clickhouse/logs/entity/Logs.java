package com.matariky.clickhouse.logs.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("signoz_tracers.distributed_signoz_index_v2")
@ApiModel(value = "Tracers", description = "tracers")
public class Logs implements Serializable {


    private static final long serialVersionUID = 1L;
    private String tenantName;
    private String tenantId;
    private String appName;
    private String operationName;
    private String message;
    private String logType;
    private String tidEpc;
    private String operator;
    private String hasError;
    private String ip;
    private String mac;
    private String timestamp;
    private String timeStart;
    private String timeEnd;
    
    private String deviceType;
    private String deviceCode;

    private Long timeStartLong;
    private Long timeEndLong;
    
    private Integer perPage;
    private Integer offSet; 


}
