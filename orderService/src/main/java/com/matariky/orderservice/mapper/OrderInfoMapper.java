package com.matariky.orderservice.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;
import com.matariky.annotation.DataScope;
import com.matariky.orderservice.bean.OrderInfo;
import com.matariky.orderservice.param.QueryTapeOrderInfoParam;
import com.matariky.orderservice.vo.OrderInfoPageVo;
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
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
     
    public List<OrderInfo> getOrderInfoAll();

     
    public int getOrderInfoAllCount();

     
    public int createOrderInfo(OrderInfo bean);

     
    public int updateOrderInfo(OrderInfo bean);

     
    public int delOrderInfoById(Long id);

     
    public OrderInfo getOrderInfoById(int id);

    //Insert a record
    @Override
    int insert(OrderInfo record);

    //Delete By ID
    @Override
    int deleteById(Serializable id);

    //Delete By Column Map
    @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //Delete By Entity
    @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    //Delete Batch By IDs
    @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    //Update By IDs
    @Override
    int updateById(@Param(Constants.ENTITY) OrderInfo entity);

    //Update By Entity
    @Override
    int update(@Param(Constants.ENTITY) OrderInfo entity, @Param(Constants.WRAPPER) Wrapper<OrderInfo> updateWrapper);

    //Query By ID 
    @Override
    OrderInfo selectById(Serializable id);

     
    @Override
    List<OrderInfo> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

     
    @Override
    List<OrderInfo> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
//
//    //Query One By Entity
//    @Override
//    OrderInfo selectOne(@Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    //Query Count By Wrapper
    @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    //Query All By Entity
    @Override
    List<OrderInfo> selectList(@Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    //Query All Maps By Wrapper
    @Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    //Query All Maps By Wrapper
    @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    //Query All By Entity（ With Pagination ）
    Page<OrderInfo> selectPage(Page<OrderInfo> page, @Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);
    @DataScope
    public List<OrderInfoPageVo> getOrderInfoDAC(QueryTapeOrderInfoParam params);
    @DataScope("a")
    public Long getOrderInfoDACCount(QueryTapeOrderInfoParam params);

    //Query All Maps By Wrapper（ With Pagination ）
    Page<Map<String, Object>> selectMapsPage(Page<OrderInfo> page, @Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    public Long selectCountByOrderTenantId(@Param("orderTenantId") String orderTenantId);

    public List<OrderInfo> selectOrderInfoByOrderTenantId(@Param("params") Map<String, Object> params);


    public List<OrderInfo> selectByOrderTenantId(@Param("params") Map<String, Object> params);

    public int updateUserOrderCodeByTenantId(@Param("params") Map<String, Object> params);

    public int updateUserOrderCodeByOrderCode(@Param("params") Map<String, Object> params);


}
