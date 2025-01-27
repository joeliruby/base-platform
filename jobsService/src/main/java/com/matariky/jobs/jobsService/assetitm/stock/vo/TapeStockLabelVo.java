package com.matariky.jobs.jobsService.assetitm.stock.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 磁带库存vo
 * @author: bo.chen
 * @create: 2023/9/25 13:26
 **/
@Data
public class TapeStockLabelVo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 地点id
     */
    private String locationId;

    /**
     * Library id
     */
    private Long libraryId;

    /**
     * epc
     */
    private String epc;

    /**
     * Label id
     */
    private Long labelId;

}
