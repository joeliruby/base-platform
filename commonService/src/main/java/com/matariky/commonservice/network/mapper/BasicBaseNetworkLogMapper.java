package com.matariky.commonservice.network.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.network.bean.BasicBaseNetworkLog;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseNetworkLogMapper extends BaseMapper<BasicBaseNetworkLog>{
	 
	@DataScope(alias="a")
	public List<BasicBaseNetworkLog> getBasicBaseNetworkLogAll(@Param("params") BasicBaseNetworkLog bean);
	 
	@DataScope
	public int getBasicBaseNetworkLogAllCount();
	 
	public int createBasicBaseNetworkLog(BasicBaseNetworkLog bean);
	 
	public int updateBasicBaseNetworkLog(@Param("params") BasicBaseNetworkLog bean);
	 
	public int delBasicBaseNetworkLogById(int id);
	 
	public BasicBaseNetworkLog getBasicBaseNetworkLogById(int id);
	//插入一条记录
	@Override
	int insert(BasicBaseNetworkLog record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseNetworkLog entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseNetworkLog entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> updateWrapper);
	//根据 ID  Query 
	@Override
	BasicBaseNetworkLog selectById(Serializable id);
	 
	@Override
	List<BasicBaseNetworkLog> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseNetworkLog> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicBaseNetworkLog selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBaseNetworkLog> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBaseNetworkLog> selectPage(Page<BasicBaseNetworkLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	public List<BasicBaseNetworkLog> getBasicBaseNetworkLogDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseNetworkLogDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseNetworkLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);


}
