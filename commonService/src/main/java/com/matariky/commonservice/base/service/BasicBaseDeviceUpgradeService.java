package com.matariky.commonservice.base.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.matariky.commonservice.base.bean.BasicBaseDevice;
import com.matariky.commonservice.base.bean.BasicBaseDevicePackage;
import com.matariky.commonservice.base.bean.BasicBaseDeviceUpgrade;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDevicePackageMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceUpgradeMapper;
import com.matariky.commonservice.base.vo.*;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.PermissionConstant;
import com.matariky.constant.TracerConstants;
import com.matariky.exception.QslException;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.utils.OpenTelemetryUtil;
import com.matariky.utils.TokenUtils;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBaseDeviceUpgradeService
        extends BaseServiceImpl<BasicBaseDeviceUpgradeMapper, BasicBaseDeviceUpgrade> {

    @Autowired
    private BasicBaseDeviceUpgradeMapper basicBaseDeviceupgradeMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BasicBaseDevicePackageMapper basicBaseDevicepackageMapper;
    @Autowired
    private BasicBaseDeviceMapper basicBaseDeviceMapper;
    @Value("${signoz.tracer.url}")
    private String signozTracerUrl;
    @Value("${job.api.url}")
    private String jobUrl;
    @Autowired
    private CommonDictService commonDictService;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Autowired
    private CommonService commonService;

    private static Jedis jedis;

    static {
        jedis = new Jedis("172.28.1.17", 26379);
        jedis.auth("network-signoz-redis");
    }

    /**
     * Upgrade Pagination
     *
     * @param vo
     * @return
     */
    public List<BasicBaseDeviceUpgradeResVO> list(UpgradeListVO vo) {
        String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(
                TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId,
                commonDictType.getId());
        if (dict == null) {
            vo.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
        } else {
            vo.setStrategyCode(dict.getDictValue());
        }
        vo.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        vo.setOrgCode(TokenUtils.extractOrgCode(request));
        vo.setTenantId(tenantId);
        List<BasicBaseDeviceUpgradeResVO> list = basicBaseDeviceupgradeMapper.getBasicBaseDeviceupgradeAll(vo);

        list.stream().forEach(item -> {
            String deviceIdList = item.getDeviceIdListStr();
            JSONArray array = JSONArray.parseArray(deviceIdList);
            if (array.size() > 0) {
                List<DeviceIdVO> idList = array.stream().map(json -> {
                    DeviceIdVO idVO = new DeviceIdVO();
                    JSONObject a = (JSONObject) json;
                    Long deviceId = Long.valueOf(a.get("deviceId").toString());
                    String deviceCode = a.get("deviceCode").toString();
                    idVO.setDeviceId(deviceId);
                    idVO.setDeviceCode(deviceCode);
                    return idVO;
                }).collect(Collectors.toList());
                item.setDeviceIdList(idList);
            }
        });
        return list;
    }

    /**
     * Upgrade Device
     */
    @Transactional(rollbackFor = Exception.class)
    public void createBasicBaseDeviceupgradeWithOrg(BasicBaseDeviceUpgradeAddDTO addDTO) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        long userId = Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request));
        BasicBaseDevicePackage devicePackage = basicBaseDevicepackageMapper.selectById(addDTO.getPackageId());
        if (devicePackage == null) {
            throw new QslException(MessageKey.DEVICE_PACKAGE_NOT_EXIST);
        }

        basicBaseDeviceupgradeMapper.delete(Wrappers.lambdaQuery(BasicBaseDeviceUpgrade.class)
                .eq(BasicBaseDeviceUpgrade::getUpgradeStatus, 0)
                .eq(BasicBaseDeviceUpgrade::getPackageId, addDTO.getPackageId())
                .eq(BasicBaseDeviceUpgrade::getDeleteTime, 0));

        addDTO.getDeviceUpgradeList().stream().forEach(item -> {
            /** Immediate Upgrade **/
            if ("immediate".equals(item.getUpgradeType())) {
                Long rowId = IdWorker.getId();
                item.getDeviceIdList().forEach(deviceId -> {
                    BasicBaseDevice basicBaseDevice = basicBaseDeviceMapper.selectById(deviceId);
                    /** Device Upgrade **/
                    commonService.deviceUpgrade(devicePackage.getMd5(), devicePackage.getPackageVersion(),
                            basicBaseDevice.getDeviceCode());
                    /** When Upgraded ,Update Device Version **/
                    BasicBaseDevice update = new BasicBaseDevice();
                    update.setId(deviceId);
                    update.setPrevVersion(basicBaseDevice.getCurrentVersion());
                    update.setCurrentVersion(devicePackage.getPackageVersion());
                    update.setUpdateTime(System.currentTimeMillis());
                    update.setUpdateBy(userId);
                    basicBaseDeviceMapper.updateById(update);
                    Long count = basicBaseDeviceupgradeMapper
                            .selectCount(Wrappers.lambdaQuery(BasicBaseDeviceUpgrade.class)
                                    .eq(BasicBaseDeviceUpgrade::getPackageId, addDTO.getPackageId())
                                    .eq(BasicBaseDeviceUpgrade::getDeviceId, deviceId)
                                    .eq(BasicBaseDeviceUpgrade::getDeleteTime, 0)
                                    .eq(BasicBaseDeviceUpgrade::getTenantId, tenantId));
                    /** Has been Upgraded, not again Upgrade **/
                    if (count > 0) {
                        return;
                    }
                    BasicBaseDeviceUpgrade add = new BasicBaseDeviceUpgrade();
                    add.setDeviceId(deviceId);
                    add.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
                    add.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
                    add.setCreateTime(System.currentTimeMillis());
                    add.setCreateBy(userId);
                    add.setTenantId(tenantId);
                    add.setPackageId(addDTO.getPackageId());
                    add.setUpgradeStatus(1);
                    add.setUpgradeType(item.getUpgradeType());
                    add.setUpgradeTime(item.getUpgradeTime());

                    /** Temporarily believe Upgrade success **/
                    add.setUpgradeStatus(1);
                    add.setExecuteTime(System.currentTimeMillis());
                    add.setRowId(rowId);

                });
                /** Scheduled Upgrade **/
            } else if ("scheduler".equals(item.getUpgradeType())) {
                Long rowId = IdWorker.getId();
                item.getDeviceIdList().forEach(deviceId -> {
                    BasicBaseDeviceUpgrade add = new BasicBaseDeviceUpgrade();
                    add.setDeviceId(deviceId);
                    add.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
                    add.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
                    add.setCreateTime(System.currentTimeMillis());
                    add.setCreateBy(userId); //
                    add.setTenantId(tenantId);
                    add.setPackageId(addDTO.getPackageId());
                    add.setUpgradeStatus(0);
                    add.setUpgradeType(item.getUpgradeType());
                    add.setUpgradeTime(item.getUpgradeTime());

                    /** Temporarily believe Upgrade success **/
                    add.setExecuteTime(System.currentTimeMillis());
                    add.setRowId(rowId);
                    basicBaseDeviceupgradeMapper.createBasicBaseDeviceupgrade(add);
                    try {
                        /** Add Timer Task **/
                        HttpHeaders headers = new HttpHeaders();
                        headers.add("Authorization", "Bearer " + TokenUtils.getTokenFromRequest(request));
                        headers.add("Id", request.getHeader("Id"));
                        RestTemplate restTemplate = new RestTemplate();
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("deviceId", deviceId);
                        jsonObject.put("packageId", addDTO.getPackageId());
                        jsonObject.put("upgradeTime", item.getUpgradeTime());
                        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(jsonObject, headers);
                        String url = jobUrl + "/api/job/v1/tenant/" + tenantId + "/addDeviceUpgradeJob";
                        restTemplate.postForObject(url, requestEntity, String.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });
        /** Update包 Upgrade 的 Device Quantity **/
        BasicBaseDevicePackage baseDevicepackage = new BasicBaseDevicePackage();
        baseDevicepackage.setUpdateTime(System.currentTimeMillis());
        baseDevicepackage.setUpdateBy(userId);
        baseDevicepackage.setId(addDTO.getPackageId());
        baseDevicepackage.setDeviceNum(addDTO.getDeviceUpgradeList().size());
        basicBaseDevicepackageMapper.updateById(baseDevicepackage);
    }

    public void recordLog(String deviceCode, String content) {
        try {
            OpenTelemetryUtil.init("karaf", signozTracerUrl);
            // RetrieveTracer
            Tracer tracer = OpenTelemetryUtil.getTracer();
            // Create Span
            // Operation Name
            Span span = tracer.spanBuilder(TracerConstants.APPSTART).startSpan();
            try (Scope scope = span.makeCurrent()) {
                // RetrievetraceId
                // RetrievespanId
                // Configuration Properties
                span.setAttribute(TracerConstants.APPNAME, "karaf");// App Name
                span.setAttribute(TracerConstants.HASERROR, "false");// Wether There is Error
                // The following is specific Error Message
                Attributes eventAttributes = Attributes.of(
                        AttributeKey.stringKey(TracerConstants.SUCCESS), TracerConstants.SUCCESS,
                        AttributeKey.stringKey(TracerConstants.DEVICECODE), deviceCode,
                        AttributeKey.stringKey(TracerConstants.DEVICE_UPGRADE_CONTENT), content);
                // Add Event
                span.addEvent(TracerConstants.APPSTART, eventAttributes);
            } catch (Throwable t) {
                span.setStatus(StatusCode.ERROR, t.getMessage());
                span.setAttribute(TracerConstants.HASERROR, "true");// Wether There is Error
                Attributes eventAttributes = Attributes.of(AttributeKey.stringKey(TracerConstants.ERROR),
                        t.getMessage());
                // Add Event
                span.addEvent(TracerConstants.APPSTART, eventAttributes);
            } finally {
                OpenTelemetryUtil.getExporter().flush();
                span.end();
            }
            // In the docker environment, the container will not call System.exit exit, nor
            // will it be closed to close HOOK。
            Runtime.getRuntime().addShutdownHook((new Thread() {
                public void run() {
                    Span span = tracer.spanBuilder(TracerConstants.APPSTOP).startSpan();
                    span.setAttribute(TracerConstants.APPNAME, "karaf");// App Name
                    span.setAttribute(TracerConstants.HASERROR, "false");// Wether There is Error
                    // The following is specific Error Message
                    Attributes eventAttributes = Attributes.of(AttributeKey.stringKey(TracerConstants.ERROR),
                            TracerConstants.SUCCESS);
                    // Add Event
                    span.addEvent(TracerConstants.APPSTOP, eventAttributes);
                    OpenTelemetryUtil.getExporter().flush();
                    span.end();
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Upgraded Device Pagination
     *
     * @param packageId
     * @return
     */
    public List<UpgradeDeviceVO> upgradeDeviceList(Long packageId) {
        return basicBaseDeviceupgradeMapper.getUpgradeDeviceList(packageId);
    }

    /**
     * Delete
     *
     * @param vo
     */
    public void delete(DeviceUpgradeListVO vo) {
        vo.getIdList().stream().forEach(id -> {
            BasicBaseDeviceUpgrade delete = new BasicBaseDeviceUpgrade();
            delete.setId(id);
            delete.setDeleteTime(System.currentTimeMillis());
            basicBaseDeviceupgradeMapper.updateById(delete);
        });
    }
}
