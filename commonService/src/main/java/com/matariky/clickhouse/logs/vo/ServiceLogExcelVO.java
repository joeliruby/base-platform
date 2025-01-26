package com.matariky.clickhouse.logs.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.matariky.clickhouse.logs.excel.LogKindConverter;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceLogExcelVO {

	@ExcelProperty(value = "Service Name")
	@ColumnWidth(30)
	private String serviceName;

	@ExcelProperty(value = "Log Information")
	@ColumnWidth(30)
	private String errorMessage;

	@ExcelProperty(value = "Operation Command")
	@ColumnWidth(30)
	private String name;

	@ExcelProperty(value = "Log Type", converter = LogKindConverter.class)
	@ColumnWidth(30)
	private Integer kind;

	@ExcelProperty(value = "Duration (s)")
	@ColumnWidth(30)
	private BigDecimal durationNano;

	@ExcelProperty(value = "Whether Error")
	@ColumnWidth(30)
	private String hasError;

	@ExcelProperty(value = "Database Name")
	@ColumnWidth(30)
	private String dbName;

	@ExcelProperty(value = "Database Operation Name")
	@ColumnWidth(30)
	private String dbOperation;

	@ExcelProperty(value = "Database Type")
	@ColumnWidth(30)
	private String dbtype;

	@ExcelProperty(value = "Database Operation")
	@ColumnWidth(30)
	private String dbop;

	@ExcelProperty(value = "Database Table Name")
	@ColumnWidth(30)
	private String dbTable;

	@ExcelProperty(value = "HTTP Method")
	@ColumnWidth(30)
	private String httpMethod;

	@ExcelProperty(value = "HTTP Status Code")
	@ColumnWidth(30)
	private String httpCode;

	@ExcelProperty(value = "HTTP Route")
	@ColumnWidth(30)
	private String httpRoute;

	@ExcelProperty(value = "Operating System")
	@ColumnWidth(30)
	private String os;

	@ExcelProperty(value = "Host Name")
	@ColumnWidth(30)
	private String host;

	@ExcelProperty(value = "Development Language")
	@ColumnWidth(30)
	private String telemetryLanugage;

	@ExcelProperty(value = "Domain Name")
	@ColumnWidth(30)
	private String peerDomain;

	@ExcelProperty(value = "Port Number")
	@ColumnWidth(30)
	private String peerPort;

	@ExcelProperty(value = "Execution Statements")
	@ColumnWidth(30)
	private String dbStatements;

	@ExcelProperty(value = "Open Telemetry SDK")
	@ColumnWidth(30)
	private String otlSdk;

	@ExcelProperty(value = "Connection String")
	@ColumnWidth(30)
	private String connectionString;

	@ExcelProperty(value = "Client IP")
	@ColumnWidth(30)
	private String clientIp;

	@ExcelProperty(value = "Log Storage Time")
	@ColumnWidth(30)
    private String timestamp;

}
