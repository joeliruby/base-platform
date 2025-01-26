package com.matariky.orderservice.mapper;


import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import com.matariky.orderservice.bean.OrderInfoRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
* Persistence Interface
* @author AUTOMATION
*/
@Mapper
public interface OrderInfoRecordMapper extends BaseMapper<OrderInfoRecord>{
	 
	public List<OrderInfoRecord> getOrderInfoRecordAll();
	 
	public int getOrderInfoRecordAllCount();
	 
	public int createOrderInfoRecord(OrderInfoRecord bean);
	 
	public int updateOrderInfoRecord(OrderInfoRecord bean);
	 
	public int delOrderInfoRecordById(int id);
	 
	public OrderInfoRecord getOrderInfoRecordById(int id);
	//插入一条记录
	@Override
	int insert(OrderInfoRecord record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录 
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<OrderInfoRecord> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) OrderInfoRecord entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) OrderInfoRecord entity, @Param(Constants.WRAPPER) Wrapper<OrderInfoRecord> updateWrapper);
	//根据 ID  Query   
	@Override
	OrderInfoRecord selectById(Serializable id);
	 
	@Override
	List<OrderInfoRecord> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<OrderInfoRecord> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	OrderInfoRecord selectOne(@Param(Constants.WRAPPER) Wrapper<OrderInfoRecord> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<OrderInfoRecord> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<OrderInfoRecord> selectList(@Param(Constants.WRAPPER) Wrapper<OrderInfoRecord> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<OrderInfoRecord> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<OrderInfoRecord> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<OrderInfoRecord> selectPage(Page<OrderInfoRecord> page, @Param(Constants.WRAPPER) Wrapper<OrderInfoRecord> queryWrapper);
	public List<OrderInfoRecord> getOrderInfoRecordDAC(@Param("params") Map<String, Object> params);
	public Long getOrderInfoRecordDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<OrderInfoRecord> page, @Param(Constants.WRAPPER) Wrapper<OrderInfoRecord> queryWrapper);


}
