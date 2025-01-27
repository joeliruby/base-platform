package com.matariky.orderservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.commonservice.upload.utils.DateUtils;
import com.matariky.exception.QslException;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.orderservice.bean.OrderInfo;
import com.matariky.orderservice.bean.OrderInfoRecord;
import com.matariky.orderservice.bean.OrderSuiteConfig;
import com.matariky.orderservice.mapper.OrderInfoMapper;
import com.matariky.orderservice.mapper.OrderInfoRecordMapper;
import com.matariky.orderservice.mapper.OrderSuiteConfigMapper;
import com.matariky.orderservice.param.QueryTapeOrderInfoParam;
import com.matariky.orderservice.vo.OrderInfoAddVo;
import com.matariky.orderservice.vo.OrderInfoEditVo;
import com.matariky.orderservice.vo.OrderInfoOperateVo;
import com.matariky.orderservice.vo.OrderInfoPageVo;
import com.matariky.utils.CodeUtils;
import com.matariky.utils.DateUtil;
import com.matariky.utils.StringUtils;
import com.matariky.utils.TokenUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class OrderInfoService extends BaseServiceImpl<OrderInfoMapper, OrderInfo> {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderInfoRecordMapper orderInfoRecordMapper;
    @Autowired
    private OrderSuiteConfigMapper orderSuiteConfigMapper;
    @Autowired
    private CommonDictTypeMapper commonDictTypeMapper;
    @Value("${message.locale}")
    private String locale;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CommonDictMapper commonDictMapper;

    public List<OrderInfo> getOrderInfoAll() {
        return orderInfoMapper.getOrderInfoAll();
    }

    public int getOrderInfoAllCount() {
        return orderInfoMapper.getOrderInfoAllCount();
    }

    public int createOrderInfo(OrderInfo bean) {
        return orderInfoMapper.createOrderInfo(bean);
    }

    public boolean createOrderInfoWithOrg(OrderInfoAddVo bean, HttpServletRequest request, String tenantId,
            String jwt) {
        Long count = orderInfoMapper.selectCount(Wrappers.lambdaQuery(OrderInfo.class)
                .in(OrderInfo::getOrderStatus, 1, 3)
                .eq(OrderInfo::getOrderTenantId, bean.getOrderTenantId())
                .eq(OrderInfo::getSuiteCode, bean.getSuiteCode())
                .eq(OrderInfo::getDeleteTime, 0));
        if (count > 0) {
            throw new QslException(MessageKey.SAME_PRODUCT_IS_EXIST);
        }

        if (StringUtil.isNotEmpty(bean.getExpirationStartTime())) {
            if (new Date().getTime() > DateUtil.string2date(bean.getExpirationEndTime()).getTime()) {
                throw new QslException(MessageKey.ORDER_EXPIRATION_DATA_THAN_NOW);
            }
        }
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<OrderInfo>();
        queryWrapper.eq("contract_code", bean.getContractCode());
        queryWrapper.eq("delete_time", 0);
        List<OrderInfo> orderInfos1 = orderInfoMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(orderInfos1)) {
            throw new QslException(MessageKey.ORDER_CONTRACT_IS_EXIST);
        }
        // At one Time for one Tenant only one Record
        Map<String, Object> params = new HashMap<>();
        params.put("orderTenantId", bean.getOrderTenantId());
        params.put("startTime", bean.getExpirationStartTime());
        params.put("endTime", bean.getExpirationEndTime());
        List<OrderInfo> orderInfos = orderInfoMapper.selectOrderInfoByOrderTenantId(params);
        if (CollectionUtils.isNotEmpty(orderInfos)) {
            orderInfos.stream().forEach(item -> {
                if (!item.getOrderStatus().equals("3")) {
                    if (item.getExpirationStartTime() >= DateUtil.string2date(bean.getExpirationStartTime()).getTime()
                            &&
                            item.getExpirationStartTime() <= DateUtil.string2date(bean.getExpirationEndTime())
                                    .getTime()) {
                        throw new QslException(MessageKey.ORDER_EXPIRATION_DATA_OVERLAPS);
                    }

                    if (item.getExpirationEndTime() <= DateUtil.string2date(bean.getExpirationEndTime()).getTime() &&
                            item.getExpirationEndTime() >= DateUtil.string2date(bean.getExpirationStartTime())
                                    .getTime()) {
                        throw new QslException(MessageKey.ORDER_EXPIRATION_DATA_OVERLAPS);
                    }
                }
            });
        }

        if (bean.getAccountNumber() != null && bean.getAccountNumber() != 0) {

            QueryWrapper<OrderInfo> queryWrapper2 = new QueryWrapper<OrderInfo>();
            queryWrapper2.eq("order_tenant_id", bean.getOrderTenantId());
            queryWrapper2.eq("order_status", 1);
            queryWrapper2.eq("delete_time", 0);
            List<OrderInfo> orderInfos2 = orderInfoMapper.selectList(queryWrapper2);

            QueryWrapper<OrderSuiteConfig> queryWrapper1 = new QueryWrapper<OrderSuiteConfig>();
            queryWrapper1.eq("suite_code", bean.getSuiteCode());
            List<OrderSuiteConfig> orderSuiteConfigs = orderSuiteConfigMapper.selectList(queryWrapper1);
            if (CollectionUtils.isNotEmpty(orderSuiteConfigs)) {
                orderSuiteConfigs.stream().forEach(item -> {
                    if (bean.getAccountNumber() >= item.getNumberStart()
                            && bean.getAccountNumber() <= item.getNumberEnd()) {
                        if (item.getNumberEnd() < bean.getAccountNumber()
                                || item.getNumberStart() > bean.getAccountNumber()) {
                            throw new QslException(MessageKey.ORDER_ACCOUNTS_NUMBER_RANGE_OUT);
                        }

                        if (CollectionUtils.isNotEmpty(orderInfos2)) {
                            Integer sumData = orderInfos2.stream().mapToInt(OrderInfo::getAccountNumber).sum();
                            if (bean.getAccountNumber() + sumData > item.getNumberEnd()) {
                                throw new QslException(MessageKey.ORDER_ACCOUNTS_NUMBER_THAN_SAME_ACCOUNTS_NUMBER);
                            }
                        }

                    }
                });
            }
        }
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(bean, orderInfo);

        orderInfo.setTenantId(tenantId);
        orderInfo.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
        orderInfo.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));

        orderInfo.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        orderInfo.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));

        orderInfo.setCreateTime(Calendar.getInstance().getTimeInMillis());
        orderInfo.setUpdateTime(Calendar.getInstance().getTimeInMillis());
        orderInfo.setDeleteTime(0L);
        orderInfo.setOrderStatus("1");
        orderInfo.setOrderCode("O" + CodeUtils.getNum(7));
        if (StringUtil.isNotEmpty(bean.getExpirationStartTime())) {
            orderInfo.setExpirationStartTime(DateUtil.string2date(bean.getExpirationStartTime()).getTime());
        }
        if (StringUtil.isNotEmpty(bean.getExpirationEndTime())) {
            orderInfo.setExpirationEndTime(DateUtil.string2date(bean.getExpirationEndTime()).getTime());
        }
        if (StringUtil.isNotEmpty(bean.getPaymentTime())) {
            orderInfo.setPaymentTime(DateUtil.string2date(bean.getPaymentTime()).getTime());
        }

        if (StringUtils.isNotBlank(bean.getPaymentVoucherPath())) {
            orderInfo.setPaymentVoucherPath(bean.getPaymentVoucherPath());
        }

        orderInfoMapper.createOrderInfo(orderInfo);

        OrderInfoRecord orderInfoRecord = new OrderInfoRecord();
        BeanUtils.copyProperties(orderInfo, orderInfoRecord);
        orderInfoRecord.setRecordType(0L);

        orderInfoRecord.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
        orderInfoRecord.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
        orderInfoRecord.setCreateTime(Calendar.getInstance().getTimeInMillis());
        orderInfoRecord.setUpdateTime(Calendar.getInstance().getTimeInMillis());
        orderInfoRecordMapper.createOrderInfoRecord(orderInfoRecord);

        return true;
    }

    public boolean updateOrderInfo(OrderInfoEditVo bean, HttpServletRequest request, String tenantId, String jwt) {

        OrderInfo orderInfo = orderInfoMapper.selectById(bean.getOrderId());
        if (orderInfo == null) {
            throw new QslException(MessageKey.ORDER_DOES_NOT_EXIST);
        }

        QueryWrapper<OrderInfo> queryWrapper1 = new QueryWrapper<OrderInfo>();
        queryWrapper1.eq("contract_code", bean.getContractCode());
        queryWrapper1.eq("delete_time", 0);
        List<OrderInfo> orderInfos1 = orderInfoMapper.selectList(queryWrapper1);
        if (CollectionUtils.isNotEmpty(orderInfos1)) {
            List<OrderInfo> orderInfos2 = orderInfos1.stream()
                    .filter(i -> !i.getId().toString().equals(orderInfo.getId().toString()))
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(orderInfos2)) {
                throw new QslException(MessageKey.ORDER_CONTRACT_IS_EXIST);
            }
        }

        orderInfo.setContractCode(bean.getContractCode());
        orderInfo.setContractCode(bean.getContractCode());
        if (StringUtil.isNotEmpty(bean.getExpirationEndTime())) {
            QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<OrderInfo>();
            queryWrapper.eq("suite_code", orderInfo.getSuiteCode());
            List<OrderInfo> orderInfos = orderInfoMapper.selectList(queryWrapper);
            if (CollectionUtils.isNotEmpty(orderInfos)) {
                orderInfos.stream().forEach(item -> {
                    Date date1 = DateUtils.parse(bean.getExpirationEndTime(), DateUtils.DATE_PATTERN);
                    if (item.getExpirationEndTime() >= date1.getTime()) {
                        throw new QslException(MessageKey.ORDER_SELECTION_DATE_THAN_EXPIRATION_DATE);
                    }
                });
            }
            Date date = DateUtils.parse(bean.getExpirationEndTime(), DateUtils.DATE_PATTERN);
            orderInfo.setExpirationEndTime(date.getTime());

        }

        if (bean.getAccountNumber() != null && bean.getAccountNumber() != 0) {
            QueryWrapper<OrderSuiteConfig> queryWrapper2 = new QueryWrapper<OrderSuiteConfig>();
            queryWrapper2.eq("suite_code", bean.getSuiteCode());
            List<OrderSuiteConfig> orderSuiteConfigs = orderSuiteConfigMapper.selectList(queryWrapper2);
            if (CollectionUtils.isNotEmpty(orderSuiteConfigs)) {
                orderSuiteConfigs.stream().forEach(item -> {
                    if (bean.getAccountNumber() >= item.getNumberStart()
                            && bean.getAccountNumber() <= item.getNumberEnd()) {
                        if (item.getNumberEnd() < bean.getAccountNumber()
                                || item.getNumberStart() > bean.getAccountNumber()) {
                            throw new QslException(MessageKey.ORDER_ACCOUNTS_NUMBER_RANGE_OUT);
                        }
                    }
                });
            }
        }

        if (StringUtil.isNotEmpty(bean.getPaymentTime())) {
            orderInfo.setPaymentTime(DateUtil.string2date(bean.getPaymentTime()).getTime());
        }

        orderInfo.setContacts(bean.getContacts());
        orderInfo.setContactsPhone(bean.getContactsPhone());
        orderInfo.setAccountNumber(orderInfo.getAccountNumber() + bean.getAccountNumber());
        orderInfo.setChargeMode(bean.getChargeMode());
        orderInfo.setPrice(bean.getPrice());
        orderInfo.setDiscountPrice(bean.getDiscountPrice());
        if (StringUtils.isNotBlank(bean.getPaymentVoucherPath())) {
            orderInfo.setPaymentVoucherPath(bean.getPaymentVoucherPath());
        }

        orderInfo.setRemark(bean.getRemark());
        orderInfoMapper.updateOrderInfo(orderInfo);

        OrderInfoRecord orderInfoRecord = new OrderInfoRecord();
        BeanUtils.copyProperties(orderInfo, orderInfoRecord);
        orderInfoRecord.setRecordType(1L);

        orderInfoRecord.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
        orderInfoRecord.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
        orderInfoRecord.setCreateTime(Calendar.getInstance().getTimeInMillis());
        orderInfoRecord.setUpdateTime(Calendar.getInstance().getTimeInMillis());

        orderInfoRecordMapper.createOrderInfoRecord(orderInfoRecord);
        return true;
    }

    public boolean delOrderInfoById(Long orderId) {
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        if (orderId == null) {
            throw new QslException(MessageKey.ORDER_DOES_NOT_EXIST);
        }
        Long count = orderInfoMapper.selectCountByOrderTenantId(orderInfo.getOrderTenantId());
        if (count > 1) {
            throw new QslException(MessageKey.ORDER_UNABLE_DELETE_PRESENT_ORDER);
        }

        orderInfoMapper.delOrderInfoById(orderId);

        return true;
    }

    public OrderInfo getOrderInfoById(int id) {
        return orderInfoMapper.getOrderInfoById(id);
    }

    public List<OrderInfoPageVo> getOrderInfoDAC(QueryTapeOrderInfoParam params) {

        List<OrderInfoPageVo> orderInfoPageVos = orderInfoMapper.getOrderInfoDAC(params);
        if (CollectionUtils.isNotEmpty(orderInfoPageVos)) {
            orderInfoPageVos.stream().forEach(item -> {
                QueryWrapper<OrderInfoRecord> queryWrapper = new QueryWrapper<OrderInfoRecord>();
                queryWrapper.eq("order_code", item.getOrderCode());
                queryWrapper.eq("order_tenant_id", item.getOrderTenantId());

                item.setRenewalCount(0L);
                item.setDelayCount(0L);
                item.setExpansionCount(0L);

                if (item.getAlreadyAccountNumber() != null && item.getAlreadyAccountNumber() != 0) {
                    item.setAlreadyAccountNumber(item.getAlreadyAccountNumber() - 1);
                }
                List<OrderInfoRecord> orderInfoRecords = orderInfoRecordMapper.selectList(queryWrapper);
                if (CollectionUtils.isNotEmpty(orderInfoRecords)) {
                    List<OrderInfoRecord> o1 = orderInfoRecords.stream().filter(o -> o.getRecordType() == 1L)
                            .collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(o1)) {
                        item.setRenewalCount(Long.parseLong(String.valueOf(o1.size())));
                    }
                    List<OrderInfoRecord> o2 = orderInfoRecords.stream().filter(o -> o.getRecordType() == 2L)
                            .collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(o2)) {
                        item.setDelayCount(Long.parseLong(String.valueOf(o2.size())));
                    }
                    List<OrderInfoRecord> o3 = orderInfoRecords.stream().filter(o -> o.getRecordType() == 3L)
                            .collect(Collectors.toList());
                    if (CollectionUtils.isNotEmpty(o3)) {
                        item.setExpansionCount(Long.parseLong(String.valueOf(o3.size())));
                    }
                }
                // Delete
                item.setDelete(false);
                // Renewal
                item.setRenewal(false);
                // postpone
                item.setDelay(false);
                // Expand
                item.setExpansion(false);
                // termination
                item.setAborted(false);

                if (item.getExpirationStartTime() != null
                        && item.getExpirationStartTime() > DateUtil.getCurrentDate().getTime()) {
                    item.setDelete(true);
                }

                if (StringUtil.isNotEmpty(item.getOrderStatus()) && item.getOrderStatus().equals("1")) {
                    // Execution
                    item.setExpansion(true);
                    item.setDelay(true);
                    item.setRenewal(true);
                    item.setAborted(true);
                } else if (StringUtil.isNotEmpty(item.getOrderStatus()) && item.getOrderStatus().equals("2")) {
                    // End
                } else if (StringUtil.isNotEmpty(item.getOrderStatus()) && item.getOrderStatus().equals("3")) {
                    // Extension
                    item.setDelay(true);
                }
                item.setExpiringSoon(false);
                if (item.getExpirationEndTime() != null) {
                    Date date1 = new Date(item.getExpirationEndTime());
                    String expiringDay = this.getExpiringDay();
                    Date d1 = DateUtil.addDay(date1, -Integer.valueOf(expiringDay));
                    Date d2 = DateUtil.getCurrentDate3();
                    if (d2.getTime() >= d1.getTime() && item.getOrderStatus().equals("1")) {
                        item.setExpiringSoon(true);
                    }
                }

            });
        }
        return orderInfoPageVos;
    }

    public String getExpiringDay() {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        CommonDictType dictType = commonDictTypeMapper.selectOne(Wrappers.lambdaQuery(CommonDictType.class)
                .eq(CommonDictType::getDictTypeKey, locale + "_PRODUCT_EXPIRING_DAY")
                .eq(CommonDictType::getIsActive, 1)
                .eq(CommonDictType::getTenantId, tenantId));
        if (dictType == null) {
            throw new QslException(MessageKey.APP_UPGRADE_TYPE_DICT_TYPE_NOT_EXIST);
        }
        CommonDict deviceTypeCode = commonDictMapper.selectOne(Wrappers.lambdaQuery(CommonDict.class)
                .eq(CommonDict::getDictTypeId, dictType.getId())
                .eq(CommonDict::getDictKey, "PRODUCT_EXPIRING_DAY")
                .eq(CommonDict::getIsActive, 1)
                .eq(CommonDict::getTenantId, tenantId)
                .eq(CommonDict::getDeleteTime, 0));
        if (deviceTypeCode == null) {
            throw new QslException(MessageKey.PRODUCT_EXPIRING_DAY_NOT_EXIST);
        }
        return deviceTypeCode.getDictValue();
    }

    public Long getOrderInfoDACCount(QueryTapeOrderInfoParam params) {
        return orderInfoMapper.getOrderInfoDACCount(params);
    }

    public boolean operateData(OrderInfoOperateVo bean, HttpServletRequest request, String tenantId, String jwt) {
        OrderInfo orderInfo = orderInfoMapper.selectById(bean.getOrderId());
        if (orderInfo == null) {
            throw new QslException(MessageKey.ORDER_DOES_NOT_EXIST);
        }

        if (StringUtil.isNotEmpty(bean.getExpirationEndTime())) {
            QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<OrderInfo>();
            queryWrapper.eq("suite_code", orderInfo.getSuiteCode());
            List<OrderInfo> orderInfos = orderInfoMapper.selectList(queryWrapper);
            if (CollectionUtils.isNotEmpty(orderInfos)) {
                orderInfos.stream().forEach(item -> {
                    Date date1 = DateUtils.parse(bean.getExpirationEndTime(), DateUtils.DATE_PATTERN);
                    if (item.getExpirationEndTime() >= date1.getTime()) {
                        throw new QslException(MessageKey.ORDER_SELECTION_DATE_THAN_EXPIRATION_DATE);
                    }
                });
            }
            Date date = DateUtils.parse(bean.getExpirationEndTime(), DateUtils.DATE_PATTERN);
            orderInfo.setExpirationEndTime(date.getTime());
        }

        if (bean.getDiscountPrice() != null) {
            orderInfo.setDiscountPrice(bean.getDiscountPrice());
        }

        if (StringUtils.isNotBlank(bean.getPaymentVoucherPath())) {
            orderInfo.setPaymentVoucherPath(bean.getPaymentVoucherPath());
        }

        if (bean.getAccountNumber() != null && bean.getAccountNumber() != 0) {

            QueryWrapper<OrderSuiteConfig> queryWrapper = new QueryWrapper<OrderSuiteConfig>();
            queryWrapper.eq("suite_code", orderInfo.getSuiteCode());
            List<OrderSuiteConfig> orderSuiteConfigs = orderSuiteConfigMapper.selectList(queryWrapper);
            if (CollectionUtils.isNotEmpty(orderSuiteConfigs)) {
                orderSuiteConfigs.stream().forEach(item -> {
                    if (orderInfo.getAccountNumber() >= item.getNumberStart()
                            && orderInfo.getAccountNumber() <= item.getNumberEnd()) {
                        if (item.getNumberEnd() < bean.getAccountNumber()
                                || item.getNumberStart() > bean.getAccountNumber()) {
                            throw new QslException(MessageKey.ORDER_ACCOUNTS_NUMBER_RANGE_OUT);
                        }
                    }
                });
            }
            orderInfo.setAccountNumber(orderInfo.getAccountNumber() + bean.getAccountNumber());
        }

        if (StringUtil.isNotEmpty(bean.getOrderStatus())) {
            orderInfo.setOrderStatus(bean.getOrderStatus());
            if (bean.getOrderStatus().equals("2")) {
                Map<String, Object> params = new HashMap<>();
                params.put("orderCode", orderInfo.getOrderCode());
                orderInfoMapper.updateUserOrderCodeByOrderCode(params);
            }

        }

        if (bean.getRecordType() != null && bean.getRecordType().toString().equals("2")) {
            orderInfo.setOrderStatus("1");
        }
        if (StringUtil.isNotEmpty(bean.getRemark())) {
            orderInfo.setRemark(bean.getRemark());
        }

        orderInfo.setTenantId(tenantId);
        orderInfo.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));

        orderInfo.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        orderInfo.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));

        orderInfo.setUpdateTime(Calendar.getInstance().getTimeInMillis());
        orderInfo.setDeleteTime(0L);
        orderInfoMapper.updateOrderInfo(orderInfo);

        if (bean.isRecord()) {

            OrderInfoRecord orderInfoRecord = new OrderInfoRecord();
            BeanUtils.copyProperties(orderInfo, orderInfoRecord);
            orderInfoRecord.setRecordType(bean.getRecordType());

            orderInfoRecord.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
            orderInfoRecord.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
            orderInfoRecord.setCreateTime(Calendar.getInstance().getTimeInMillis());
            orderInfoRecord.setUpdateTime(Calendar.getInstance().getTimeInMillis());

            orderInfoRecordMapper.createOrderInfoRecord(orderInfoRecord);
        }
        return true;
    }

    public List<OrderInfo> getOrderTenantIdList(String tenantId) {

        Map<String, Object> params = new HashMap<>();
        params.put("orderTenantId", tenantId);
        Date date1 = DateUtil.getStartTimeOfDay(new Date());
        Date date2 = DateUtil.getEndTimeOfDay(new Date());
        params.put("startTime", date1.getTime());
        params.put("endTime", date2.getTime());

        return orderInfoMapper.selectByOrderTenantId(params);
    }

    public OrderInfo getOrderInfoByOrderCode(String orderCode) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<OrderInfo>();
        queryWrapper.eq("order_code", orderCode);
        queryWrapper.eq("delete_time", 0);
        return orderInfoMapper.selectOne(queryWrapper);
    }

    public boolean getOrderInfoExpiringSoon(String tenantId) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderTenantId", tenantId);
        Date date1 = DateUtil.getStartTimeOfDay(new Date());
        Date date2 = DateUtil.getEndTimeOfDay(new Date());
        params.put("startTime", date1.getTime());
        params.put("endTime", date2.getTime());
        List<OrderInfo> orderInfos = orderInfoMapper.selectByOrderTenantId(params);
        if (CollectionUtils.isNotEmpty(orderInfos)) {
            OrderInfo orderInfo = orderInfos.get(0);
            Date d = new Date(orderInfo.getExpirationEndTime());
            Date d1 = DateUtil.addDay(d, 30);
            Date d2 = DateUtil.getCurrentDate3();
            if (d2.getTime() > d1.getTime()) {
                return true;
            }
        }
        return false;
    }
}
