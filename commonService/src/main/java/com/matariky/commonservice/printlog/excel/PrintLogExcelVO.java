package com.matariky.commonservice.printlog.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.matariky.commonservice.error.excel.LongTimeConverterTime;
import lombok.Data;

/**
 * Automatically generated entity class
 *
 * @author AUTOMATION
 */
@Data
public class PrintLogExcelVO {

    @ExcelProperty(value = "  System Version")
    @ColumnWidth(30)
    private String systemVersionNumber;

    @ExcelProperty(value = " Business  Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long businessTime;

    @ExcelProperty(value = " Print  Task   Code ")
    @ColumnWidth(30)
    private String printTaskNumber;

    @ExcelProperty(value = " Print   Detail ID")
    @ColumnWidth(30)
    private String printDetailId;

    @ExcelProperty(value = " Print  Status ", converter = PrintStatusConverter.class)
    @ColumnWidth(30)
    private Integer printStatus;

    @ExcelProperty(value = "SKU")
    @ColumnWidth(30)
    private String sku;

    @ExcelProperty(value = " Print   Content")
    @ColumnWidth(30)
    private String printContent;

    @ExcelProperty(value = " Returned TID")
    @ColumnWidth(30)
    private String returnTid;

    @ExcelProperty(value = "RFID  Configuration")
    @ColumnWidth(30)
    private String rfidSetting;

    @ExcelProperty(value = " Device  Code ")
    @ColumnWidth(30)
    private String deviceCode;

    @ExcelProperty(value = "MAC Address ")
    @ColumnWidth(30)
    private String macAddress;

    @ExcelProperty(value = "  Access account")
    @ColumnWidth(30)
    private String accessAccount;

    @ExcelProperty(value = "   Server IP Address ")
    @ColumnWidth(30)
    private String serverIp;

    @ExcelProperty(value = " Log  Storage  Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long createTime;

}
