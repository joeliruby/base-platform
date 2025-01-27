package com.matariky.orderservice.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;
import com.matariky.orderservice.bean.OrderSuiteConfig;
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
public interface OrderSuiteConfigMapper extends BaseMapper<OrderSuiteConfig> {
     
    public List<OrderSuiteConfig> getOrderSuiteConfigAll();

     
    public int getOrderSuiteConfigAllCount();

     
    public int createOrderSuiteConfig(OrderSuiteConfig bean);

     
    public int updateOrderSuiteConfig(OrderSuiteConfig bean);

     
    public int delOrderSuiteConfigById(int id);

     
    public OrderSuiteConfig getOrderSuiteConfigById(int id);

    //Insert a record
    @Override
    int insert(OrderSuiteConfig record);

    //Delete By ID
    @Override
    int deleteById(Serializable id);

    //Delete By Column Map
    @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //Delete By Entity
    @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    //Delete Batch By IDs
    @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    //Update By IDs
    @Override
    int updateById(@Param(Constants.ENTITY) OrderSuiteConfig entity);

    //Update By Entity
    @Override
    int update(@Param(Constants.ENTITY) OrderSuiteConfig entity, @Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> updateWrapper);

    //Query By ID 
    @Override
    OrderSuiteConfig selectById(Serializable id);

     
    @Override
    List<OrderSuiteConfig> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

     
    @Override
    List<OrderSuiteConfig> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //Query One By Entity
    @Override
    OrderSuiteConfig selectOne(@Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    //Query Count By Wrapper
    @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    //Query All By Entity
    @Override
    List<OrderSuiteConfig> selectList(@Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    //Query All Maps By Wrapper
    @Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    //Query All Maps By Wrapper
    @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    //Query All By Entity（ With Pagination ）
    Page<OrderSuiteConfig> selectPage(Page<OrderSuiteConfig> page, @Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    public List<OrderSuiteConfig> getOrderSuiteConfigDAC(@Param("params") Map<String, Object> params);

    public Long getOrderSuiteConfigDACCount(@Param("params") Map<String, Object> params);

    //Query All Maps By Wrapper（ With Pagination ）
    Page<Map<String, Object>> selectMapsPage(Page<OrderSuiteConfig> page, @Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);


    int insertOrderSuiteConfigs(@Param("orderSuiteConfigs")List<OrderSuiteConfig> orderSuiteConfigs);

    int delOrderSuiteConfigBySuiteCode(@Param("suiteCode")String suiteCode);


    OrderSuiteConfig getPriceByParameter(@Param("params") Map<String, Object> params);
}
