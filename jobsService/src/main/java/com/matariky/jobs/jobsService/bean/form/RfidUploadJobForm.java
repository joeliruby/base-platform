package com.matariky.jobs.jobsService.bean.form;

import com.matariky.commonservice.base.vo.TapeRfidCreateCNExeclReqVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:  Label Import
 * @author: chenyajun
 * @create: 2024/02/16 18:14
 **/
@Data
public class RfidUploadJobForm   implements Serializable {
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

    private List<TapeRfidCreateCNExeclReqVo> tapeRfidCreateCNExeclReqVos;


}
