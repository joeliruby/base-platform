package com.matariky.jobs.jobsService.bean.form;

import com.matariky.commonservice.base.vo.TapeRfidCreateCNExeclReqVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RfidUploadJobForm implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Task id
     */
    private Long taskId;

    /**
     * Start Time
     */
    private Long startTime;

    /**
     * Task Type ,1=Execute immediately, 2 = one -time, 3 = cycle
     */
    private Integer taskType;

    private List<TapeRfidCreateCNExeclReqVo> tapeRfidCreateCNExeclReqVos;

}
