package com.matariky.clickhouse.logs.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class LogTypeConverter implements Converter<String> {


    @Override
    public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
    	String s = "";
    	if (value.equals("1")) {
    	    s = "Network Log";
    	} else if (value.equals("2")) {
    	    s = "App Crash Log";
    	} else if (value.equals("3")) {
    	    s = "Print Data Log";
    	} else if (value.equals("4")) {
    	    s = "Device Log";
    	}
    	return new WriteCellData(s);
    }
}
