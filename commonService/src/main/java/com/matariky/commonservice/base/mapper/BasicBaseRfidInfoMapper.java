package com.matariky.commonservice.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import com.matariky.commonservice.base.vo.TapeRfidCreateCNExeclReqVo;
import org.apache.ibatis.annotations.Param;
import com.matariky.commonservice.base.bean.BasicBaseRfidInfo;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicBaseRfidInfoMapper extends BaseMapper<BasicBaseRfidInfo> {

	public List<BasicBaseRfidInfo> getBasicBaseRfidInfoAll();

	public int getBasicBaseRfidInfoAllCount();

	/**
	 * New Method
	 */
	public int createBasicBaseRfidInfo(BasicBaseRfidInfo bean);

	public int updateBasicBaseRfidInfo(BasicBaseRfidInfo bean);

	public int delBasicBaseRfidInfoById(int id);

	public BasicBaseRfidInfo getBasicBaseRfidInfoById(int id);

	// Insert a record
	@Override
	int insert(BasicBaseRfidInfo record);

	// Delete By ID
	@Override
	int deleteById(Serializable id);

	// Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);

	// Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	// Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseRfidInfo entity);

	// Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseRfidInfo entity,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> updateWrapper);

	// Query By ID
	@Override
	BasicBaseRfidInfo selectById(Serializable id);

	@Override
	List<BasicBaseRfidInfo> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicBaseRfidInfo> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	// Query One By Entity

	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);

	// Query All By Entity
	@Override
	List<BasicBaseRfidInfo> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);

	// Query All By Entity（ With Pagination ）
	Page<BasicBaseRfidInfo> selectPage(Page<BasicBaseRfidInfo> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);

	public List<BasicBaseRfidInfo> getBasicBaseRfidInfoDAC(@Param("params") Map<String, Object> params);

	public Long getBasicBaseRfidInfoDACCount(@Param("params") Map<String, Object> params);

	// Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseRfidInfo> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);

	public BasicBaseRfidInfo getBasicBaseRfidInfoByEpc(String epc);

	public BasicBaseRfidInfo getBasicBaseRfidInfoByOdqr(TapeRfidCreateCNExeclReqVo tapeRfidCreateCNExeclReqVo);

	public BasicBaseRfidInfo getBasicBaseRfidInfo();

	public List<BasicBaseRfidInfo> getBasicBaseRfidInfoByFactoryId(Long Id);

}
