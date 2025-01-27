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
	//Insert a record
	@Override
	int insert(BasicBaseNetworkLog record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseNetworkLog entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseNetworkLog entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> updateWrapper);
	//Query By ID 
	@Override
	BasicBaseNetworkLog selectById(Serializable id);
	 
	@Override
	List<BasicBaseNetworkLog> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseNetworkLog> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	BasicBaseNetworkLog selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	//Query All By Entity
	@Override
	List<BasicBaseNetworkLog> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	//Query All By Entity（ With Pagination ）
	 Page<BasicBaseNetworkLog> selectPage(Page<BasicBaseNetworkLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);
	public List<BasicBaseNetworkLog> getBasicBaseNetworkLogDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseNetworkLogDACCount(@Param("params") Map<String, Object> params);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseNetworkLog> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseNetworkLog> queryWrapper);


}
