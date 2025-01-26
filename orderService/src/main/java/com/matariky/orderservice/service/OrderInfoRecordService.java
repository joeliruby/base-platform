package com.matariky.orderservice.service;


import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.orderservice.bean.OrderInfoRecord;
import com.matariky.orderservice.mapper.OrderInfoRecordMapper;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class OrderInfoRecordService extends BaseServiceImpl<OrderInfoRecordMapper, OrderInfoRecord> implements BaseService<OrderInfoRecord> {

    @Autowired
    private OrderInfoRecordMapper orderInfoRecordMapper;

     
    public List<OrderInfoRecord> getOrderInfoRecordAll() {
        return orderInfoRecordMapper.getOrderInfoRecordAll();
    }

     
    public int getOrderInfoRecordAllCount() {
        return orderInfoRecordMapper.getOrderInfoRecordAllCount();
    }

     
    public int createOrderInfoRecord(OrderInfoRecord bean) {
        return orderInfoRecordMapper.createOrderInfoRecord(bean);
    }

     
     
    public int createOrderInfoRecordWithOrg(OrderInfoRecord bean, HttpServletRequest request) {
        bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        return orderInfoRecordMapper.createOrderInfoRecord(bean);
    }

    public int updateOrderInfoRecord(OrderInfoRecord bean) {
        return orderInfoRecordMapper.updateById(bean);
    }

     
    public int delOrderInfoRecordById(int id) {
        return orderInfoRecordMapper.delOrderInfoRecordById(id);
    }

     
    public OrderInfoRecord getOrderInfoRecordById(int id) {
        return orderInfoRecordMapper.getOrderInfoRecordById(id);
    }

    public List<OrderInfoRecord> getOrderInfoRecordDAC(Map<String, Object> params, HttpServletRequest request) {
        return orderInfoRecordMapper.getOrderInfoRecordDAC(params);
    }

    public Long getOrderInfoRecordDACCount(Map<String, Object> params, HttpServletRequest request) {
        return orderInfoRecordMapper.getOrderInfoRecordDACCount(params);
    }

}
