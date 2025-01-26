package com.matariky.commonservice.network.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.matariky.commonservice.error.excel.DeviceTypeConvert;
import com.matariky.commonservice.error.excel.LongTimeConverterTime;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
public class NetworkLogExcelVO {

    @ExcelProperty(value = "业务 Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long businessTime;

    @ExcelProperty(value = "访问终端", converter = DeviceTypeConvert.class)
    @ColumnWidth(30)
    private String deviceType;

    @ExcelProperty(value = "访问模块")
    @ColumnWidth(30)
    private String businessModule;

    @ExcelProperty(value = "网络信号")
    @ColumnWidth(30)
    private Integer signalLevel;

    @ExcelProperty(value = "访问账号")
    @ColumnWidth(30)
    private String accessAccount;


    @ExcelProperty(value = "日志存储 Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long createTime;


}
