package com.matariky.commonservice.base.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.matariky.excel.DeviceDbmOptionServiceImpl;
import com.matariky.excel.ExcelSelected;
import com.matariky.excel.TypeNameOptionServiceImpl;
import lombok.Data;

import java.io.Serializable;

@Data
public class BasicBaseDeviceExeclVO implements Serializable {

    @ExcelProperty(" Device Type ")
    @ColumnWidth(30)
    @ExcelSelected(sourceClass = TypeNameOptionServiceImpl.class)
    private String typeName;

    @ExcelProperty(" Device  Code ")
    @ColumnWidth(20)
    private String deviceCode;

    @ExcelProperty(" Device  Power ")
    @ColumnWidth(20)
    @ExcelSelected(sourceClass = DeviceDbmOptionServiceImpl.class)
    private String deviceDbm;

    @ExcelProperty(" Device  Description ")
    @ColumnWidth(20)
    private String deviceDescribe;

    @ExcelProperty(" Device IP")
    @ColumnWidth(20)
    private String deviceIp;

    @ExcelProperty(" Device MAC Address ")
    @ColumnWidth(20)
    private String deviceMac;

    @ExcelProperty("longitude")
    @ColumnWidth(20)
    private String longitude;

    @ExcelProperty("latitude")
    @ColumnWidth(20)
    private String latitude;

    @ExcelProperty(" Install  Address ")
    @ColumnWidth(20)
    private String installAddress;

}
