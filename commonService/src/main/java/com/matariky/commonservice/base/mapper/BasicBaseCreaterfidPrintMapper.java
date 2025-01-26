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
	//插入一条记录
	@Override
	int insert(BasicBaseCreaterfidPrint record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录 
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseCreaterfidPrint entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseCreaterfidPrint entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> updateWrapper);
	//根据 ID  Query   
	@Override
	BasicBaseCreaterfidPrint selectById(Serializable id);
	 
	@Override
	List<BasicBaseCreaterfidPrint> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseCreaterfidPrint> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicBaseCreaterfidPrint selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBaseCreaterfidPrint> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBaseCreaterfidPrint> selectPage(Page<BasicBaseCreaterfidPrint> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);
	public List<BasicBaseCreaterfidPrint> getBasicBaseCreaterfidPrintDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseCreaterfidPrintDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseCreaterfidPrint> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseCreaterfidPrint> queryWrapper);


}
