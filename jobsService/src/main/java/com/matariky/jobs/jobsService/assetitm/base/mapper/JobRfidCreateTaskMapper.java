package com.matariky.jobs.jobsService.assetitm.base.mapper;

import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidCreateParameterTask;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidCreateTask;
import com.matariky.mybatis.EnhanceBaseMapper;

import java.util.List;

public interface JobRfidCreateTaskMapper  extends EnhanceBaseMapper<TapeRfidCreateTask> {
     
    public TapeRfidCreateTask getBasicBaseRfidfactoryById(Long id);

     
    public List<TapeRfidCreateParameterTask> getBasicBaseRfidfactoryParameterById(Long id);

    public int updateBasicBaseRfidfactory(TapeRfidCreateTask bean);

}
