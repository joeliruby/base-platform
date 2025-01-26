package com.matariky.bizservice.assetitm.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.Collection;

import com.github.pagehelper.Page;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintParameter;
import org.apache.ibatis.annotations.Param;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicBaseRfidprintParameterMapper extends BaseMapper<BasicBaseRfidprintParameter> {

	public List<BasicBaseRfidprintParameter> getBasicBaseRfidprintParameterAll();

	public int getBasicBaseRfidprintParameterAllCount();

	public int createBasicBaseRfidprintParameter(BasicBaseRfidprintParameter bean);

	public int updateBasicBaseRfidprintParameter(BasicBaseRfidprintParameter bean);

	public int delBasicBaseRfidprintParameterById(int id);

	public BasicBaseRfidprintParameter getBasicBaseRfidprintParameterById(int id);

	@Override
	int insert(BasicBaseRfidprintParameter record);

	@Override
	int deleteById(Serializable id);

	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintParameter> queryWrapper);

	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseRfidprintParameter entity);

	@Override
	int update(@Param(Constants.ENTITY) BasicBaseRfidprintParameter entity,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintParameter> updateWrapper);

	@Override
	BasicBaseRfidprintParameter selectById(Serializable id);

	@Override
	List<BasicBaseRfidprintParameter> selectBatchIds(
			@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicBaseRfidprintParameter> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	BasicBaseRfidprintParameter selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintParameter> queryWrapper);

	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintParameter> queryWrapper);

	@Override
	List<BasicBaseRfidprintParameter> selectList(
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintParameter> queryWrapper);

	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintParameter> queryWrapper);

	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintParameter> queryWrapper);

	Page<BasicBaseRfidprintParameter> selectPage(Page<BasicBaseRfidprintParameter> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintParameter> queryWrapper);

	public List<BasicBaseRfidprintParameter> getBasicBaseRfidprintParameterDAC(
			@Param("params") Map<String, Object> params);

	public Long getBasicBaseRfidprintParameterDACCount(@Param("params") Map<String, Object> params);

	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseRfidprintParameter> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintParameter> queryWrapper);

}
