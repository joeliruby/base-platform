package com.matariky.excel;

import com.matariky.commonservice.base.service.ExcelService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ExcelSelectedResolve {
    /**
     * 下拉 Content
     */
    private String[] source;

    /**
     * Configuration下拉框的起始行 ,默认为第二行
     */
    private int firstRow;

    /**
     * Configuration下拉框的结束行 ,默认为最后一行
     */
    private int lastRow;

    public String[] resolveSelectedSource(ExcelSelected excelSelected, ExcelService excelService) {
        if (excelSelected == null) {
            return null;
        }

        // Retrieve固定下拉框的 Content
        String[] source = excelSelected.source();
        if (source.length > 0) {
            return source;
        }

        // Retrieve动态下拉框的 Content
        Class<? extends ExcelDynamicSelect>[] classes = excelSelected.sourceClass();
        if (classes.length > 0) {
            try {
                ExcelDynamicSelect excelDynamicSelect = classes[0].newInstance();
                String[] dynamicSelectSource = excelDynamicSelect.getSource(excelService);
                if (dynamicSelectSource != null && dynamicSelectSource.length > 0) {
                    return dynamicSelectSource;
                }
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("解析动态下拉框 Data 异常", e);
            }
        }
        return null;
    }

}
