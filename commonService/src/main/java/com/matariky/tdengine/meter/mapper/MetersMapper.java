package com.matariky.tdengine.meter.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.tdengine.meter.bean.Meters;
import com.matariky.tdengine.meter.vo.MetersVO;

public interface MetersMapper extends BaseMapper<Meters> {

    void insertOne(Meters meters);

    void insertBatch(@Param("table") String table, @Param("list") List<Meters> metersList);

    List<MetersVO> selectByTime(@Param("code") String code,
                                @Param("startTime") Timestamp startTime,
                                @Param("endTime") Timestamp endTime);

    void deleteAll(@Param("table") String table);
}
