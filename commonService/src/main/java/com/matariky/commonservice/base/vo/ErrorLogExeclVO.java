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

    @ExcelProperty(value = " Business  Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long businessTime;

    @ExcelProperty(" Version Number")
    @ColumnWidth(30)
    private String systemVersionNumber;

    @ExcelProperty(value = " Access Terminal ", converter = DeviceTypeConvert.class)
    @ColumnWidth(30)
    private String deviceType;

    @ExcelProperty(value = " Business Module ")
    @ColumnWidth(30)
    private String businessModule;

    @ExcelProperty(" Access URL  Address ")
    @ColumnWidth(30)
    private String url;

    @ExcelProperty(" API Name ")
    @ColumnWidth(30)
    private String apiName;

    @ExcelProperty(" Request  Parameter ")
    @ColumnWidth(30)
    private String param;

    @ExcelProperty(" Error   Content")
    @ColumnWidth(30)
    private String errorContent;

    @ExcelProperty(value = " Error   Level ", converter = ErrorLevelConverter.class)
    @ColumnWidth(30)
    private Integer errorLevel;

    @ExcelProperty("  Access account")
    @ColumnWidth(30)
    private String accessAccount;

    @ExcelProperty(" Access IP")
    @ColumnWidth(30)
    private String serverIp;

    @ExcelProperty(" Access Physical Address")
    @ColumnWidth(30)
    private String physicalAddress;

    @ExcelProperty(value = " Log  Storage  Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long createTime;

}
