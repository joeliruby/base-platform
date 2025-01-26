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
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidtemplate;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicBaseRfidtemplateMapper extends BaseMapper<BasicBaseRfidtemplate> {

	@DataScope(alias = "t")
	public List<BasicBaseRfidtemplate> getBasicBaseRfidtemplateAll(@Param("params") BasicBaseRfidtemplate bean);

	public int getBasicBaseRfidtemplateAllCount();

	public int createBasicBaseRfidtemplate(BasicBaseRfidtemplate bean);

	public int updateBasicBaseRfidtemplate(@Param("params") BasicBaseRfidtemplate bean);

	public int delBasicBaseRfidtemplateById(int id);

	public BasicBaseRfidtemplate getBasicBaseRfidtemplateById(int id);

	@Override
	int insert(BasicBaseRfidtemplate record);

	@Override
	int deleteById(Serializable id);

	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplate> queryWrapper);

	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseRfidtemplate entity);

	@Override
	int update(@Param(Constants.ENTITY) BasicBaseRfidtemplate entity,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplate> updateWrapper);

	@Override
	BasicBaseRfidtemplate selectById(Serializable id);

	@Override
	List<BasicBaseRfidtemplate> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicBaseRfidtemplate> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	BasicBaseRfidtemplate selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplate> queryWrapper);

	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplate> queryWrapper);

	@Override
	List<BasicBaseRfidtemplate> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplate> queryWrapper);

	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplate> queryWrapper);

	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplate> queryWrapper);

	Page<BasicBaseRfidtemplate> selectPage(Page<BasicBaseRfidtemplate> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplate> queryWrapper);

	public List<BasicBaseRfidtemplate> getBasicBaseRfidtemplateDAC(@Param("params") Map<String, Object> params);

	public Long getBasicBaseRfidtemplateDACCount(@Param("params") Map<String, Object> params);

	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseRfidtemplate> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidtemplate> queryWrapper);

}
