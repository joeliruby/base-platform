package com.matariky.jobs.jobsService.assetitm.base.mapper;

import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidPrintDetailTask;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidPrintParameterTask;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidPrintTask;
import com.matariky.mybatis.EnhanceBaseMapper;

import java.util.List;

public interface JobRfidPrintTaskMapper extends EnhanceBaseMapper<TapeRfidPrintTask> {
     
    public TapeRfidPrintTask getBasicBaseRfidPrintById(Long id);

     
    public List<TapeRfidPrintParameterTask> getBasicBaseRfidPrintParameterById(Long id);


     
    public int createBasicBaseRfidprintDetail(TapeRfidPrintDetailTask bean);
}
