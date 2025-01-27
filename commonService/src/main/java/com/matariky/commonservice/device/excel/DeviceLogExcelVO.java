package com.matariky.commonservice.device.excel;

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
public class DeviceLogExcelVO {

    @ExcelProperty(value = " Business  Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long businessTime;

    @ExcelProperty(value = "  System Version")
    @ColumnWidth(30)
    private String systemVersionNumber;

    @ExcelProperty(value = " Access Terminal ", converter = DeviceTypeConvert.class)
    @ColumnWidth(30)
    private String deviceType;

    @ExcelProperty(value = " Access URL  Address ")
    @ColumnWidth(30)
    private String url;

    @ExcelProperty(value = " API Name ")
    @ColumnWidth(30)
    private String apiName;

    @ExcelProperty(value = " Business Module ")
    @ColumnWidth(30)
    private String businessModule;

    @ExcelProperty(value = "光电触发 Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long triggerTime;

    @ExcelProperty(value = " Device  Code ")
    @ColumnWidth(30)
    private String deviceCode;

    @ExcelProperty(value = "MAC Address ")
    @ColumnWidth(30)
    private String macAddress;

    @ExcelProperty(value = " Device   Reader  Power ")
    @ColumnWidth(30)
    private BigDecimal power;

    @ExcelProperty(value = " Device 采集 Information ")
    @ColumnWidth(30)
    private String collectContent;

    @ExcelProperty(value = "  Access account")
    @ColumnWidth(30)
    private String accessAccount;

    @ExcelProperty(value = " Public Network IP（访问人）")
    @ColumnWidth(30)
    private String serverIp;

    @ExcelProperty(value = " Access Physical Address")
    @ColumnWidth(30)
    private String physicalAddress;

    @ExcelProperty(value = " Log  Storage  Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long createTime;

}
