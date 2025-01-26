package com.matariky.commonservice.printlog.mapper;

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

import com.matariky.annotation.DataScope;
import com.matariky.commonservice.printlog.bean.BasicBasePrintLog;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBasePrintLogMapper extends BaseMapper<BasicBasePrintLog>{
	 
	@DataScope(alias="a")
	public List<BasicBasePrintLog> getBasicBasePrintLogAll(@Param("params") BasicBasePrintLog bean);
	 
	@DataScope
	public int getBasicBasePrintLogAllCount();
	 
	public int createBasicBasePrintLog(BasicBasePrintLog bean);
	 
	public int updateBasicBasePrintLog(@Param("params") BasicBasePrintLog bean);
	 
	public int delBasicBasePrintLogById(int id);
	 
	public BasicBasePrintLog getBasicBasePrintLogById(int id);
	//插入一条记录
	@Override
	int insert(BasicBasePrintLog record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBasePrintLog entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicBasePrintLog entity, @Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> updateWrapper);
	//根据 ID  Query 
	@Override
	BasicBasePrintLog selectById(Serializable id);
	 
	@Override
	List<BasicBasePrintLog> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBasePrintLog> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicBasePrintLog selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBasePrintLog> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBasePrintLog> selectPage(Page<BasicBasePrintLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);
	public List<BasicBasePrintLog> getBasicBasePrintLogDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBasePrintLogDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBasePrintLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBasePrintLog> queryWrapper);


}
