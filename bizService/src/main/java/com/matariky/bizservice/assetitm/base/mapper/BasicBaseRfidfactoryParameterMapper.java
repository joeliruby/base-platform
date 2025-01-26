package com.matariky.bizservice.assetitm.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidfactoryParameter;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicBaseRfidfactoryParameterMapper extends BaseMapper<BasicBaseRfidfactoryParameter> {

	public List<BasicBaseRfidfactoryParameter> getBasicBaseRfidfactoryParameterAll();

	public int getBasicBaseRfidfactoryParameterAllCount();

	public int createBasicBaseRfidfactoryParameter(BasicBaseRfidfactoryParameter bean);

	public int updateBasicBaseRfidfactoryParameter(BasicBaseRfidfactoryParameter bean);

	public int delBasicBaseRfidfactoryParameterById(Long id);

	public int delBasicBaseRfidfactoryParameterByFactoryId(Long factoryId);

	public BasicBaseRfidfactoryParameter getBasicBaseRfidfactoryParameterById(Long id);

	@Override
	int insert(BasicBaseRfidfactoryParameter record);

	@Override
	int deleteById(Serializable id);

	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactoryParameter> queryWrapper);

	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseRfidfactoryParameter entity);

	@Override
	int update(@Param(Constants.ENTITY) BasicBaseRfidfactoryParameter entity,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactoryParameter> updateWrapper);

	@Override
	BasicBaseRfidfactoryParameter selectById(Serializable id);

	@Override
	List<BasicBaseRfidfactoryParameter> selectBatchIds(
			@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicBaseRfidfactoryParameter> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	BasicBaseRfidfactoryParameter selectOne(
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactoryParameter> queryWrapper);

	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactoryParameter> queryWrapper);

	@Override
	List<BasicBaseRfidfactoryParameter> selectList(
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactoryParameter> queryWrapper);

	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactoryParameter> queryWrapper);

	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactoryParameter> queryWrapper);

	Page<BasicBaseRfidfactoryParameter> selectPage(Page<BasicBaseRfidfactoryParameter> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactoryParameter> queryWrapper);

	public List<BasicBaseRfidfactoryParameter> getBasicBaseRfidfactoryParameterDAC(
			@Param("params") Map<String, Object> params);

	public Long getBasicBaseRfidfactoryParameterDACCount(@Param("params") Map<String, Object> params);

	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseRfidfactoryParameter> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactoryParameter> queryWrapper);

}
