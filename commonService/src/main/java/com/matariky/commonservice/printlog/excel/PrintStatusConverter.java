package com.matariky.commonservice.printlog.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class PrintStatusConverter implements Converter<Integer> {

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) throws Exception {
        String s = "";
        if (value == 1) {
            s = " Start  Print ";
        } else if (value == 2) {
            s = " Printed ";
        }
        return new WriteCellData<String>(s);
    }
}
