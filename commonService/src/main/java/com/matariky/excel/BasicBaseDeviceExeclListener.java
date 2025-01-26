package com.matariky.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.matariky.commonservice.base.bean.BasicBaseDevice;
import com.matariky.commonservice.base.bean.BasicBaseDeviceType;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceTypeMapper;
import com.matariky.commonservice.base.service.BasicBaseDeviceService;
import com.matariky.commonservice.base.vo.BasicBaseDeviceExeclVO;
import com.matariky.commonservice.base.vo.DbmVO;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.StringUtils;
import com.matariky.utils.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


public class BasicBaseDeviceExeclListener extends AnalysisEventListener<BasicBaseDeviceExeclVO> {


    private BasicBaseDeviceMapper basicBaseDeviceMapper;

    private BasicBaseDeviceTypeMapper basicBaseDevicetypeMapper;

    private Long userId;

    private HttpServletRequest request;

    private String tenantId;

    private BasicBaseDeviceService basicBaseDeviceService;

    public BasicBaseDeviceExeclListener(BasicBaseDeviceMapper basicBaseDeviceMapper, BasicBaseDeviceTypeMapper basicBaseDevicetypeMapper, Long userId,
                                        HttpServletRequest request, String tenantId,
                                        BasicBaseDeviceService basicBaseDeviceService) {
        this.basicBaseDeviceMapper = basicBaseDeviceMapper;
        this.basicBaseDevicetypeMapper = basicBaseDevicetypeMapper;
        this.userId = userId;
        this.request = request;
        this.tenantId = tenantId;
        this.basicBaseDeviceService = basicBaseDeviceService;
    }

    @Override
    public void invoke(BasicBaseDeviceExeclVO vo, AnalysisContext analysisContext) {
        if (StringUtils.isNotEmpty(vo.getDeviceCode()) && vo.getDeviceCode().length() > 200) {
            throw new QslException(MessageKey.DEVICE_CODE_LENGTH_CANNOT_EXCEED);
        }
        if (StringUtils.isNotEmpty(vo.getDeviceDbm()) && vo.getDeviceDbm().length() > 100) {
            throw new QslException(MessageKey.DEVICE_DBM_LENGTH_CANNOT_EXCEED);
        }
        if (StringUtils.isNotEmpty(vo.getDeviceIp()) && vo.getDeviceIp().length() > 50) {
            throw new QslException(MessageKey.DEVICE_IP_LENGTH_CANNOT_EXCEED);
        }
        if (StringUtils.isNotEmpty(vo.getDeviceMac()) && vo.getDeviceMac().length() > 200) {
            throw new QslException(MessageKey.DEVICE_MAX_LENGTH_CANNOT_EXCEED);
        }
        if (StringUtils.isNotEmpty(vo.getLongitude()) && vo.getLongitude().length() > 100) {
            throw new QslException(MessageKey.DEVICE_LONGITUDE_LENGTH_CANNOT_EXCEED);
        }
        if (StringUtils.isNotEmpty(vo.getLatitude()) && vo.getLatitude().length() > 100) {
            throw new QslException(MessageKey.DEVICE_LATITUDE_LENGTH_CANNOT_EXCEED);
        }
        if (StringUtils.isNotEmpty(vo.getInstallAddress()) && vo.getInstallAddress().length() > 200) {
            throw new QslException(MessageKey.DEVICE_INSTALL_ADDRESS_LENGTH_CANNOT_EXCEED);
        }
        String typeName = vo.getTypeName();
        if (StringUtils.isBlank(typeName)) {
            throw new QslException(MessageKey.XLSX_FILE_INCORRECT_FORMAT);
        }
        int a = typeName.lastIndexOf("/");
        String deviceMoel = typeName.substring(a + 1, typeName.length() - 1);
        int b = typeName.indexOf("(");
        if (b == -1) {
            throw new QslException(MessageKey.XLSX_FILE_INCORRECT_FORMAT);
        }
        int c = typeName.indexOf("/");
        if (c == -1) {
            throw new QslException(MessageKey.XLSX_FILE_INCORRECT_FORMAT);
        }
        String deviceFactory = typeName.substring(b + 1, c);
        int d = typeName.indexOf("/");
        if (d == -1) {
            throw new QslException(MessageKey.XLSX_FILE_INCORRECT_FORMAT);
        }
        int e = typeName.lastIndexOf("/");
        if (e == -1) {
            throw new QslException(MessageKey.XLSX_FILE_INCORRECT_FORMAT);
        }
        typeName = typeName.substring(d + 1, e);
        BasicBaseDevice add = new BasicBaseDevice();
        BeanUtils.copyProperties(vo, add);
        BasicBaseDeviceType basicBaseDevicetype = basicBaseDevicetypeMapper.selectOne(Wrappers.lambdaQuery(BasicBaseDeviceType.class)
                .eq(BasicBaseDeviceType::getTypeName, typeName)
                .eq(BasicBaseDeviceType::getDeviceModel, deviceMoel)
                .eq(BasicBaseDeviceType::getDeviceFactory, deviceFactory)
                .eq(BasicBaseDeviceType::getDeleteTime, 0)
                .eq(BasicBaseDeviceType::getTenantId, tenantId));
        if (basicBaseDevicetype == null) {
             throw new QslException(MessageKey.DEVICE_TYPE_NOT_EXIST);
        }
        Long count = basicBaseDeviceMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDevice.class)
                .eq(BasicBaseDevice::getDeviceCode, vo.getDeviceCode())
                .eq(BasicBaseDevice::getDeleteTime, 0));
        if (count > 0) {
            throw new QslException(MessageKey.DEVICE_CODE_NOT_REPEAT);
        }

        if (StringUtils.isNotEmpty(add.getDeviceDbm())) {
            List<DbmVO> dbmOption = basicBaseDeviceService.getDbmOption();
            List<String> dbmList = dbmOption.stream().map(DbmVO::getValue).collect(Collectors.toList());
            if (!dbmList.contains(add.getDeviceDbm())) {
                throw new QslException(MessageKey.BASE_DEVICE_DBM_VALUE_INCORRECT);
            }
        }

        if (StringUtils.isNotEmpty(add.getDeviceIp())) {
            Long ipCount = basicBaseDeviceMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDevice.class)
                    .eq(BasicBaseDevice::getDeviceIp, add.getDeviceIp())
                    .eq(BasicBaseDevice::getDeleteTime, 0));
            if (ipCount > 0) {
                throw new QslException(MessageKey.BASE_DEVICE_IP_REPEAT);
            }
        }

        if (StringUtils.isNotEmpty(add.getDeviceMac())){
            Long macCount = basicBaseDeviceMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDevice.class)
                    .eq(BasicBaseDevice::getDeviceMac, add.getDeviceMac())
                    .eq(BasicBaseDevice::getDeleteTime, 0));
            if (macCount > 0) {
                throw new QslException(MessageKey.BASE_DEVICE_MAC_REPEAT);
            }
        }

        add.setTypeId(basicBaseDevicetype.getId());
        add.setCreateTime(System.currentTimeMillis());
        add.setCreateBy(userId);
        add.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        add.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        add.setTenantId(tenantId);
        add.setCurrentVersion(basicBaseDevicetype.getCurrentVersion());
        add.setUpdateTime(System.currentTimeMillis());
        add.setUpdateBy(userId);
        basicBaseDeviceMapper.createBasicBaseDevice(add);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


}
