package com.matariky.commonservice.error.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorLevelConverter implements Converter<Integer> {

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        String s = "";
        if (value == 1) {
            s = "info";
        } else if (value == 2) {
            s = "debug";
        } else if (value == 3) {
            s = "error";
        } else if (value == 4) {
            s = "fatal";
        }
        return new WriteCellData<String>(s);
    }
}
