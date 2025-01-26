package com.matariky.commonservice.accesslog.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;
import com.matariky.commonservice.accesslog.bean.CommonAccessLog;
/**
* Persistence Interface
* @author AUTOMATION
*/
@Mapper
public interface CommonAccessLogMapper extends BaseMapper<CommonAccessLog>{
	 
	public Page<CommonAccessLog> getCommonAccessLogAll();
	 
	public int getCommonAccessLogAllCount();
	 
	public int createCommonAccessLog(CommonAccessLog bean);
	 
	public int updateCommonAccessLog(@Param("params") CommonAccessLog bean);
	 
	public int delCommonAccessLogById(int id);
	 
	public CommonAccessLog getCommonAccessLogById(String id);

		@Override
	int insert(CommonAccessLog entity);


	@Override
    int deleteById(Serializable id);


	 @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);


	 @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<CommonAccessLog> queryWrapper);

     @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);


	 @Override
    int updateById(@Param(Constants.ENTITY) CommonAccessLog entity);


	 @Override
    int update(@Param(Constants.ENTITY) CommonAccessLog entity, @Param(Constants.WRAPPER) Wrapper<CommonAccessLog> updateWrapper);


	 @Override
    CommonAccessLog selectById(Serializable id);


	 @Override
    List<CommonAccessLog> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);


	 @Override
    List<CommonAccessLog> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);


	 @Override
    CommonAccessLog selectOne(@Param(Constants.WRAPPER) Wrapper<CommonAccessLog> queryWrapper);


	 @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<CommonAccessLog> queryWrapper);


	 @Override
    List<CommonAccessLog> selectList(@Param(Constants.WRAPPER) Wrapper<CommonAccessLog> queryWrapper);


	@Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<CommonAccessLog> queryWrapper);


	 @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<CommonAccessLog> queryWrapper);


    Page<CommonAccessLog> selectPage(Page<CommonAccessLog> page, @Param(Constants.WRAPPER) Wrapper<CommonAccessLog> queryWrapper);

    Page<Map<String, Object>> selectMapsPage(Page<CommonAccessLog> page, @Param(Constants.WRAPPER) Wrapper<CommonAccessLog> queryWrapper);
     
	public List<CommonAccessLog> getCommonAccessLogDynamicCondition(@Param("params") Map<String, Object> params);
	
	public List<CommonAccessLog> getCommonAccessLogDAC(@Param("params") Map<String, Object> params);
	
	public Long getCommonAccessLogDACCount(@Param("params") Map<String, Object> params);
	
	public List<CommonAccessLog> getCommonAccessLogByIds(@Param("array") String[] split);
	
	


}
