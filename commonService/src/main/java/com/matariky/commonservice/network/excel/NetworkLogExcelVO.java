package com.matariky.commonservice.network.excel;

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
public class NetworkLogExcelVO {

    @ExcelProperty(value = " Business  Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long businessTime;

    @ExcelProperty(value = " Access Terminal ", converter = DeviceTypeConvert.class)
    @ColumnWidth(30)
    private String deviceType;

    @ExcelProperty(value = " Business Module ")
    @ColumnWidth(30)
    private String businessModule;

    @ExcelProperty(value = " Network信号")
    @ColumnWidth(30)
    private Integer signalLevel;

    @ExcelProperty(value = "  Access account")
    @ColumnWidth(30)
    private String accessAccount;

    @ExcelProperty(value = " Log  Storage  Time ", converter = LongTimeConverterTime.class)
    @ColumnWidth(30)
    private Long createTime;

}
