package com.matariky.excel;

import com.matariky.commonservice.base.service.BasicBaseDeviceService;
import com.matariky.commonservice.base.service.ExcelService;
import com.matariky.commonservice.base.vo.DbmVO;

import java.util.List;

public class DeviceDbmOptionServiceImpl implements ExcelDynamicSelect {


    @Override
    public String[] getSource(ExcelService excelService) {
        BasicBaseDeviceService deviceService = excelService.getBasicBaseDeviceService();
        List<DbmVO> dbmOption = deviceService.getDbmOption();
        String[] optionArray = new String[dbmOption.size()];
        final int[] index = {0};
        dbmOption.stream().forEach(item -> {
            optionArray[index[0]] = item.getLabel();
            index[0]++;
        });
        return optionArray;
    }
}
