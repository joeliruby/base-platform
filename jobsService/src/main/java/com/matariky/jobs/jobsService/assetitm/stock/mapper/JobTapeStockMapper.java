package com.matariky.jobs.jobsService.assetitm.stock.mapper;

import com.matariky.jobs.jobsService.assetitm.stock.bean.TapeStock;
import com.matariky.jobs.jobsService.assetitm.stock.vo.TapeStockLabelVo;
import com.matariky.mybatis.EnhanceBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* Persistence Interface
* @author AUTOMATION
*/
public interface JobTapeStockMapper extends EnhanceBaseMapper<TapeStock> {

    /**
     * @Description: 获取库存 Label 
     * @Author: bo.chen
     * @Date: 2023/9/22 11:56
     * @param locationId
     * @return java.util.List<com.matariky.jobs.jobsService.assetitm.stock.vo.TapeStockVo>
     **/
    List<TapeStockLabelVo> selectStockLabelList(@Param("locationId") String locationId, @Param("rackIds") List<Long> rackIds);

}
