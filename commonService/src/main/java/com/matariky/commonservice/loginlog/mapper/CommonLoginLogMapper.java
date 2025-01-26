package com.matariky.commonservice.loginlog.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;
import com.matariky.commonservice.loginlog.bean.CommonLoginLog;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface CommonLoginLogMapper extends BaseMapper<CommonLoginLog>{
	 
	public Page<CommonLoginLog> getCommonLoginLogAll();
	 
	public int getCommonLoginLogAllCount();
	 
	public int createCommonLoginLog(CommonLoginLog bean);
	 
	public int updateCommonLoginLog(@Param("params") CommonLoginLog bean);
	 
	public int delCommonLoginLogById(int id);
	 
	public CommonLoginLog getCommonLoginLogById(int id);

		@Override
	int insert(CommonLoginLog entity);


	@Override
    int deleteById(Serializable id);


	 @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);


	 @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<CommonLoginLog> queryWrapper);

     @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);


	 @Override
    int updateById(@Param(Constants.ENTITY) CommonLoginLog entity);


	 @Override
    int update(@Param(Constants.ENTITY) CommonLoginLog entity, @Param(Constants.WRAPPER) Wrapper<CommonLoginLog> updateWrapper);


	 @Override
    CommonLoginLog selectById(Serializable id);


	 @Override
    List<CommonLoginLog> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);


	 @Override
    List<CommonLoginLog> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);


	 @Override
    CommonLoginLog selectOne(@Param(Constants.WRAPPER) Wrapper<CommonLoginLog> queryWrapper);


	 @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<CommonLoginLog> queryWrapper);


	 @Override
    List<CommonLoginLog> selectList(@Param(Constants.WRAPPER) Wrapper<CommonLoginLog> queryWrapper);


	@Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<CommonLoginLog> queryWrapper);


	 @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<CommonLoginLog> queryWrapper);


    Page<CommonLoginLog> selectPage(Page<CommonLoginLog> page, @Param(Constants.WRAPPER) Wrapper<CommonLoginLog> queryWrapper);

    Page<Map<String, Object>> selectMapsPage(Page<CommonLoginLog> page, @Param(Constants.WRAPPER) Wrapper<CommonLoginLog> queryWrapper);

    public List<CommonLoginLog> getCommonLoginLogDynamicCondition(@Param("params")Map<String, Object> params);
    
	public List<CommonLoginLog> getCommonLoginLogByIds(@Param("array") String[] split);
     
     
}
