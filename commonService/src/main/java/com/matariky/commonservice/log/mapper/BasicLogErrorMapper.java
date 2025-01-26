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
import com.matariky.commonservice.log.bean.BasicLogError;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicLogErrorMapper extends BaseMapper<BasicLogError>{
	 
	public List<BasicLogError> getBasicLogErrorAll();
	 
	public int getBasicLogErrorAllCount();
	 
	public int createBasicLogError(BasicLogError bean);
	 
	public int updateBasicLogError( @Param("params") BasicLogError bean);
	 
	public int delBasicLogErrorById(int id);
	 
	public BasicLogError getBasicLogErrorById(int id);
	//插入一条记录
	@Override
	int insert(BasicLogError record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录 
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicLogError entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicLogError entity, @Param(Constants.WRAPPER) Wrapper<BasicLogError> updateWrapper);
	//根据 ID  Query   
	@Override
	BasicLogError selectById(Serializable id);
	 
	@Override
	List<BasicLogError> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicLogError> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicLogError selectOne(@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicLogError> selectList(@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicLogError> selectPage(Page<BasicLogError> page, @Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);
	public List<BasicLogError> getBasicLogErrorDAC(@Param("params") Map<String, Object> params);
	public Long getBasicLogErrorDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicLogError> page, @Param(Constants.WRAPPER) Wrapper<BasicLogError> queryWrapper);


}
