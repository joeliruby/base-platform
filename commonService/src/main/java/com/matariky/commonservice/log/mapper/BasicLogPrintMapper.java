package com.matariky.commonservice.log.mapper;

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
import com.matariky.commonservice.log.bean.BasicLogPrint;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicLogPrintMapper extends BaseMapper<BasicLogPrint>{
	 
	public List<BasicLogPrint> getBasicLogPrintAll();
	 
	public int getBasicLogPrintAllCount();
	 
	public int createBasicLogPrint(BasicLogPrint bean);
	 
	public int updateBasicLogPrint(@Param("params") BasicLogPrint bean);
	 
	public int delBasicLogPrintById(int id);
	 
	public BasicLogPrint getBasicLogPrintById(int id);
	//插入一条记录
	@Override
	int insert(BasicLogPrint record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录 
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicLogPrint entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicLogPrint entity, @Param(Constants.WRAPPER) Wrapper<BasicLogPrint> updateWrapper);
	//根据 ID  Query   
	@Override
	BasicLogPrint selectById(Serializable id);
	 
	@Override
	List<BasicLogPrint> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicLogPrint> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicLogPrint selectOne(@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicLogPrint> selectList(@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicLogPrint> selectPage(Page<BasicLogPrint> page, @Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);
	public List<BasicLogPrint> getBasicLogPrintDAC(@Param("params") Map<String, Object> params);
	public Long getBasicLogPrintDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicLogPrint> page, @Param(Constants.WRAPPER) Wrapper<BasicLogPrint> queryWrapper);


}
