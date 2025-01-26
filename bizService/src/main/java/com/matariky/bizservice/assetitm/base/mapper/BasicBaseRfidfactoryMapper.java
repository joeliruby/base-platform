package com.matariky.bizservice.assetitm.base.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;
import com.matariky.annotation.DataScope;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidfactory;
import com.matariky.mybatis.EnhanceBaseMapper;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicBaseRfidfactoryMapper extends EnhanceBaseMapper<BasicBaseRfidfactory> {

	@DataScope(alias = "a")
	public List<BasicBaseRfidfactory> getBasicBaseRfidfactoryAll(@Param("params") BasicBaseRfidfactory bean);

	@DataScope()
	public int getBasicBaseRfidfactoryAllCount();

	public int createBasicBaseRfidfactory(BasicBaseRfidfactory bean);

	public int updateBasicBaseRfidfactory(BasicBaseRfidfactory bean);

	public int delBasicBaseRfidfactoryById(Long id);

	public BasicBaseRfidfactory getBasicBaseRfidfactoryById(Long id);

	@Override
	int insert(BasicBaseRfidfactory record);

	@Override
	int deleteById(Serializable id);

	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactory> queryWrapper);

	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseRfidfactory entity);

	@Override
	int update(@Param(Constants.ENTITY) BasicBaseRfidfactory entity,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactory> updateWrapper);

	@Override
	BasicBaseRfidfactory selectById(Serializable id);

	@Override
	List<BasicBaseRfidfactory> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicBaseRfidfactory> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	BasicBaseRfidfactory selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactory> queryWrapper);

	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactory> queryWrapper);

	@Override
	List<BasicBaseRfidfactory> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactory> queryWrapper);

	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactory> queryWrapper);

	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactory> queryWrapper);

	Page<BasicBaseRfidfactory> selectPage(Page<BasicBaseRfidfactory> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactory> queryWrapper);

	public List<BasicBaseRfidfactory> getBasicBaseRfidfactoryDAC(@Param("params") Map<String, Object> params);

	public Long getBasicBaseRfidfactoryDACCount(@Param("params") Map<String, Object> params);

	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseRfidfactory> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidfactory> queryWrapper);

}
