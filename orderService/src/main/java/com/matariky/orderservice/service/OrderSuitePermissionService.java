package com.matariky.orderservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import com.matariky.constant.PermissionConstant;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.orderservice.bean.OrderSuitePermission;
import com.matariky.orderservice.mapper.OrderSuitePermissionMapper;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class OrderSuitePermissionService extends BaseServiceImpl<OrderSuitePermissionMapper, OrderSuitePermission> {

    @Autowired
    private OrderSuitePermissionMapper orderSuitePermissionMapper;

    public List<OrderSuitePermission> getOrderSuitePermissionAll() {
        return orderSuitePermissionMapper.getOrderSuitePermissionAll();
    }

    public int getOrderSuitePermissionAllCount() {
        return orderSuitePermissionMapper.getOrderSuitePermissionAllCount();
    }

    public int createOrderSuitePermission(OrderSuitePermission bean) {
        return orderSuitePermissionMapper.createOrderSuitePermission(bean);
    }

    public int createOrderSuitePermissionWithOrg(OrderSuitePermission bean, HttpServletRequest request) {
        return orderSuitePermissionMapper.createOrderSuitePermission(bean);
    }

    public int updateOrderSuitePermission(OrderSuitePermission bean) {
        return orderSuitePermissionMapper.updateById(bean);
    }

    public int delOrderSuitePermissionById(int id) {
        return orderSuitePermissionMapper.delOrderSuitePermissionById(id);
    }

    public OrderSuitePermission getOrderSuitePermissionById(int id) {
        return orderSuitePermissionMapper.getOrderSuitePermissionById(id);
    }

    public List<OrderSuitePermission> getOrderSuitePermissionDAC(Map<String, Object> params,
            HttpServletRequest request) {
        strategyCodeToParams(params, request);
        return orderSuitePermissionMapper.getOrderSuitePermissionDAC(params);
    }

    public Long getOrderSuitePermissionDACCount(Map<String, Object> params, HttpServletRequest request) {

        String strategyCode = (String) params.get("strategyCode");
        if (StringUtil.isEmpty(strategyCode))
            strategyCode = PermissionConstant.COMMON_DATA_ACCESS_PRIVATE;// By default only visible by owner
        switch (strategyCode) {
            case PermissionConstant.COMMON_DATA_ACCESS_PRIVATE:// Visible to owner with special sharing rules
                Map<String, List<String>> sharingOrgCodes0 = extractedSharingOrgCodes(request);
                params.put("selfOrgCode", TokenUtils.extractSelfOrgCode(request));
                params.putAll(sharingOrgCodes0);
                break;
            case PermissionConstant.COMMON_DATA_ACCESS_ALL:// All visible to all without special sharing rules
                break;
            case PermissionConstant.COMMON_DATA_ACCESS_ORG:// Visible to organizations of same or upper level
                Map<String, List<String>> sharingOrgCodes3 = extractedSharingOrgCodes(request);
                params.put("orgCode", TokenUtils.extractOrgCode(request));
                params.putAll(sharingOrgCodes3);
                break;
            case PermissionConstant.COMMON_DATA_ACCESS_LEVEL:// Visible to organizations of same level with special
                                                             // sharing rules
                Map<String, List<String>> sharingOrgCodes2 = extractedSharingOrgCodes(request);
                params.put("orgCode", TokenUtils.extractOrgCode(request));
                params.putAll(sharingOrgCodes2);
                break;
            default:
                break;
        }

        return orderSuitePermissionMapper.getOrderSuitePermissionDACCount(params);
    }

    public List<OrderSuitePermission> getOrderSuitePermissions(OrderSuitePermission orderSuitePermission) {
        QueryWrapper<OrderSuitePermission> queryWrapper = new QueryWrapper<OrderSuitePermission>();
        queryWrapper.eq("suite_code", orderSuitePermission.getSuiteCode());

        List<OrderSuitePermission> orderSuitePermissions = orderSuitePermissionMapper.selectList(queryWrapper);
        return orderSuitePermissions;

    }
}
