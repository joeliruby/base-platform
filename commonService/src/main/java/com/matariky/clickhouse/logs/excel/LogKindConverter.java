package com.matariky.clickhouse.logs.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class LogKindConverter implements Converter<Integer> {

	@Override
	public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty,
			GlobalConfiguration globalConfiguration) throws Exception {
		String s = "";
		if (value == 0) {
			s = "Unspecified";
		} else if (value == 1) {
			s = "Internal";
		} else if (value == 2) {
			s = "Server";
		} else if (value == 3) {
			s = "Client";
		} else if (value == 4) {
			s = "Message Producer";
		} else if (value == 5) {
			s = "Message Consumer";
		}
		return new WriteCellData<String>(s);
	}
}
