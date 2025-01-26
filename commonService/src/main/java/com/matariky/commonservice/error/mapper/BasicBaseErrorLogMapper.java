package com.matariky.commonservice.error.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.error.bean.BasicBaseErrorLog;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseErrorLogMapper extends BaseMapper<BasicBaseErrorLog>{
	 
	@DataScope(alias="a")
	public List<BasicBaseErrorLog> getBasicBaseErrorLogAll(@Param("params") BasicBaseErrorLog bean);
	 
	@DataScope
	public int getBasicBaseErrorLogAllCount();
	 
	public int createBasicBaseErrorLog(BasicBaseErrorLog bean);
	 
	public int updateBasicBaseErrorLog(@Param("params") BasicBaseErrorLog bean);
	 
	public int delBasicBaseErrorLogById(int id);
	 
	public BasicBaseErrorLog getBasicBaseErrorLogById(int id);
	//插入一条记录
	@Override
	int insert(BasicBaseErrorLog record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseErrorLog entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseErrorLog entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> updateWrapper);
	//根据 ID  Query 
	@Override
	BasicBaseErrorLog selectById(Serializable id);
	 
	@Override
	List<BasicBaseErrorLog> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseErrorLog> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicBaseErrorLog selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBaseErrorLog> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBaseErrorLog> selectPage(Page<BasicBaseErrorLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);
	public List<BasicBaseErrorLog> getBasicBaseErrorLogDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseErrorLogDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseErrorLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseErrorLog> queryWrapper);


}
