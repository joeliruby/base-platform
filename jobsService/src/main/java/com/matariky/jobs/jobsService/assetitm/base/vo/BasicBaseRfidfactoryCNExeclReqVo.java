package com.matariky.jobs.jobsService.assetitm.base.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicBaseRfidfactoryCNExeclReqVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 118157189495042162L;

    /**
     * Serial number
     */
    @ExcelProperty("EPC")
    @ColumnWidth(20)
    private String EPC;

    /**
     * epc
     */
    @ExcelProperty("EPC Lock  Password ")
    @ColumnWidth(20)
    private String password;

    /**
     * location
     */
    @ExcelProperty(" Item  Name")
    @ColumnWidth(20)
    private String goodsName;

    /**
     * Library
     */
    @ExcelProperty(" Item  Code ")
    @ColumnWidth(20)
    private String goodsCode;

    /**
     * BarCode
     */
    @ExcelProperty(" Barcode   Content")
    @ColumnWidth(20)
    private String odContent;

    /**
     * location
     */
    @ExcelProperty(" QR Code   Content")
    @ColumnWidth(20)
    private String qrContent;

}
