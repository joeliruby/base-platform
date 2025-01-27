package com.matariky.jobs.jobsService.assetitm.base.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: Reader Base Information
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
     * Library Code
     */
    private String rackCode;

    /**
     * Reader Code
     */
    private String readerCode;
}
