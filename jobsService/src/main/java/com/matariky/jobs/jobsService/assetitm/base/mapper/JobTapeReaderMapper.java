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

    List<BasicReaderBo> selectBasicList(@Param("locationId") String locationId,
            @Param("rackIds") Collection<Long> rackIds);

}
