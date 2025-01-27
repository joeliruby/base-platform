package com.matariky.jobs.jobsService.assetitm.inventory.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TapeInventoryTaskBasicInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Task Name
     */
    private String taskName;

    /**
     * Task id
     */
    private Long id;
}
