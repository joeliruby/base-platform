package com.matariky.jobs.jobsService.assetitm.inventory.mapper;

import com.matariky.jobs.jobsService.assetitm.inventory.bean.TapeInventorySubtask;
import com.matariky.mybatis.EnhanceBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobTapeInventorySubtaskMapper extends EnhanceBaseMapper<TapeInventorySubtask> {

    List<TapeInventorySubtask> selectDiscrepancySubtask(@Param("firstLocationId") String firstLocationId,
            @Param("locationId") String locationId, @Param("arrayRackIds") String arrayRackIds);
}
