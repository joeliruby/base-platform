package com.matariky.clickhouse.logs.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.matariky.clickhouse.logs.excel.LogTypeConverter;
import lombok.Data;

import java.io.Serializable;

@Data
public class LogsExcelVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ExcelProperty(value = " Tenant ")
    @ColumnWidth(30)
    private String tenantId;

    @ExcelProperty("application Name")
    @ColumnWidth(30)
    private String appName;


    @ExcelProperty(value = "operate Name")
    @ColumnWidth(30)
    private String operationName;

    @ExcelProperty(value = "Log content")
    @ColumnWidth(30)
    private String message;

    @ExcelProperty(value = " Log Type ",converter = LogTypeConverter.class)
    @ColumnWidth(30)
    private String logType;


    @ExcelProperty(value = "TidEpc")
    @ColumnWidth(30)
    private String tidEpc;


    @ExcelProperty(value = "Operator")
    @ColumnWidth(30)
    private String operator;

    @ExcelProperty(value = " Wether  Error ")
    @ColumnWidth(30)
    private String hasError;


    @ExcelProperty(value = "IP")
    @ColumnWidth(30)
    private String ip;

    @ExcelProperty(value = "MAC Address ")
    @ColumnWidth(30)
    private String mac;

    @ExcelProperty(value = " Log  Storage  Time ")
    @ColumnWidth(30)
    private String timestamp;

}
