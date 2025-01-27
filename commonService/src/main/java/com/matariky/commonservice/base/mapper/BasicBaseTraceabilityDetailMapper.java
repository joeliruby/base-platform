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
	//Insert a record
	@Override
	int insert(BasicBaseTraceabilityDetail record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseTraceabilityDetail entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseTraceabilityDetail entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> updateWrapper);
	//Query By ID   
	@Override
	BasicBaseTraceabilityDetail selectById(Serializable id);
	 
	@Override
	List<BasicBaseTraceabilityDetail> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseTraceabilityDetail> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	BasicBaseTraceabilityDetail selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	//Query All By Entity
	@Override
	List<BasicBaseTraceabilityDetail> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	//Query All By Entity（ With Pagination ）
	 Page<BasicBaseTraceabilityDetail> selectPage(Page<BasicBaseTraceabilityDetail> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);
	public List<BasicBaseTraceabilityDetail> getBasicBaseTraceabilityDetailDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseTraceabilityDetailDACCount(@Param("params") Map<String, Object> params);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseTraceabilityDetail> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseTraceabilityDetail> queryWrapper);


}
