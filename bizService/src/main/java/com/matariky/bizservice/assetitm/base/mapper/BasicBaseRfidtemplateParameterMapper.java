package com.matariky.bizservice.assetitm.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.matariky.annotation.DataScope;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidtemplateParameter;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicBaseRfidtemplateParameterMapper extends BaseMapper<BasicBaseRfidtemplateParameter> {

	@DataScope(alias = "t")
	public List<BasicBaseRfidtemplateParameter> getBasicBaseRfidtemplateParameterAll(
			@Param("params") BasicBaseRfidtemplateParameter bean);

	public int getBasicBaseRfidtemplateParameterAllCount();

	public int createBasicBaseRfidtemplateParameter(BasicBaseRfidtemplateParameter bean);

	public int updateBasicBaseRfidtemplateParameter(@Param("params") BasicBaseRfidtemplateParameter bean);

	public int delBasicBaseRfidtemplateParameterById(int id);

	public BasicBaseRfidtemplateParameter getBasicBaseRfidtemplateParameterById(int id);

	@Override
	int insert(BasicBaseRfidtemplateParameter record);

	@Override
	int deleteById(Serializable id);

	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplateParameter> queryWrapper);

	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseRfidtemplateParameter entity);

	@Override
	int update(@Param(Constants.ENTITY) BasicBaseRfidtemplateParameter entity,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplateParameter> updateWrapper);

	@Override
	BasicBaseRfidtemplateParameter selectById(Serializable id);

	@Override
	List<BasicBaseRfidtemplateParameter> selectBatchIds(
			@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicBaseRfidtemplateParameter> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	BasicBaseRfidtemplateParameter selectOne(
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplateParameter> queryWrapper);

	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplateParameter> queryWrapper);

	@Override
	List<BasicBaseRfidtemplateParameter> selectList(
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplateParameter> queryWrapper);

	@Override
	List<Map<String, Object>> selectMaps(
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplateParameter> queryWrapper);

	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplateParameter> queryWrapper);

	Page<BasicBaseRfidtemplateParameter> selectPage(Page<BasicBaseRfidtemplateParameter> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplateParameter> queryWrapper);

	public List<BasicBaseRfidtemplateParameter> getBasicBaseRfidtemplateParameterDAC(
			@Param("params") Map<String, Object> params);

	public Long getBasicBaseRfidtemplateParameterDACCount(@Param("params") Map<String, Object> params);

	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseRfidtemplateParameter> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplateParameter> queryWrapper);

}
