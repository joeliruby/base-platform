package com.matariky.commonservice.printlog.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.matariky.commonservice.error.excel.DeviceTypeConvert;
import com.matariky.commonservice.error.excel.LongTimeConverterTime;
import lombok.Data;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
public class PrintLogExcelVO {

    @ExcelProperty(value = "系统版本")
    @ColumnWidth(30)
    private String systemVersionNumber;

    @ExcelProperty(value = "业务 Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long businessTime;

    @ExcelProperty(value = " Print  Task 编号")
    @ColumnWidth(30)
    private String printTaskNumber;

    @ExcelProperty(value = " Print 详情ID")
    @ColumnWidth(30)
    private String printDetailId;

    @ExcelProperty(value = " Print  Status ", converter = PrintStatusConverter.class)
    @ColumnWidth(30)
    private Integer printStatus;

    @ExcelProperty(value = "商品SKU")
    @ColumnWidth(30)
    private String sku;

    @ExcelProperty(value = " Print 内容")
    @ColumnWidth(30)
    private String printContent;

    @ExcelProperty(value = "返回TID")
    @ColumnWidth(30)
    private String returnTid;

    @ExcelProperty(value = "RFID设置")
    @ColumnWidth(30)
    private String rfidSetting;

    @ExcelProperty(value = " Device 编码")
    @ColumnWidth(30)
    private String deviceCode;

    @ExcelProperty(value = "MAC地址")
    @ColumnWidth(30)
    private String macAddress;

    @ExcelProperty(value = "访问账号")
    @ColumnWidth(30)
    private String accessAccount;

    @ExcelProperty(value = "服务器访问地址")
    @ColumnWidth(30)
    private  String  serverIp;

    @ExcelProperty(value = "日志存储 Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long createTime;


}
