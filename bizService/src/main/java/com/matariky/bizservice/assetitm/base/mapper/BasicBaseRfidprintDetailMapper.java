package com.matariky.bizservice.assetitm.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintDetail;
import org.apache.ibatis.annotations.Param;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicBaseRfidprintDetailMapper extends BaseMapper<BasicBaseRfidprintDetail> {

	public List<BasicBaseRfidprintDetail> getBasicBaseRfidprintDetailAll(BasicBaseRfidprintDetail bean);

	public int getBasicBaseRfidprintDetailAllCount();

	public int createBasicBaseRfidprintDetail(BasicBaseRfidprintDetail bean);

	public int updateBasicBaseRfidprintDetail(BasicBaseRfidprintDetail bean);

	public int delBasicBaseRfidprintDetailById(int id);

	public BasicBaseRfidprintDetail getBasicBaseRfidprintDetailById(Long id);

	@Override
	int insert(BasicBaseRfidprintDetail record);

	@Override
	int deleteById(Serializable id);

	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintDetail> queryWrapper);

	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseRfidprintDetail entity);

	@Override
	int update(@Param(Constants.ENTITY) BasicBaseRfidprintDetail entity,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintDetail> updateWrapper);

	@Override
	BasicBaseRfidprintDetail selectById(Serializable id);

	@Override
	List<BasicBaseRfidprintDetail> selectBatchIds(
			@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicBaseRfidprintDetail> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	BasicBaseRfidprintDetail selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintDetail> queryWrapper);

	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintDetail> queryWrapper);

	@Override
	List<BasicBaseRfidprintDetail> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintDetail> queryWrapper);

	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintDetail> queryWrapper);

	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintDetail> queryWrapper);

	Page<BasicBaseRfidprintDetail> selectPage(Page<BasicBaseRfidprintDetail> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintDetail> queryWrapper);

	public List<BasicBaseRfidprintDetail> getBasicBaseRfidprintDetailDAC(@Param("params") Map<String, Object> params);

	public Long getBasicBaseRfidprintDetailDACCount(@Param("params") Map<String, Object> params);

	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseRfidprintDetail> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidprintDetail> queryWrapper);

	public List<BasicBaseRfidprintDetail> getBasicBaseRfidprintDetailAllById(Long id);

	public List<BasicBaseRfidprintDetail> getBasicBaseRfidprintDetailUnprintById(Long id);
}
