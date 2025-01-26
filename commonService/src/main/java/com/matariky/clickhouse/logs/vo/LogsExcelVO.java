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

    @ExcelProperty("应用 Name")
    @ColumnWidth(30)
    private String appName;


    @ExcelProperty(value = "操作 Name")
    @ColumnWidth(30)
    private String operationName;

    @ExcelProperty(value = "日志内容")
    @ColumnWidth(30)
    private String message;

    @ExcelProperty(value = "日志Type ",converter = LogTypeConverter.class)
    @ColumnWidth(30)
    private String logType;


    @ExcelProperty(value = "TidEpc")
    @ColumnWidth(30)
    private String tidEpc;


    @ExcelProperty(value = "操作人")
    @ColumnWidth(30)
    private String operator;

    @ExcelProperty(value = " Wether 错误")
    @ColumnWidth(30)
    private String hasError;


    @ExcelProperty(value = "IP")
    @ColumnWidth(30)
    private String ip;

    @ExcelProperty(value = "MAC地址")
    @ColumnWidth(30)
    private String mac;

    @ExcelProperty(value = "日志存储 Time ")
    @ColumnWidth(30)
    private String timestamp;

}
