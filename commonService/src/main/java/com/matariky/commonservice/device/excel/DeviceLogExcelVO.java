package com.matariky.commonservice.device.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.matariky.commonservice.error.excel.DeviceTypeConvert;
import com.matariky.commonservice.error.excel.LongTimeConverterTime;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
public class DeviceLogExcelVO {

    @ExcelProperty(value = "业务 Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long businessTime;

    @ExcelProperty(value = "系统版本")
    @ColumnWidth(30)
    private String systemVersionNumber;

    @ExcelProperty(value = "访问终端", converter = DeviceTypeConvert.class)
    @ColumnWidth(30)
    private String deviceType;

    @ExcelProperty(value = "访问系统地址")
    @ColumnWidth(30)
    private String url;

    @ExcelProperty(value = "访问接口名")
    @ColumnWidth(30)
    private String apiName;

    @ExcelProperty(value = "访问模块")
    @ColumnWidth(30)
    private String businessModule;

    @ExcelProperty(value = "光电触发 Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long triggerTime;

    @ExcelProperty(value = " Device 编码")
    @ColumnWidth(30)
    private String deviceCode;

    @ExcelProperty(value = "MAC地址")
    @ColumnWidth(30)
    private String macAddress;

    @ExcelProperty(value = " Device 读写器功率")
    @ColumnWidth(30)
    private BigDecimal power;

    @ExcelProperty(value = " Device 采集 Information ")
    @ColumnWidth(30)
    private String collectContent;

    @ExcelProperty(value = "访问账号")
    @ColumnWidth(30)
    private String accessAccount;

    @ExcelProperty(value = "外网IP（访问人）")
    @ColumnWidth(30)
    private String serverIp;

    @ExcelProperty(value = "登录地点")
    @ColumnWidth(30)
    private String physicalAddress;

    @ExcelProperty(value = "日志存储 Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long createTime;


}
