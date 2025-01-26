package com.matariky.excel;

import com.matariky.commonservice.base.service.BasicBaseDeviceTypeService;
import com.matariky.commonservice.base.service.ExcelService;
import com.matariky.commonservice.base.vo.DeviceTypeOption;

import java.util.List;

public class TypeNameOptionServiceImpl implements ExcelDynamicSelect {

    @Override
    public String[] getSource(ExcelService excelService) {
        BasicBaseDeviceTypeService devicetypeService = excelService.getBasicBaseDevicetypeService();
        List<DeviceTypeOption> optionList = devicetypeService.getOptionList();
        String[]  optionArray = new String[optionList.size()];
        final int[] index = {0};
        optionList.stream().forEach(item -> {
            optionArray[index[0]] = item.getLabel();
            index[0]++;
        });
        return optionArray;
    }
}
