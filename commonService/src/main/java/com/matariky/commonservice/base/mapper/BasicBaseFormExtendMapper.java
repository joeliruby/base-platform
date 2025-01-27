package com.matariky.commonservice.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.matariky.annotation.DataScope;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import com.matariky.commonservice.base.bean.BasicBaseFormExtend;
import com.matariky.commonservice.base.vo.BasicBaseFormExtendQueryVO;
import org.apache.ibatis.annotations.Param;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicBaseFormExtendMapper extends BaseMapper<BasicBaseFormExtend> {

	@DataScope(alias = "t")
	public List<BasicBaseFormExtend> getBasicBaseFormExtendAll(@Param("params") BasicBaseFormExtend bean);

	public int getBasicBaseFormExtendAllCount();

	public int createBasicBaseFormExtend(BasicBaseFormExtend bean);

	public List<BasicBaseFormExtend> selectExtendClearList();

	public int updateBasicBaseFormExtend(@Param("params") BasicBaseFormExtend bean);

	public int delBasicBaseFormExtendById(Long id);

	public BasicBaseFormExtend getBasicBaseFormExtendById(Long id);

	// Insert a record
	@Override
	int insert(BasicBaseFormExtend record);

	// Delete By ID
	@Override
	int deleteById(Serializable id);

	// Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);

	// Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	// Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseFormExtend entity);

	// Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseFormExtend entity,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> updateWrapper);

	// Query By ID
	@Override
	BasicBaseFormExtend selectById(Serializable id);

	@Override
	List<BasicBaseFormExtend> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicBaseFormExtend> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Query One By Entity
	@Override
	BasicBaseFormExtend selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);

	// Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);

	public Integer getBasicBaseFormExtendCount(@Param("params") BasicBaseFormExtendQueryVO extend);

	// Query All By Entity
	@Override
	List<BasicBaseFormExtend> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);

	// Query All By Entity（ With Pagination ）
	Page<BasicBaseFormExtend> selectPage(Page<BasicBaseFormExtend> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);

	public List<BasicBaseFormExtend> getBasicBaseFormExtendDAC(@Param("params") Map<String, Object> params);

	public Long getBasicBaseFormExtendDACCount(@Param("params") Map<String, Object> params);

	// Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseFormExtend> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);

}
