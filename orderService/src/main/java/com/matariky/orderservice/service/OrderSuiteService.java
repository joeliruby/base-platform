package com.matariky.orderservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.orderservice.bean.OrderInfo;
import com.matariky.orderservice.bean.OrderSuite;
import com.matariky.orderservice.bean.OrderSuiteConfig;
import com.matariky.orderservice.bean.OrderSuitePermission;
import com.matariky.orderservice.mapper.OrderInfoMapper;
import com.matariky.orderservice.mapper.OrderSuiteConfigMapper;
import com.matariky.orderservice.mapper.OrderSuiteMapper;
import com.matariky.orderservice.mapper.OrderSuitePermissionMapper;
import com.matariky.orderservice.param.QueryTapeOrderSuiteParam;
import com.matariky.orderservice.vo.OrderSuiteAddVo;
import com.matariky.orderservice.vo.OrderSuiteConfigVo;
import com.matariky.orderservice.vo.OrderSuiteEditVo;
import com.matariky.utils.CodeUtils;
import com.matariky.utils.TokenUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class OrderSuiteService extends BaseServiceImpl<OrderSuiteMapper, OrderSuite> implements BaseService<OrderSuite> {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderSuiteMapper orderSuiteMapper;

    @Autowired
    private OrderSuiteConfigMapper orderSuiteConfigMapper;

    @Autowired
    private OrderSuitePermissionMapper orderSuitePermissionMapper;
     
    public List<OrderSuite> getOrderSuiteAll() {
        return orderSuiteMapper.getOrderSuiteAll();
    }

     
    public int getOrderSuiteAllCount() {
        return orderSuiteMapper.getOrderSuiteAllCount();
    }

     
    public int createOrderSuite(OrderSuite bean) {
        return orderSuiteMapper.createOrderSuite(bean);
    }

     
     
    public int createOrderSuiteWithOrg(OrderSuite bean, HttpServletRequest request) {
        bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        return orderSuiteMapper.createOrderSuite(bean);
    }


    public boolean saveOrderSuite(OrderSuiteAddVo orderSuiteAddVo, HttpServletRequest request){

        if(CollectionUtils.isNotEmpty(orderSuiteAddVo.getOrderSuiteConfigVos())){
            orderSuiteAddVo.getOrderSuiteConfigVos().stream().forEach(item -> {
                if(item.getNumberEnd()<item.getNumberStart()){
                    throw new QslException(MessageKey.ORDER_USING_NUMBER_CONFIG_THAN_ENDING_NUMBER);
                }
                List<OrderSuiteConfigVo> configVos1 = orderSuiteAddVo.getOrderSuiteConfigVos().stream()
                        .filter(i ->
                                (i.getNumberStart()>=item.getNumberStart() && i.getNumberStart()<=item.getNumberStart()) ||
                                        (i.getNumberEnd()>=item.getNumberStart() && i.getNumberEnd()<= item.getNumberEnd())
                        ).collect(Collectors.toList());

                if(CollectionUtils.isNotEmpty(configVos1) && configVos1.size() > 1){
                    throw new QslException(MessageKey.ORDER_USING_NUMBER_CONFIG_OVERLAPS);
                }

            });
        }
        QueryWrapper<OrderSuite> queryWrapper = new QueryWrapper<OrderSuite>();
        queryWrapper.eq("suite_name",orderSuiteAddVo.getSuiteName());
        List<OrderSuite> orderSuites = orderSuiteMapper.selectList(queryWrapper);
        if(CollectionUtils.isNotEmpty(orderSuites)){
            throw new QslException(MessageKey.ORDER_SUITE_NAME_IS_USED);
        }
        orderSuiteAddVo.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        orderSuiteAddVo.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));


        orderSuiteAddVo.setCreateTime(Calendar.getInstance().getTimeInMillis());
        orderSuiteAddVo.setUpdateTime(Calendar.getInstance().getTimeInMillis());
        orderSuiteAddVo.setDeleteTime(0L);

        OrderSuite orderSuite = new OrderSuite();
        BeanUtils.copyProperties(orderSuiteAddVo,orderSuite);
        orderSuite.setSuiteCode("S"+ CodeUtils.getNum(7));

        orderSuiteMapper.createOrderSuite(orderSuite);

        if(CollectionUtils.isNotEmpty(orderSuiteAddVo.getOrderSuiteConfigVos())){

            List<OrderSuiteConfig> orderSuiteConfigs = orderSuiteAddVo.getOrderSuiteConfigVos().stream().map(item -> {
                OrderSuiteConfig orderSuiteConfig = new OrderSuiteConfig();
                BeanUtils.copyProperties(item,orderSuiteConfig);

                if(item.getYearPrice() == null){
                    orderSuiteConfig.setYearPrice(BigDecimal.valueOf(0));
                }

                if(item.getAveragePrice() == null){
                    orderSuiteConfig.setAveragePrice(BigDecimal.valueOf(0));
                }
                orderSuiteConfig.setSuiteCode(orderSuite.getSuiteCode());
                orderSuiteConfig.setSuiteConfigCode("SC"+ CodeUtils.getNum(7));
                return orderSuiteConfig;
            }).collect(Collectors.toList());
            orderSuiteConfigMapper.insertOrderSuiteConfigs(orderSuiteConfigs);
        }
        if(StringUtil.isNotEmpty(orderSuiteAddVo.getOrderSuitePermissionVos())){
            String[] strArry = orderSuiteAddVo.getOrderSuitePermissionVos().split(",");
            List<OrderSuitePermission> orderSuitePermissions = Arrays.stream(strArry).map(item -> {
                OrderSuitePermission orderSuitePermission = new OrderSuitePermission();
                orderSuitePermission.setPermissionId(Long.parseLong(item));
                orderSuitePermission.setSuiteCode(orderSuite.getSuiteCode());
                return orderSuitePermission;
            }).collect(Collectors.toList());

            orderSuitePermissionMapper.insertOrderSuitePermissions(orderSuitePermissions);
        }
        return true;
    }
    public boolean updateOrderSuiteStatus(Long suiteId,String suiteStatus) {
        OrderSuite orderSuite = orderSuiteMapper.getOrderSuiteById(suiteId);
        if(orderSuite != null){
            orderSuite.setSuiteStatus(suiteStatus);
            orderSuiteMapper.updateOrderSuite(orderSuite);
        }
        return true;
    }
    public boolean updateOrderSuite(OrderSuiteEditVo orderSuiteEditVo, HttpServletRequest request) {

        if(CollectionUtils.isNotEmpty(orderSuiteEditVo.getOrderSuiteConfigVos())){
            orderSuiteEditVo.getOrderSuiteConfigVos().stream().forEach(item -> {
                if(item.getNumberEnd()<item.getNumberStart()){
                    throw new QslException(MessageKey.ORDER_USER_NUMBER_MUSTBE);
                }
                List<OrderSuiteConfigVo> configVos1 = orderSuiteEditVo.getOrderSuiteConfigVos().stream()
                        .filter(i ->
                                (i.getNumberStart()>=item.getNumberStart() && i.getNumberStart()<=item.getNumberStart()) ||
                                        (i.getNumberEnd()>=item.getNumberStart() && i.getNumberEnd()<= item.getNumberEnd())
                        ).collect(Collectors.toList());

                if(CollectionUtils.isNotEmpty(configVos1) && configVos1.size() > 1){
                    throw new QslException(MessageKey.ORDER_USER_NUMBER_OVERLAP);
                }

            });
        }


        OrderSuite orderSuite = orderSuiteMapper.getOrderSuiteById(orderSuiteEditVo.getId());
        if(orderSuite != null){

            QueryWrapper<OrderSuite> queryWrapper = new QueryWrapper<OrderSuite>();
            queryWrapper.eq("suite_name",orderSuiteEditVo.getSuiteName());
            List<OrderSuite> orderSuites1 = orderSuiteMapper.selectList(queryWrapper);
            if(CollectionUtils.isNotEmpty(orderSuites1)){
                List<OrderSuite> orderSuites2 = orderSuites1.stream().filter(i -> !i.getId().toString().equals(orderSuite.getId().toString())).collect(Collectors.toList());
                if(CollectionUtils.isNotEmpty(orderSuites2)) {
                    throw new QslException(MessageKey.ORDER_SUITE_NAME_IS_USED);
                }
            }

            if(!orderSuiteEditVo.getOperateType().equals("auth")) {
                Map<String, Object> params = new HashMap<>();
                params.put("suiteCode", orderSuite.getSuiteCode());
                List<OrderSuite> orderSuites = orderSuiteMapper.selectOrderSuitesIsUsed(params);
                if (CollectionUtils.isNotEmpty(orderSuites)) {
                    throw new QslException(MessageKey.ORDER_SUITE_USED_NOT_EDIT);
                }
            }


            if(CollectionUtils.isNotEmpty(orderSuiteEditVo.getOrderSuiteConfigVos())){
                QueryWrapper<OrderInfo> queryWrapper1 = new QueryWrapper<OrderInfo>();
                queryWrapper1.eq("order_status",1);
                queryWrapper1.eq("delete_time",0);
                queryWrapper1.eq("suite_code",orderSuite.getSuiteCode());
                List<OrderInfo> orderInfos = orderInfoMapper.selectList(queryWrapper1);
                Integer sumData = 0;
                if(CollectionUtils.isNotEmpty(orderInfos)) {
                    sumData = orderInfos.stream().mapToInt(OrderInfo::getAccountNumber).sum();
                }
                Integer finalSumData = sumData;
                orderSuiteEditVo.getOrderSuiteConfigVos().stream().forEach(item -> {
                    if(item.getNumberEnd() < finalSumData) {
                        throw new QslException(MessageKey.ORDER_USER_NUMBER_THAN_SAME_ACCOUNTS_NUMBER);
                    }
                });

            }

            orderSuite.setUpdateTime(Calendar.getInstance().getTimeInMillis());
            orderSuite.setDeleteTime(0L);
            orderSuite.setUpdateBy(orderSuiteEditVo.getUpdateBy());

            if(StringUtil.isNotEmpty(orderSuiteEditVo.getTenantId())) {
                orderSuite.setTenantId(orderSuiteEditVo.getTenantId());
            }
            if(StringUtil.isNotEmpty(orderSuiteEditVo.getSuiteNotes())) {
                orderSuite.setSuiteNotes(orderSuiteEditVo.getSuiteNotes());
            }
            if(StringUtil.isNotEmpty(orderSuiteEditVo.getSuiteStatus())) {
                orderSuite.setSuiteStatus(orderSuiteEditVo.getSuiteStatus());
            }
            if(StringUtil.isNotEmpty(orderSuiteEditVo.getSuiteName())) {
                orderSuite.setSuiteName(orderSuiteEditVo.getSuiteName());
            }
            orderSuiteMapper.updateOrderSuite(orderSuite);

            if(!orderSuiteEditVo.getOperateType().equals("auth")) {
                orderSuiteConfigMapper.delOrderSuiteConfigBySuiteCode(orderSuite.getSuiteCode());
            }
            orderSuitePermissionMapper.delOrderSuitePermissionsBySuiteCode(orderSuite.getSuiteCode());

            if(CollectionUtils.isNotEmpty(orderSuiteEditVo.getOrderSuiteConfigVos())){

                List<OrderSuiteConfig> orderSuiteConfigs = orderSuiteEditVo.getOrderSuiteConfigVos().stream().map(item -> {
                    OrderSuiteConfig orderSuiteConfig = new OrderSuiteConfig();
                    BeanUtils.copyProperties(item,orderSuiteConfig);
                    orderSuiteConfig.setSuiteCode(orderSuite.getSuiteCode());
                    orderSuiteConfig.setSuiteConfigCode("SC"+ CodeUtils.getNum(7));
                    return orderSuiteConfig;
                }).collect(Collectors.toList());
                orderSuiteConfigMapper.insertOrderSuiteConfigs(orderSuiteConfigs);
            }

            if(StringUtil.isNotEmpty(orderSuiteEditVo.getOrderSuitePermissionVos())){
                String[] strArry = orderSuiteEditVo.getOrderSuitePermissionVos().split(",");
                List<OrderSuitePermission> orderSuitePermissions = Arrays.stream(strArry).map(item -> {
                    OrderSuitePermission orderSuitePermission = new OrderSuitePermission();
                    orderSuitePermission.setPermissionId(Long.parseLong(item));
                    orderSuitePermission.setSuiteCode(orderSuite.getSuiteCode());
                    return orderSuitePermission;
                }).collect(Collectors.toList());

                orderSuitePermissionMapper.insertOrderSuitePermissions(orderSuitePermissions);
            }

        }
        return true;
    }

     
    public int delOrderSuiteById(int id) {
        return orderSuiteMapper.delOrderSuiteById(id);
    }

     
    public OrderSuite getOrderSuiteById(Long id) {
        return orderSuiteMapper.getOrderSuiteById(id);
    }

    public List<OrderSuite> getOrderSuiteDAC(QueryTapeOrderSuiteParam params) {
        List<OrderSuite> orderSuites = orderSuiteMapper.getOrderSuiteDAC(params);
        return orderSuites;

    }

    public Long getOrderSuiteDACCount(QueryTapeOrderSuiteParam params) {

        return orderSuiteMapper.getOrderSuiteDACCount(params);
    }


    public List<OrderSuite> getOrderSuiteList(String tenantId,String suiteStatus) {
        QueryWrapper<OrderSuite> queryWrapper = new QueryWrapper<OrderSuite>();
        queryWrapper.eq("tenant_id",tenantId);
        if(StringUtil.isNotEmpty(suiteStatus)) {
            queryWrapper.eq("suite_status", suiteStatus);
        }
        queryWrapper.eq("delete_time",0L);
        List<OrderSuite> orderSuites = orderSuiteMapper.selectList(queryWrapper);
        return orderSuites;

    }
}
