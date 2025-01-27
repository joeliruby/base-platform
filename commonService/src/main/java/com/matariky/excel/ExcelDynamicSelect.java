package com.matariky.excel;

import com.matariky.commonservice.base.service.ExcelService;

public interface ExcelDynamicSelect {
    /**
     * Retrieve Dynamic generation drop -down box is available Data
     *
     * @return Dynamic generation drop -down box is available Data
     */
    String[] getSource(ExcelService excelService);
}
