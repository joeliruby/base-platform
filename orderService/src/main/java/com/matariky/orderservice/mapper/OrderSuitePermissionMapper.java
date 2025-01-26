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

    //插入一条记录
    @Override
    int insert(OrderSuitePermission record);

    //根据 ID 删除
    @Override
    int deleteById(Serializable id);

    //根据 columnMap 条件，删除记录
    @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //根据 entity 条件，删除记录
    @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    //删除（根据ID 批量删除）
    @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    //根据 ID 修改
    @Override
    int updateById(@Param(Constants.ENTITY) OrderSuitePermission entity);

    //根据 whereEntity 条件，Update记录
    @Override
    int update(@Param(Constants.ENTITY) OrderSuitePermission entity, @Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> updateWrapper);

    //根据 ID  Query 
    @Override
    OrderSuitePermission selectById(Serializable id);

     
    @Override
    List<OrderSuitePermission> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

     
    @Override
    List<OrderSuitePermission> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //根据 entity 条件， Query 一条记录
    @Override
    OrderSuitePermission selectOne(@Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    //根据 Wrapper 条件， Query 总记录数
    @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    //根据 entity 条件， Query 全部记录
    @Override
    List<OrderSuitePermission> selectList(@Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    //根据 Wrapper 条件， Query 全部记录
    @Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    //根据 Wrapper 条件， Query 全部记录
    @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    //根据 entity 条件， Query 全部记录（并翻页）
    Page<OrderSuitePermission> selectPage(Page<OrderSuitePermission> page, @Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    public List<OrderSuitePermission> getOrderSuitePermissionDAC(@Param("params") Map<String, Object> params);

    public Long getOrderSuitePermissionDACCount(@Param("params") Map<String, Object> params);

    //根据 Wrapper 条件， Query 全部记录（并翻页）
    Page<Map<String, Object>> selectMapsPage(Page<OrderSuitePermission> page, @Param(Constants.WRAPPER) Wrapper<OrderSuitePermission> queryWrapper);

    int insertOrderSuitePermissions(@Param("orderSuitePermissions") List<OrderSuitePermission> orderSuitePermissions);

    int delOrderSuitePermissionsBySuiteCode(@Param("suiteCode") String suiteCode);


    List<OrderSuitePermission> selectOrderSuitePermissionsByTenantId(@Param("params") Map<String, Object> params);
}
