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

    //插入一条记录
    @Override
    int insert(OrderInfo record);

    //根据 ID 删除
    @Override
    int deleteById(Serializable id);

    //根据 columnMap 条件，删除记录
    @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //根据 entity 条件，删除记录
    @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    //删除（根据ID 批量删除）
    @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    //根据 ID 修改
    @Override
    int updateById(@Param(Constants.ENTITY) OrderInfo entity);

    //根据 whereEntity 条件，Update记录
    @Override
    int update(@Param(Constants.ENTITY) OrderInfo entity, @Param(Constants.WRAPPER) Wrapper<OrderInfo> updateWrapper);

    //根据 ID  Query 
    @Override
    OrderInfo selectById(Serializable id);

     
    @Override
    List<OrderInfo> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

     
    @Override
    List<OrderInfo> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
//
//    //根据 entity 条件， Query 一条记录
//    @Override
//    OrderInfo selectOne(@Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    //根据 Wrapper 条件， Query 总记录数
    @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    //根据 entity 条件， Query 全部记录
    @Override
    List<OrderInfo> selectList(@Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    //根据 Wrapper 条件， Query 全部记录
    @Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    //根据 Wrapper 条件， Query 全部记录
    @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    //根据 entity 条件， Query 全部记录（并翻页）
    Page<OrderInfo> selectPage(Page<OrderInfo> page, @Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);
    @DataScope
    public List<OrderInfoPageVo> getOrderInfoDAC(QueryTapeOrderInfoParam params);
    @DataScope("a")
    public Long getOrderInfoDACCount(QueryTapeOrderInfoParam params);

    //根据 Wrapper 条件， Query 全部记录（并翻页）
    Page<Map<String, Object>> selectMapsPage(Page<OrderInfo> page, @Param(Constants.WRAPPER) Wrapper<OrderInfo> queryWrapper);

    public Long selectCountByOrderTenantId(@Param("orderTenantId") String orderTenantId);

    public List<OrderInfo> selectOrderInfoByOrderTenantId(@Param("params") Map<String, Object> params);


    public List<OrderInfo> selectByOrderTenantId(@Param("params") Map<String, Object> params);

    public int updateUserOrderCodeByTenantId(@Param("params") Map<String, Object> params);

    public int updateUserOrderCodeByOrderCode(@Param("params") Map<String, Object> params);


}
