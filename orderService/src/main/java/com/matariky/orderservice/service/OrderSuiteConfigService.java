package com.matariky.orderservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.commonservice.upload.utils.DateUtils;
import com.matariky.constant.PermissionConstant;
import com.matariky.exception.QslException;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.orderservice.bean.OrderInfo;
import com.matariky.orderservice.bean.OrderSuiteConfig;
import com.matariky.orderservice.mapper.OrderInfoMapper;
import com.matariky.orderservice.mapper.OrderSuiteConfigMapper;
import com.matariky.utils.DateUtil;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.math.RoundingMode;

/**
 * Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class OrderSuiteConfigService extends BaseServiceImpl<OrderSuiteConfigMapper, OrderSuiteConfig> {

    @Autowired
    private OrderSuiteConfigMapper orderSuiteConfigMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public List<OrderSuiteConfig> getOrderSuiteConfigAll() {
        return orderSuiteConfigMapper.getOrderSuiteConfigAll();
    }

    public int getOrderSuiteConfigAllCount() {
        return orderSuiteConfigMapper.getOrderSuiteConfigAllCount();
    }

    public int createOrderSuiteConfig(OrderSuiteConfig bean) {
        return orderSuiteConfigMapper.createOrderSuiteConfig(bean);
    }

    public int createOrderSuiteConfigWithOrg(OrderSuiteConfig bean, HttpServletRequest request) {
        return orderSuiteConfigMapper.createOrderSuiteConfig(bean);
    }

    public int updateOrderSuiteConfig(OrderSuiteConfig bean) {
        return orderSuiteConfigMapper.updateById(bean);
    }

    public int delOrderSuiteConfigById(int id) {
        return orderSuiteConfigMapper.delOrderSuiteConfigById(id);
    }

    public OrderSuiteConfig getOrderSuiteConfigById(int id) {
        return orderSuiteConfigMapper.getOrderSuiteConfigById(id);
    }

    public List<OrderSuiteConfig> getOrderSuiteConfigDAC(Map<String, Object> params, HttpServletRequest request) {
        strategyCodeToParams(params, request);
        return orderSuiteConfigMapper.getOrderSuiteConfigDAC(params);
    }

    public Long getOrderSuiteConfigDACCount(Map<String, Object> params, HttpServletRequest request) {

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

        return orderSuiteConfigMapper.getOrderSuiteConfigDACCount(params);
    }

    public List<OrderSuiteConfig> getOrderSuiteConfigs(OrderSuiteConfig orderSuiteConfig) {
        QueryWrapper<OrderSuiteConfig> queryWrapper = new QueryWrapper<OrderSuiteConfig>();
        queryWrapper.eq("suite_code", orderSuiteConfig.getSuiteCode());
        return orderSuiteConfigMapper.selectList(queryWrapper);
    }

    public String getOrderSuiteConfigByParams(Map<String, Object> params) {

        OrderSuiteConfig orderSuiteConfig = orderSuiteConfigMapper.getPriceByParameter(params);
        if (orderSuiteConfig != null) {
            String startTime = "";
            if (params.get("startTime") != null) {
                startTime = params.get("startTime").toString();
            } else {
                OrderInfo orderInfo = orderInfoMapper.selectById(
                        params.get("orderId") != null ? Long.parseLong(params.get("orderId").toString()) : 0);
                if (orderInfo != null) {
                    if (params.get("endTime") != null) {
                        if (orderInfo.getExpirationEndTime() >= DateUtils
                                .parse(params.get("endTime").toString(), "yyyy-MM-dd").getTime()) {
                            throw new QslException(MessageKey.ORDER_SELECTION_DATE_THAN_EXPIRATION_DATE);
                        }
                    }
                    Date date = new Date(orderInfo.getExpirationEndTime());
                    // Date d1 = DateUtils.addDateDays(date,1);
                    startTime = DateUtils.format(date, "yyyy-MM-dd");

                }
            }
            String endTime = params.get("endTime").toString();
            BigDecimal b = new BigDecimal("0");
            if (StringUtil.isNotEmpty(startTime) && StringUtil.isNotEmpty(endTime)) {
                int year = DateUtil.compareDate(startTime, endTime, 2);
                BigDecimal b1 = new BigDecimal("1");
                if (year != 0) {
                    b1 = new BigDecimal(year);
                }
                if (params.get("chargeMode") != null) {
                    if (params.get("chargeMode").toString().equals("1")) {
                        b = b1.multiply(orderSuiteConfig.getYearPrice());
                    } else {
                        if (params.get("accountNumber") != null) {
                            Integer accountNumber = Integer.parseInt(params.get("accountNumber").toString());
                            b = b1.multiply(orderSuiteConfig.getAveragePrice())
                                    .multiply(BigDecimal.valueOf(accountNumber));
                        }
                    }
                }
            }

            return b.setScale(2, RoundingMode.HALF_UP).toString();
        } else {
            throw new QslException(MessageKey.ORDER_ACCOUNTS_NUMBER_RANGE_OUT);
        }
    }

    public OrderSuiteConfig getOrderSuiteConfigByCode(Map<String, Object> params) {
        OrderSuiteConfig info = orderSuiteConfigMapper.getPriceByParameter(params);
        if (info == null) {
            throw new QslException(MessageKey.ACCOUNT_NUMBER_NOT_RIGHT);
        }
        return info;
    }
}
