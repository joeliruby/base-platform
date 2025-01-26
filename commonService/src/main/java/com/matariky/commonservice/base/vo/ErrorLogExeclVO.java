package com.matariky.commonservice.base.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.matariky.commonservice.error.excel.DeviceTypeConvert;
import com.matariky.commonservice.error.excel.ErrorLevelConverter;
import com.matariky.commonservice.error.excel.LongTimeConverterTime;
import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorLogExeclVO implements Serializable {


    @ExcelProperty(value = "业务 Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long businessTime;

    @ExcelProperty("版本号")
    @ColumnWidth(30)
    private String systemVersionNumber;


    @ExcelProperty(value = "访问终端", converter = DeviceTypeConvert.class)
    @ColumnWidth(30)
    private String deviceType;


    @ExcelProperty(value = "访问模块")
    @ColumnWidth(30)
    private String businessModule;


    @ExcelProperty("访问系统地址")
    @ColumnWidth(30)
    private String url;

    @ExcelProperty("访问接口名")
    @ColumnWidth(30)
    private String apiName;

    @ExcelProperty("请求 Parameter ")
    @ColumnWidth(30)
    private String param;

    @ExcelProperty("错误内容")
    @ColumnWidth(30)
    private String errorContent;

    @ExcelProperty(value = "错误等级", converter = ErrorLevelConverter.class)
    @ColumnWidth(30)
    private Integer errorLevel;

    @ExcelProperty("访问账号")
    @ColumnWidth(30)
    private String accessAccount;

    @ExcelProperty("外网ip(访问人)")
    @ColumnWidth(30)
    private String serverIp;

    @ExcelProperty("登录地点")
    @ColumnWidth(30)
    private String physicalAddress;

    @ExcelProperty(value = "日志存储 Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long createTime;


}
