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
	//插入一条记录
	@Override
	int insert(BasicBaseDevicecommandPackage record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseDevicecommandPackage entity);
	//根据 whereEntity 条件，Update记录
	//根据 ID  Query 
	@Override
	BasicBaseDevicecommandPackage selectById(Serializable id);
	 
	@Override
	List<BasicBaseDevicecommandPackage> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseDevicecommandPackage> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicBaseDevicecommandPackage selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBaseDevicecommandPackage> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBaseDevicecommandPackage> selectPage(Page<BasicBaseDevicecommandPackage> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);
	public List<BasicBaseDevicecommandPackage> getBasicBaseDevicecommandPackageDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseDevicecommandPackageDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseDevicecommandPackage> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommandPackage> queryWrapper);


}
