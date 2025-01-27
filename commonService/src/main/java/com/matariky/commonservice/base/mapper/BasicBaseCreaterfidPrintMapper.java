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
import com.matariky.commonservice.base.bean.BasicBaseCreaterfidPrint;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseCreaterfidPrintMapper extends BaseMapper<BasicBaseCreaterfidPrint>{
	 
	public List<BasicBaseCreaterfidPrint> getBasicBaseCreaterfidPrintAll();
	 
	public int getBasicBaseCreaterfidPrintAllCount();
	 
	public int createBasicBaseCreaterfidPrint(BasicBaseCreaterfidPrint bean);
	 
	public int updateBasicBaseCreaterfidPrint(BasicBaseCreaterfidPrint bean);
	 
	public int delBasicBaseCreaterfidPrintById(int id);
	 
	public BasicBaseCreaterfidPrint getBasicBaseCreaterfidPrintById(int id);
	//Insert a record
	@Override
	int insert(BasicBaseCreaterfidPrint record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseCreaterfidPrint entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseCreaterfidPrint entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> updateWrapper);
	//Query By ID   
	@Override
	BasicBaseCreaterfidPrint selectById(Serializable id);
	 
	@Override
	List<BasicBaseCreaterfidPrint> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseCreaterfidPrint> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	BasicBaseCreaterfidPrint selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	//Query All By Entity
	@Override
	List<BasicBaseCreaterfidPrint> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	//Query All By Entity（ With Pagination ）
	 Page<BasicBaseCreaterfidPrint> selectPage(Page<BasicBaseCreaterfidPrint> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	public List<BasicBaseCreaterfidPrint> getBasicBaseCreaterfidPrintDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseCreaterfidPrintDACCount(@Param("params") Map<String, Object> params);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseCreaterfidPrint> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);


}
