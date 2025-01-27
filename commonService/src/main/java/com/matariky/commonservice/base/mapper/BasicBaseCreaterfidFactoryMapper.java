package com.matariky.commonservice.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.Collection;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import com.matariky.commonservice.base.bean.BasicBaseCreaterfidFactory;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseCreaterfidFactoryMapper extends BaseMapper<BasicBaseCreaterfidFactory>{
	 
	public List<BasicBaseCreaterfidFactory> getBasicBaseCreaterfidFactoryAll();
	 
	public int getBasicBaseCreaterfidFactoryAllCount();
	 
	public int createBasicBaseCreaterfidFactory(BasicBaseCreaterfidFactory bean);
	 
	public int updateBasicBaseCreaterfidFactory(BasicBaseCreaterfidFactory bean);
	 
	public int delBasicBaseCreaterfidFactoryById(int id);
	 
	public BasicBaseCreaterfidFactory getBasicBaseCreaterfidFactoryById(int id);
	//Insert a record
	@Override
	int insert(BasicBaseCreaterfidFactory record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseCreaterfidFactory entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseCreaterfidFactory entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> updateWrapper);
	//Query By ID   
	@Override
	BasicBaseCreaterfidFactory selectById(Serializable id);
	 
	@Override
	List<BasicBaseCreaterfidFactory> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseCreaterfidFactory> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	BasicBaseCreaterfidFactory selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	//Query All By Entity
	@Override
	List<BasicBaseCreaterfidFactory> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	//Query All By Entity（ With Pagination ）
	 Page<BasicBaseCreaterfidFactory> selectPage(Page<BasicBaseCreaterfidFactory> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	public List<BasicBaseCreaterfidFactory> getBasicBaseCreaterfidFactoryDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseCreaterfidFactoryDACCount(@Param("params") Map<String, Object> params);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseCreaterfidFactory> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	public List<BasicBaseCreaterfidFactory> getBasicBaseCreaterfidFactoryByFactoryId(Long id);

}
