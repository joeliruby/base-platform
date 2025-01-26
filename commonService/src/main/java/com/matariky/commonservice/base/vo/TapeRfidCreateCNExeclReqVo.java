package com.matariky.commonservice.base.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;

@Data
public class TapeRfidCreateCNExeclReqVo  implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * epc
     */
    @ExcelProperty("EPC")
    @ColumnWidth(20)
    private String epc;


    /**
     * tid
     */
    @ExcelProperty("TID")
    @ColumnWidth(20)
    private String tid;


    /**
     * BarCode
     */
    @ExcelProperty(" Barcode ")
    @ColumnWidth(20)
    private String odContent;

    /**
     * BarCode
     */
    @ExcelProperty(" QR Code ")
    @ColumnWidth(20)
    private String qrContent;

    /**
     * BarCode
     */
    @ExcelProperty(" Item 编码")
    @ColumnWidth(20)
    private String goodsCode;

    /**
     * BarCode
     */
    @ExcelProperty("EPC锁定 Password ")
    @ColumnWidth(20)
    private String password;

    private Long id;


}
