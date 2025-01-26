package com.matariky.jobs.jobsService.bean.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:  Label  Generation 
 * @author: chenyajun
 * @create: 2024/02/16 18:14
 **/
@Data
public class RfidCreateJobForm  implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *  Task id
     */
    private Long taskId;

    /**
     * 开始 Time 
     */
    private Long startTime;


    /**
     *  Task Type ，1=立即执行，2=一次性,3=周期
     */
    private Integer taskType;

}
