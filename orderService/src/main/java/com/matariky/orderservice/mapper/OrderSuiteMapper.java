package com.matariky.orderservice.mapper;


import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import com.matariky.annotation.DataScope;
import com.matariky.orderservice.bean.OrderSuite;
import com.matariky.orderservice.param.QueryTapeOrderSuiteParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
* Persistence Interface
* @author AUTOMATION
*/
@Mapper
public interface OrderSuiteMapper extends BaseMapper<OrderSuite>{
	 
	public List<OrderSuite> getOrderSuiteAll();
	 
	public int getOrderSuiteAllCount();
	 
	public int createOrderSuite(OrderSuite bean);
	 
	public int updateOrderSuite(OrderSuite bean);
	 
	public int delOrderSuiteById(int id);
	 
	public OrderSuite getOrderSuiteById(Long id);
	//Insert a record
	@Override
	int insert(OrderSuite record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) OrderSuite entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) OrderSuite entity, @Param(Constants.WRAPPER) Wrapper<OrderSuite> updateWrapper);
	//Query By ID   
	@Override
	OrderSuite selectById(Serializable id);
	 
	@Override
	List<OrderSuite> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<OrderSuite> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	OrderSuite selectOne(@Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	//Query All By Entity
	@Override
	List<OrderSuite> selectList(@Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	//Query All By Entity（ With Pagination ）
	 Page<OrderSuite> selectPage(Page<OrderSuite> page, @Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	@DataScope
	public List<OrderSuite> getOrderSuiteDAC(QueryTapeOrderSuiteParam params);
	@DataScope
	public Long getOrderSuiteDACCount(QueryTapeOrderSuiteParam params);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<OrderSuite> page, @Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);

	int insertOrderSuites(@Param("orderSuites")List<OrderSuite> orderSuites);

	public List<OrderSuite>  selectOrderSuitesIsUsed(@Param("params") Map<String, Object> params);
}
