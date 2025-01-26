package com.matariky.jobs.jobsService.assetitm.inventory.mapper;

import com.matariky.jobs.jobsService.assetitm.inventory.bean.TapeInventoryTask;
import com.matariky.mybatis.EnhanceBaseMapper;
import org.apache.ibatis.annotations.Param;


/**
 * @description: 磁带盘点 Task mapper
 * @author: bo.chen
 * @create: 2023/9/08 10:02
 **/
public interface JobTapeInventoryTaskMapper extends EnhanceBaseMapper<TapeInventoryTask> {


    /**
     * @Description:  Query 正在盘点 Task  Name
     * @Author: bo.chen
     * @Date: 2023/10/13 18:00
     * @param firstLocationId
     * @return java.lang.String
     **/
    String selectInInventoryTaskName(@Param("firstLocationId") String firstLocationId,@Param("locationId") String locationId, @Param("arrayRackIds") String arrayRackIds, @Param("filterTaskId") Long filterTaskId);

}
