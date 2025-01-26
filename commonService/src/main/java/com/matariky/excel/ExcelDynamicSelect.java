package com.matariky.excel;

import com.matariky.commonservice.base.service.ExcelService;

public interface ExcelDynamicSelect {
    /**
     * 获取动态 Generation 的下拉框可选 Data 
     *
     * @return 动态 Generation 的下拉框可选 Data 
     */
    String[] getSource(ExcelService excelService);
}
