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
	//插入一条记录
	@Override
	int insert(OrderSuite record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录 
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) OrderSuite entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) OrderSuite entity, @Param(Constants.WRAPPER) Wrapper<OrderSuite> updateWrapper);
	//根据 ID  Query   
	@Override
	OrderSuite selectById(Serializable id);
	 
	@Override
	List<OrderSuite> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<OrderSuite> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	OrderSuite selectOne(@Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<OrderSuite> selectList(@Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<OrderSuite> selectPage(Page<OrderSuite> page, @Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);
	@DataScope
	public List<OrderSuite> getOrderSuiteDAC(QueryTapeOrderSuiteParam params);
	@DataScope
	public Long getOrderSuiteDACCount(QueryTapeOrderSuiteParam params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<OrderSuite> page, @Param(Constants.WRAPPER) Wrapper<OrderSuite> queryWrapper);

	int insertOrderSuites(@Param("orderSuites")List<OrderSuite> orderSuites);

	public List<OrderSuite>  selectOrderSuitesIsUsed(@Param("params") Map<String, Object> params);
}
