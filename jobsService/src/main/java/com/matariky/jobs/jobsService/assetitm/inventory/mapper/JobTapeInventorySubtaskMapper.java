package com.matariky.jobs.jobsService.assetitm.inventory.mapper;

import com.matariky.jobs.jobsService.assetitm.inventory.bean.TapeInventorySubtask;
import com.matariky.mybatis.EnhanceBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 盘点子 Task mapper
 * @author: bo.chen
 * @create: 2023/9/22 14:49
 **/
public interface JobTapeInventorySubtaskMapper extends EnhanceBaseMapper<TapeInventorySubtask> {

    /**
     * @Description:  Query 有差异的子 Task （未处理）
     * @Author: bo.chen
     * @Date: 2023/10/16 10:03
     * @param firstLocationId
     * @param locationId
     * @param arrayRackIds
     * @return java.util.List<com.matariky.jobs.jobsService.assetitm.inventory.bean.TapeInventorySubtask>
     **/
    List<TapeInventorySubtask> selectDiscrepancySubtask(@Param("firstLocationId") String firstLocationId, @Param("locationId") String locationId, @Param("arrayRackIds") String arrayRackIds);
}
