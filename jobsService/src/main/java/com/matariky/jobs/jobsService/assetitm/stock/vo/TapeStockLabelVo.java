package com.matariky.jobs.jobsService.assetitm.stock.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TapeStockLabelVo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Place id
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
