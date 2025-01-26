package com.matariky.jobs.jobsService.assetitm.inventory.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 盘点 Task 基础 Information 
 * @author: bo.chen
 * @create: 2023/10/13 17:56
 **/
@Data
public class TapeInventoryTaskBasicInfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *  Task  Name
     */
    private String taskName;

    /**
     *   Task id
     */
    private Long id;
}
