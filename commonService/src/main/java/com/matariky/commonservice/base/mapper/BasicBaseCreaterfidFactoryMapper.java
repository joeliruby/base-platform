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
	//插入一条记录
	@Override
	int insert(BasicBaseCreaterfidFactory record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录 
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseCreaterfidFactory entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseCreaterfidFactory entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> updateWrapper);
	//根据 ID  Query   
	@Override
	BasicBaseCreaterfidFactory selectById(Serializable id);
	 
	@Override
	List<BasicBaseCreaterfidFactory> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseCreaterfidFactory> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicBaseCreaterfidFactory selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBaseCreaterfidFactory> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBaseCreaterfidFactory> selectPage(Page<BasicBaseCreaterfidFactory> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	public List<BasicBaseCreaterfidFactory> getBasicBaseCreaterfidFactoryDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseCreaterfidFactoryDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseCreaterfidFactory> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidFactory> queryWrapper);
	public List<BasicBaseCreaterfidFactory> getBasicBaseCreaterfidFactoryByFactoryId(Long id);

}
