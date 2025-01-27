package com.matariky.orderservice.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;
import com.matariky.orderservice.bean.OrderSuitePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
@Mapper
public interface OrderSuitePermissionMapper extends BaseMapper<OrderSuitePermission> {
     
    public List<OrderSuitePermission> getOrderSuitePermissionAll();

     
    public int getOrderSuitePermissionAllCount();

     
    public int createOrderSuitePermission(OrderSuitePermission bean);

     
    public int updateOrderSuitePermission(OrderSuitePermission bean);

     
    public int delOrderSuitePermissionById(int id);

     
    public OrderSuitePermission getOrderSuitePermissionById(int id);

    //Insert a record
    @Override
    int insert(OrderSuitePermission record);

    //Delete By ID
    @Override
    int deleteById(Serializable id);

    //Delete By Column Map
    @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //Delete By Entity
    @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    //Delete Batch By IDs
    @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    //Update By IDs
    @Override
    int updateById(@Param(Constants.ENTITY) OrderSuitePermission entity);

    //Update By Entity
    @Override
    int update(@Param(Constants.ENTITY) OrderSuitePermission entity, @Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> updateWrapper);

    //Query By ID 
    @Override
    OrderSuitePermission selectById(Serializable id);

     
    @Override
    List<OrderSuitePermission> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

     
    @Override
    List<OrderSuitePermission> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //Query One By Entity
    @Override
    OrderSuitePermission selectOne(@Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    //Query Count By Wrapper
    @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    //Query All By Entity
    @Override
    List<OrderSuitePermission> selectList(@Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    //Query All Maps By Wrapper
    @Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    //Query All Maps By Wrapper
    @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    //Query All By Entity（ With Pagination ）
    Page<OrderSuitePermission> selectPage(Page<OrderSuitePermission> page, @Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    public List<OrderSuitePermission> getOrderSuitePermissionDAC(@Param("params") Map<String, Object> params);

    public Long getOrderSuitePermissionDACCount(@Param("params") Map<String, Object> params);

    //Query All Maps By Wrapper（ With Pagination ）
    Page<Map<String, Object>> selectMapsPage(Page<OrderSuitePermission> page, @Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    int insertOrderSuitePermissions(@Param("orderSuitePermissions") List<OrderSuitePermission> orderSuitePermissions);

    int delOrderSuitePermissionsBySuiteCode(@Param("suiteCode") String suiteCode);


    List<OrderSuitePermission> selectOrderSuitePermissionsByTenantId(@Param("params") Map<String, Object> params);
}
