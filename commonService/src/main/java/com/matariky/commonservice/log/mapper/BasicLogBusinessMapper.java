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
import com.matariky.commonservice.log.bean.BasicLogBusiness;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicLogBusinessMapper extends BaseMapper<BasicLogBusiness>{
	 
	public List<BasicLogBusiness> getBasicLogBusinessAll();
	 
	public int getBasicLogBusinessAllCount();
	 
	public int createBasicLogBusiness(BasicLogBusiness bean);
	 
	public int updateBasicLogBusiness(@Param("params") BasicLogBusiness bean);
	 
	public int delBasicLogBusinessById(int id);
	 
	public BasicLogBusiness getBasicLogBusinessById(int id);
	//插入一条记录
	@Override
	int insert(BasicLogBusiness record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录 
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicLogBusiness entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicLogBusiness entity, @Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> updateWrapper);
	//根据 ID  Query   
	@Override
	BasicLogBusiness selectById(Serializable id);
	 
	@Override
	List<BasicLogBusiness> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicLogBusiness> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicLogBusiness selectOne(@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicLogBusiness> selectList(@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicLogBusiness> selectPage(Page<BasicLogBusiness> page, @Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);
	public List<BasicLogBusiness> getBasicLogBusinessDAC(@Param("params") Map<String, Object> params);
	public Long getBasicLogBusinessDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicLogBusiness> page, @Param(Constants.WRAPPER) Wrapper<BasicLogBusiness> queryWrapper);


}
