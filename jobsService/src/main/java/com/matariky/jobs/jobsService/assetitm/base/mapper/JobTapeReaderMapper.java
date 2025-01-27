package com.matariky.jobs.jobsService.assetitm.base.mapper;

import com.matariky.jobs.jobsService.assetitm.base.bean.TapeReader;
import com.matariky.jobs.jobsService.assetitm.base.bo.BasicReaderBo;
import com.matariky.mybatis.EnhanceBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * Persistence Interface
 *
 * @author AUTOMATION
 */
public interface JobTapeReaderMapper extends EnhanceBaseMapper<TapeReader> {

    /**
     * @Description: Query Reader 基本 Information
     * @Author: bo.chen
     * @Date: 2023/10/8 13:53
     * @param locationId
     * @param rackIds
     * @return java.util.List<com.matariky.jobs.jobsService.assetitm.base.bo.BasicReaderBo>
     **/
    List<BasicReaderBo> selectBasicList(@Param("locationId") String locationId,
            @Param("rackIds") Collection<Long> rackIds);

}
