package com.matariky.commonservice.sqlog.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;

import com.matariky.commonservice.sqlog.bean.CommonSqlLog;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface CommonSqlLogMapper extends BaseMapper<CommonSqlLog>{
	 
	public Page<CommonSqlLog> getCommonSqlLogAll();
	 
	public int getCommonSqlLogAllCount();
	 
	public int createCommonSqlLog(CommonSqlLog bean);
	 
	public int updateCommonSqlLog(@Param("params") CommonSqlLog bean);
	 
	public int delCommonSqlLogById(int id);
	 
	public CommonSqlLog getCommonSqlLogById(int id);

		@Override
	int insert(CommonSqlLog entity);

    
	@Override
    int deleteById(Serializable id);

    
	 @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    
	 @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<CommonSqlLog> queryWrapper);

     @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    
	 @Override
    int updateById(@Param(Constants.ENTITY) CommonSqlLog entity);

    
	 @Override
    int update(@Param(Constants.ENTITY) CommonSqlLog entity, @Param(Constants.WRAPPER) Wrapper<CommonSqlLog> updateWrapper);

    
	 @Override
    CommonSqlLog selectById(Serializable id);

    
	 @Override
    List<CommonSqlLog> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    
	 @Override
    List<CommonSqlLog> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    
	 @Override
    CommonSqlLog selectOne(@Param(Constants.WRAPPER) Wrapper<CommonSqlLog> queryWrapper);

    
	 @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<CommonSqlLog> queryWrapper);

    
	 @Override
    List<CommonSqlLog> selectList(@Param(Constants.WRAPPER) Wrapper<CommonSqlLog> queryWrapper);

    
	@Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<CommonSqlLog> queryWrapper);

    
	 @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<CommonSqlLog> queryWrapper);

    
    Page<CommonSqlLog> selectPage(Page<CommonSqlLog> page, @Param(Constants.WRAPPER) Wrapper<CommonSqlLog> queryWrapper);

    Page<Map<String, Object>> selectMapsPage(Page<CommonSqlLog> page, @Param(Constants.WRAPPER) Wrapper<CommonSqlLog> queryWrapper);
     
	public Page<CommonSqlLog> getCommonSQLLogDynamicCondition(CommonSqlLog params);
}
