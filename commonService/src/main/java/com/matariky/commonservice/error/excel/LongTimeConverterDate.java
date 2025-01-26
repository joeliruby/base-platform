package com.matariky.commonservice.error.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LongTimeConverterDate implements Converter<Long> {


    @Override
    public WriteCellData<?> convertToExcelData(Long value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Date date =new Date(value);
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        return new WriteCellData(format);
    }
}
