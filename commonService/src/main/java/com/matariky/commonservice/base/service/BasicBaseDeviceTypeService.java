package com.matariky.commonservice.base.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.matariky.commonservice.base.bean.*;
import com.matariky.commonservice.base.mapper.*;
import com.matariky.commonservice.base.vo.*;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.PermissionConstant;
import com.matariky.exception.QslException;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.model.QueryDataIsolation;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
@Slf4j
public class BasicBaseDeviceTypeService extends BaseServiceImpl<BasicBaseDeviceTypeMapper, BasicBaseDeviceType> implements BaseService<BasicBaseDeviceType> {

    @Autowired
    private BasicBaseDeviceTypeMapper basicBaseDevicetypeMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CommonDictService commonDictService;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Autowired
    private BasicBaseDeviceMapper basicBaseDeviceMapper;
    @Autowired
    private BasicBaseDevicePackageMapper basicBaseDevicePackageMapper;
    @Autowired
    private BasicBaseDeviceRuleMapper basicBaseDeviceRuleMapper;
    @Autowired
    private BasicBaseDevicecommandMapper basicBaseDevicecommandMapper;
    @Autowired
    private BasicBaseDevicecommandPackageMapper baseDevicecommandPackageMapper;


    /**
     *  Query 所有分页
     */
    public List<BasicBaseDeviceTypeInfoVO> getBasicBaseDevicetypeAll(BasicBaseDeviceTypeListVO vo) {
        String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId, commonDictType.getId());
        if (dict == null) {
            vo.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
        } else {
            vo.setStrategyCode(dict.getDictValue());
        }
        vo.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        vo.setOrgCode(TokenUtils.extractOrgCode(request));
        vo.setTenantId(tenantId);
        PageHelper.startPage(vo.getIndex(), vo.getPerPage());
        return basicBaseDevicetypeMapper.getBasicBaseDevicetypeAll(vo);
    }


    /**
     * New方法
     */
    @Transactional(rollbackFor = Exception.class)
    public void createBasicBaseDevicetypeWithOrg(BasicBaseDeviceTypeAddVO addVO, String jwt) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        long userId = Long.parseLong(TokenUtils.extractUserIdFromToken(jwt));
        BasicBaseDeviceType add = new BasicBaseDeviceType();
        CommonDict deviceTypeCode = commonService.getDeviceTypeCodeDict(addVO.getTypeKey(), tenantId);
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            BasicBaseDeviceType lastType = basicBaseDevicetypeMapper.selectOne(Wrappers.lambdaQuery(BasicBaseDeviceType.class)
                    .eq(BasicBaseDeviceType::getDeleteTime, 0)
                    .eq(BasicBaseDeviceType::getTenantId, tenantId)
                    .orderByDesc(BasicBaseDeviceType::getCreateTime)
                    .last("limit 1"));
            String typeCode = "0001";
            if (lastType != null) {
                typeCode = lastType.getTypeCode();
                typeCode = "1" + typeCode;
                typeCode = String.valueOf(Integer.parseInt(typeCode) + 1);
                typeCode = typeCode.substring(1);
            }
            Long modelCount = basicBaseDevicetypeMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDeviceType.class)
                    .eq(BasicBaseDeviceType::getTypeName, deviceTypeCode.getDictName())
                    .eq(BasicBaseDeviceType::getDeviceModel, addVO.getDeviceModel())
                    .eq(BasicBaseDeviceType::getDeviceFactory, addVO.getDeviceFactory())
                    .eq(BasicBaseDeviceType::getTenantId, tenantId)
                    .eq(BasicBaseDeviceType::getDeleteTime, 0));
            if (modelCount > 0) {
                throw new QslException(MessageKey.DEVICE_MODEL_NOT_REPEAT);
            }
            BeanUtils.copyProperties(addVO, add);
            add.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
            add.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
            add.setCreateTime(System.currentTimeMillis());
            add.setTypeName(deviceTypeCode.getDictName());
            add.setCreateBy(userId);
            add.setTenantId(tenantId);
            add.setTypeCode(typeCode);
            add.setUpdateBy(userId);
            add.setUpdateTime(System.currentTimeMillis());
            add.setCurrentVersion("V1.0.0");
            basicBaseDevicetypeMapper.createBasicBaseDevicetype(add);
        } finally {
            lock.unlock();
        }
    }


    /**
     * 编辑方法
     *
     * @param updateVO
     * @param jwt
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateBasicBaseDevicetype(BasicBaseDeviceTypeUpdateVO updateVO, String jwt) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);

        CommonDict deviceTypeCode = commonService.getDeviceTypeCodeDict(updateVO.getTypeKey(), tenantId);
        Long modelCount = basicBaseDevicetypeMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDeviceType.class)
                .eq(BasicBaseDeviceType::getTypeName, deviceTypeCode.getDictName())
                .eq(BasicBaseDeviceType::getDeviceModel, updateVO.getDeviceModel())
                .eq(BasicBaseDeviceType::getDeviceFactory, updateVO.getDeviceFactory())
                .eq(BasicBaseDeviceType::getDeleteTime, 0)
                .ne(BasicBaseDeviceType::getId, updateVO.getId()));
        if (modelCount > 0) {
            throw new QslException(MessageKey.DEVICE_MODEL_NOT_REPEAT);
        }
        long userId = Long.parseLong(TokenUtils.extractUserIdFromToken(jwt));
        BasicBaseDeviceType update = new BasicBaseDeviceType();
        BeanUtils.copyProperties(updateVO, update);
        update.setUpdateBy(userId);
        update.setUpdateTime(System.currentTimeMillis());
        update.setTypeName(deviceTypeCode.getDictName());
        basicBaseDevicetypeMapper.updateById(update);


        baseDevicecommandPackageMapper.update(null, Wrappers.lambdaUpdate(BasicBaseDevicecommandPackage.class)
                .set(BasicBaseDevicecommandPackage::getDeleteTime, System.currentTimeMillis())
                .eq(BasicBaseDevicecommandPackage::getTypeId, updateVO.getId()));

        updateVO.getCommandList().stream().forEach(item -> {
            item.getPackageIds().stream().forEach(i -> {
                BasicBaseDevicecommandPackage commandPackage = new BasicBaseDevicecommandPackage();
                commandPackage.setCommandId(item.getCommandId());
                commandPackage.setPackageId(i);
                commandPackage.setTypeId(updateVO.getId());
                commandPackage.setCreateBy(userId);
                commandPackage.setUpdateBy(userId);
                commandPackage.setTenantId(tenantId);
                commandPackage.setCreateTime(System.currentTimeMillis());
                commandPackage.setUpdateTime(System.currentTimeMillis());
                commandPackage.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
                commandPackage.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
                baseDevicecommandPackageMapper.createBasicBaseDevicecommandPackage(commandPackage);
            });
        });


    }

    /**
     * Update Device  Status 
     *
     * @param vo
     */
    public void updateBasicBaseDevicetypeStatus(BasicBaseDevicetypeStatusVO vo) {
        BasicBaseDeviceType devicetype = new BasicBaseDeviceType();
        devicetype.setId(vo.getId());
        devicetype.setStatus(vo.getStatus());
        devicetype.setUpdateBy(Long.valueOf(TokenUtils.extractUserIdFromHttpReqeust(request)));
        devicetype.setUpdateTime(System.currentTimeMillis());
        basicBaseDevicetypeMapper.updateById(devicetype);
    }

    /**
     * 删除方法
     *
     * @param id
     * @return
     */
    public int delBasicBaseDevicetypeById(Long id) {
        Long count = basicBaseDeviceMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDevice.class)
                .eq(BasicBaseDevice::getTypeId, id)
                .eq(BasicBaseDevice::getDeleteTime, 0));

        throwBaseDeviceTypeUsedException(count);

        count = basicBaseDevicePackageMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDevicePackage.class)
                .eq(BasicBaseDevicePackage::getTypeId, id)
                .eq(BasicBaseDevicePackage::getDeleteTime, 0));

        throwBaseDeviceTypeUsedException(count);

        count = basicBaseDeviceRuleMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDeviceRule.class)
                .eq(BasicBaseDeviceRule::getTypeId, id)
                .eq(BasicBaseDeviceRule::getDeleteTime, 0));

        throwBaseDeviceTypeUsedException(count);

        return basicBaseDevicetypeMapper.delBasicBaseDevicetypeById(id);
    }

    /**
     *  Device Type 已经被使用异常
     */
    public void throwBaseDeviceTypeUsedException(Long count) {
        if (count > 0) {
            throw new QslException(MessageKey.BASE_DEVICE_TYPE_USED);
        }
    }

    /**
     *  Device Type 下拉选
     *
     * @return
     */
    public List<DeviceTypeOption> getOptionList() {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);

        QueryDataIsolation vo = new QueryDataIsolation();
        String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId, commonDictType.getId());
        if (dict == null) {
            vo.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
        } else {
            vo.setStrategyCode(dict.getDictValue());
        }
        vo.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        vo.setOrgCode(TokenUtils.extractOrgCode(request));
        vo.setTenantId(tenantId);
        List<DeviceTypeOption> optionList = basicBaseDevicetypeMapper.getOptionList(vo);
        optionList.stream().forEach(item -> {
            item.setLabel(item.getTypeCode() + "(" + item.getDeviceFactory() + "/" + item.getTypeName() + "/" + item.getDeviceModel() + ")");
        });
        return optionList;
    }

    /**
     * Query Detail By ID
     *
     * @param id
     * @return
     */
    public BasicBaseDeviceTypeInfo getBasicBaseDevicetypeById(Long id) {
        BasicBaseDeviceTypeInfo result = new BasicBaseDeviceTypeInfo();
        BasicBaseDeviceType typeInfo = basicBaseDevicetypeMapper.getBasicBaseDevicetypeById(id);
        BeanUtils.copyProperties(typeInfo, result);
        /** 指令列表 **/
        List<BasicBaseDevicecommand> list = basicBaseDevicecommandMapper.selectList(Wrappers.lambdaQuery(BasicBaseDevicecommand.class)
                .eq(BasicBaseDevicecommand::getDeleteTime, 0)
                .eq(BasicBaseDevicecommand::getProtocolType, typeInfo.getProtocolType()));
        List<BasicBaseDevicecommandVO> commandList = list.stream().map(item -> {
            BasicBaseDevicecommandVO vo = new BasicBaseDevicecommandVO();
            BeanUtils.copyProperties(item, vo);
            vo.setCommandId(item.getId());
            /** 指令绑定的固件包 **/
            List<BasicBaseDevicecommandPackage> commandPackageList = baseDevicecommandPackageMapper.selectList(Wrappers.lambdaQuery(BasicBaseDevicecommandPackage.class)
                    .eq(BasicBaseDevicecommandPackage::getDeleteTime, 0)
                    .eq(BasicBaseDevicecommandPackage::getCommandId, item.getId()));
            if (commandPackageList.isEmpty()) {
                return null;
            }
            List<Long> packageIds = commandPackageList.stream().map(BasicBaseDevicecommandPackage::getPackageId).collect(Collectors.toList());
            List<BasicBaseDevicePackage> packageList = basicBaseDevicePackageMapper.selectList(Wrappers.lambdaQuery(BasicBaseDevicePackage.class)
                    .in(BasicBaseDevicePackage::getId, packageIds)
                    .eq(BasicBaseDevicePackage::getDeleteTime, 0));
            /**  Device Type 下，所有的固件包 **/
            BasicBaseDeviceUpgradeListVO params = new BasicBaseDeviceUpgradeListVO();
            params.setTypeId(commandPackageList.get(0).getTypeId());
            String hid = request.getHeader("id");
            String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
            String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
            CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
            CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId, commonDictType.getId());
            if (dict == null) {
                params.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
            } else {
                params.setStrategyCode(dict.getDictValue());
            }
            params.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
            params.setOrgCode(TokenUtils.extractOrgCode(request));
            params.setTenantId(tenantId);
            List<BasicBaseDevicePackageInfoVO> allPackageList = basicBaseDevicePackageMapper.getBasicBaseDevicepackageAll(params);
            boolean selectAll = allPackageList.size() == packageIds.size();
            if (selectAll) {
                vo.setIsAll(true);
            } else {
                vo.setIsAll(false);
            }
            List<PackageInfoVO> packageInfoIds = packageList.stream().map(a -> {
                PackageInfoVO idInfo = new PackageInfoVO();
                idInfo.setPackageId(a.getId());
                idInfo.setPackageVersion(a.getPackageVersion());
                return idInfo;
            }).collect(Collectors.toList());
            vo.setPackageIds(packageInfoIds);
            return vo;
        }).collect(Collectors.toList());
        commandList = commandList.stream().filter(item -> item != null).collect(Collectors.toList());
        result.setCommandList(commandList);
        return result;
    }
}
