package com.matariky.jobs.jobsService.assetitm.base.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenyajun
 * @DATe: 2024/2/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicBaseRfidfactoryCNExeclReqVo  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 118157189495042162L;


    /**
     * 序列号
     */
    @ExcelProperty("EPC")
    @ColumnWidth(20)
    private String EPC;


    /**
     * epc
     */
    @ExcelProperty("EPC锁定 Password ")
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
    @ExcelProperty(" Item 编码")
    @ColumnWidth(20)
    private String goodsCode;


    /**
     * BarCode
     */
    @ExcelProperty(" Barcode 内容")
    @ColumnWidth(20)
    private String odContent;


    /**
     * location
     */
    @ExcelProperty(" QR Code 内容")
    @ColumnWidth(20)
    private String qrContent;

}
