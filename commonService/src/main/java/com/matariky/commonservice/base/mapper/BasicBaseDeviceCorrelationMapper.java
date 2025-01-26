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
import com.matariky.commonservice.base.bean.BasicBaseDeviceCorrelation;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseDeviceCorrelationMapper extends BaseMapper<BasicBaseDeviceCorrelation>{
	 
@DataScope(alias="t")	public List<BasicBaseDeviceCorrelation> getBasicBaseDeviceCorrelationAll(@Param("params") BasicBaseDeviceCorrelation bean);
	 
	public int getBasicBaseDeviceCorrelationAllCount();
	 
	public int createBasicBaseDeviceCorrelation(BasicBaseDeviceCorrelation bean);
	 
	public int updateBasicBaseDeviceCorrelation(@Param("params") BasicBaseDeviceCorrelation bean);
	 
	public int delBasicBaseDeviceCorrelationById(int id);
	 
	public BasicBaseDeviceCorrelation getBasicBaseDeviceCorrelationById(int id);
	//插入一条记录
	@Override
	int insert(BasicBaseDeviceCorrelation record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录 
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseDeviceCorrelation entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseDeviceCorrelation entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> updateWrapper);
	//根据 ID  Query   
	@Override
	BasicBaseDeviceCorrelation selectById(Serializable id);
	 
	@Override
	List<BasicBaseDeviceCorrelation> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseDeviceCorrelation> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicBaseDeviceCorrelation selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBaseDeviceCorrelation> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBaseDeviceCorrelation> selectPage(Page<BasicBaseDeviceCorrelation> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);
	public List<BasicBaseDeviceCorrelation> getBasicBaseDeviceCorrelationDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseDeviceCorrelationDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseDeviceCorrelation> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDeviceCorrelation> queryWrapper);


}
