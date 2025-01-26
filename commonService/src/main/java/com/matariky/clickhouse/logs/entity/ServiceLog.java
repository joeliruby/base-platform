package com.matariky.clickhouse.logs.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("signoz_tracers.distributed_signoz_index_v2")
@ApiModel(value = "ServiceTracers", description = "serviceTracers")
public class ServiceLog {
	private String timestamp;
	private String errorMessage;
	private String serviceName;
	private String name; //Operation Command
	private Integer kind; //0 Unspecified, 1 Internal, 2 Server, 3 Client, 4 Message Producer, 5 Message Consumer
	private BigDecimal durationNano; //Duration
	private String dbSystem; // Database System (mysql, redis, clickhouse, taos, initially manual input)
	private String dbName; // Database Name
	private String dbOperation; // Database Operation Name
	private String httpMethod; //HTTP Method
	private String httpCode; //HTTP Status Code
	private String httpRoute; //HTTP Relative Address
	private String os; //Operating System
	private String host; //Host Name
	private String dbtype; // Database Type
	private String dbop; // Database Operation
	private String telemetryLanugage; //Development Language
	private String peerDomain; //Domain Name
	private String peerPort; //Port Number
	private String dbStatements; //Execution Statements
	private String otlSdk; //Open Telemetry SDK
	private String connectionString; // Connection String
	private String clientIp; //Client IP
	private String dbTable; // Database Table Name
	private Boolean hasError;

	private String timeStart;
	private String timeEnd;

	private Long timeStartLong;
	private Long timeEndLong;

	private Integer perPage;
	private Integer offSet;
}
