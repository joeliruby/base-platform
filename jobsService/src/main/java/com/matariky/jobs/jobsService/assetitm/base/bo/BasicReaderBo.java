package com.matariky.jobs.jobsService.assetitm.base.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 读写器基础 Information 
 * @author: bo.chen
 * @create: 2023/10/8 13:50
 **/
@Data
public class BasicReaderBo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 机架编码
     */
    private String rackCode;

    /**
     * 读写器编码
     */
    private String readerCode;
}
