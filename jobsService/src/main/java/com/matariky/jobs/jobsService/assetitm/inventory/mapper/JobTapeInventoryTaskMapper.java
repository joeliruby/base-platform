package com.matariky.jobs.jobsService.assetitm.inventory.mapper;

import com.matariky.jobs.jobsService.assetitm.inventory.bean.TapeInventoryTask;
import com.matariky.mybatis.EnhanceBaseMapper;
import org.apache.ibatis.annotations.Param;

public interface JobTapeInventoryTaskMapper extends EnhanceBaseMapper<TapeInventoryTask> {

    String selectInInventoryTaskName(@Param("firstLocationId") String firstLocationId,
            @Param("locationId") String locationId, @Param("arrayRackIds") String arrayRackIds,
            @Param("filterTaskId") Long filterTaskId);

}
