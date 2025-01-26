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
import com.matariky.commonservice.base.bean.BasicBaseTraceabilityDetail;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseTraceabilityDetailMapper extends BaseMapper<BasicBaseTraceabilityDetail>{
	 
@DataScope(alias="t")	public List<BasicBaseTraceabilityDetail> getBasicBaseTraceabilityDetailAll(@Param("params") BasicBaseTraceabilityDetail bean);
	 
	public int getBasicBaseTraceabilityDetailAllCount();
	 
	public int createBasicBaseTraceabilityDetail(BasicBaseTraceabilityDetail bean);
	 
	public int updateBasicBaseTraceabilityDetail(@Param("params") BasicBaseTraceabilityDetail bean);
	 
	public int delBasicBaseTraceabilityDetailById(int id);
	 
	public BasicBaseTraceabilityDetail getBasicBaseTraceabilityDetailById(int id);
	//插入一条记录
	@Override
	int insert(BasicBaseTraceabilityDetail record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录 
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseTraceabilityDetail entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseTraceabilityDetail entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> updateWrapper);
	//根据 ID  Query   
	@Override
	BasicBaseTraceabilityDetail selectById(Serializable id);
	 
	@Override
	List<BasicBaseTraceabilityDetail> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseTraceabilityDetail> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicBaseTraceabilityDetail selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBaseTraceabilityDetail> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBaseTraceabilityDetail> selectPage(Page<BasicBaseTraceabilityDetail> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	public List<BasicBaseTraceabilityDetail> getBasicBaseTraceabilityDetailDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseTraceabilityDetailDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseTraceabilityDetail> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);


}
