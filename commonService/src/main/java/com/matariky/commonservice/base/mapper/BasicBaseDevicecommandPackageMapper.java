package com.matariky.commonservice.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.matariky.annotation.DataScope;

import java.util.Collection;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import com.matariky.commonservice.base.bean.BasicBaseDevicecommandPackage;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseDevicecommandPackageMapper extends BaseMapper<BasicBaseDevicecommandPackage>{
	 
@DataScope(alias="t")	public List<BasicBaseDevicecommandPackage> getBasicBaseDevicecommandPackageAll(@Param("params") BasicBaseDevicecommandPackage bean);
	 
	public int getBasicBaseDevicecommandPackageAllCount();
	 
	public int createBasicBaseDevicecommandPackage(BasicBaseDevicecommandPackage bean);
	 
	public int updateBasicBaseDevicecommandPackage(@Param("params") BasicBaseDevicecommandPackage bean);
	 
	public int delBasicBaseDevicecommandPackageById(int id);
	 
	public BasicBaseDevicecommandPackage getBasicBaseDevicecommandPackageById(int id);
	//Insert a record
	@Override
	int insert(BasicBaseDevicecommandPackage record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseDevicecommandPackage entity);
	//Update By Entity
	//Query By ID 
	@Override
	BasicBaseDevicecommandPackage selectById(Serializable id);
	 
	@Override
	List<BasicBaseDevicecommandPackage> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseDevicecommandPackage> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	BasicBaseDevicecommandPackage selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	//Query All By Entity
	@Override
	List<BasicBaseDevicecommandPackage> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	//Query All By Entity（ With Pagination ）
	 Page<BasicBaseDevicecommandPackage> selectPage(Page<BasicBaseDevicecommandPackage> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	public List<BasicBaseDevicecommandPackage> getBasicBaseDevicecommandPackageDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseDevicecommandPackageDACCount(@Param("params") Map<String, Object> params);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseDevicecommandPackage> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);


}
