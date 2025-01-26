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

    //插入一条记录
    @Override
    int insert(OrderSuiteConfig record);

    //根据 ID 删除
    @Override
    int deleteById(Serializable id);

    //根据 columnMap 条件，删除记录
    @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //根据 entity 条件，删除记录
    @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    //删除（根据ID 批量删除）
    @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    //根据 ID 修改
    @Override
    int updateById(@Param(Constants.ENTITY) OrderSuiteConfig entity);

    //根据 whereEntity 条件，Update记录
    @Override
    int update(@Param(Constants.ENTITY) OrderSuiteConfig entity, @Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> updateWrapper);

    //根据 ID  Query 
    @Override
    OrderSuiteConfig selectById(Serializable id);

     
    @Override
    List<OrderSuiteConfig> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

     
    @Override
    List<OrderSuiteConfig> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //根据 entity 条件， Query 一条记录
    @Override
    OrderSuiteConfig selectOne(@Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    //根据 Wrapper 条件， Query 总记录数
    @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    //根据 entity 条件， Query 全部记录
    @Override
    List<OrderSuiteConfig> selectList(@Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    //根据 Wrapper 条件， Query 全部记录
    @Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    //根据 Wrapper 条件， Query 全部记录
    @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    //根据 entity 条件， Query 全部记录（并翻页）
    Page<OrderSuiteConfig> selectPage(Page<OrderSuiteConfig> page, @Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);

    public List<OrderSuiteConfig> getOrderSuiteConfigDAC(@Param("params") Map<String, Object> params);

    public Long getOrderSuiteConfigDACCount(@Param("params") Map<String, Object> params);

    //根据 Wrapper 条件， Query 全部记录（并翻页）
    Page<Map<String, Object>> selectMapsPage(Page<OrderSuiteConfig> page, @Param(Constants.WRAPPER) Wrapper<OrderSuiteConfig> queryWrapper);


    int insertOrderSuiteConfigs(@Param("orderSuiteConfigs")List<OrderSuiteConfig> orderSuiteConfigs);

    int delOrderSuiteConfigBySuiteCode(@Param("suiteCode")String suiteCode);


    OrderSuiteConfig getPriceByParameter(@Param("params") Map<String, Object> params);
}
