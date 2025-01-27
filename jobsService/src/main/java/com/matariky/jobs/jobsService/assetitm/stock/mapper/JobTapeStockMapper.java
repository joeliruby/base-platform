package com.matariky.jobs.jobsService.assetitm.stock.mapper;

import com.matariky.jobs.jobsService.assetitm.stock.bean.TapeStock;
import com.matariky.jobs.jobsService.assetitm.stock.vo.TapeStockLabelVo;
import com.matariky.mybatis.EnhanceBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface JobTapeStockMapper extends EnhanceBaseMapper<TapeStock> {

    List<TapeStockLabelVo> selectStockLabelList(@Param("locationId") String locationId,
            @Param("rackIds") List<Long> rackIds);

}
