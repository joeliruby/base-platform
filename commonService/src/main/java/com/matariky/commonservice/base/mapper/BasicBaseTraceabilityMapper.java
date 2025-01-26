package com.matariky.commonservice.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.matariky.annotation.DataScope;

import java.util.Collection;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import com.matariky.commonservice.base.bean.BasicBaseTraceability;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseTraceabilityMapper extends BaseMapper<BasicBaseTraceability>{
	 
@DataScope(alias="t")	public List<BasicBaseTraceability> getBasicBaseTraceabilityAll(@Param("params") BasicBaseTraceability bean);
	 
	public int getBasicBaseTraceabilityAllCount();
	 
	public int createBasicBaseTraceability(BasicBaseTraceability bean);
	 
	public int updateBasicBaseTraceability(@Param("params") BasicBaseTraceability bean);
	 
	public int delBasicBaseTraceabilityById(int id);
	 
	public BasicBaseTraceability getBasicBaseTraceabilityById(int id);
	//插入一条记录
	@Override
	int insert(BasicBaseTraceability record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录 
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseTraceability entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseTraceability entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> updateWrapper);
	//根据 ID  Query   
	@Override
	BasicBaseTraceability selectById(Serializable id);
	 
	@Override
	List<BasicBaseTraceability> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseTraceability> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicBaseTraceability selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBaseTraceability> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBaseTraceability> selectPage(Page<BasicBaseTraceability> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);
	public List<BasicBaseTraceability> getBasicBaseTraceabilityDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseTraceabilityDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseTraceability> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseTraceability> queryWrapper);


}
