package com.matariky.commonservice.base.service;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.matariky.commonservice.base.bean.BasicBaseDevice;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRule;
import com.matariky.commonservice.base.bean.BasicBaseDeviceType;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceRuleMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceTypeMapper;
import com.matariky.commonservice.base.vo.*;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.CacheConstants;
import com.matariky.constant.PermissionConstant;
import com.matariky.excel.BasicBaseDeviceExeclListener;
import com.matariky.exception.QslException;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.model.QueryDataIsolation;
import com.matariky.redis.RedisUtils;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.StringUtils;
import com.matariky.utils.TokenUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBaseDeviceService extends BaseServiceImpl<BasicBaseDeviceMapper, BasicBaseDevice> implements BaseService<BasicBaseDevice> {

    @Autowired
    private BasicBaseDeviceMapper basicBaseDeviceMapper;
    @Autowired
    private BasicBaseDeviceTypeMapper basicBaseDevicetypeMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CommonDictService commonDictService;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private BasicBaseDeviceTypeMapper basicBaseDeviceTypeMapper;
    @Autowired
    private BasicBaseDeviceRuleMapper basicBaseDeviceRuleMapper;

    /**
     *  Query 所有分页
     */
    public List<BasicBaseDeviceInfoVO> getBasicBaseDeviceAll(BasicBaseDeviceListVO vo) {
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
        List<BasicBaseDeviceInfoVO> list = basicBaseDeviceMapper.getBasicBaseDeviceAll(vo);
        list.stream().forEach(item -> {
            /** 在线缓存key **/
            String onlineKey = CacheConstants.DEVICE_ONLINE + item.getDeviceCode();
            boolean b = redisUtils.hasKey(onlineKey);
            if (b) {
                item.setStatus("on");
            } else {
                item.setStatus("off");
            }
        });
        if (StringUtils.isNotBlank(vo.getStatus())) {
            list = list.stream().filter(item -> item.getStatus().equals(vo.getStatus())).collect(Collectors.toList());
        }
        return list;
    }


    /**
     * New方法
     */
    public int createBasicBaseDeviceWithOrg(BasicBaseDeviceAddVO addVO) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        Long count = basicBaseDeviceMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDevice.class)
                .eq(BasicBaseDevice::getDeviceCode, addVO.getDeviceCode())
                .eq(BasicBaseDevice::getDeleteTime, 0));
        if (count > 0) {
            throw new QslException(MessageKey.DEVICE_CODE_NOT_REPEAT);
        }
        if (StringUtils.isNotEmpty(addVO.getDeviceIp())) {
            Long ipCount = basicBaseDeviceMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDevice.class)
                    .eq(BasicBaseDevice::getDeviceIp, addVO.getDeviceIp())
                    .eq(BasicBaseDevice::getDeleteTime, 0));
            if (ipCount > 0) {
                throw new QslException(MessageKey.BASE_DEVICE_IP_REPEAT);
            }
        }
        if (StringUtils.isNotEmpty(addVO.getDeviceMac())) {
            Long macCount = basicBaseDeviceMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDevice.class)
                    .eq(BasicBaseDevice::getDeviceMac, addVO.getDeviceMac())
                    .eq(BasicBaseDevice::getDeleteTime, 0));
            if (macCount > 0) {
                throw new QslException(MessageKey.BASE_DEVICE_MAC_REPEAT);
            }
        }
        BasicBaseDeviceType basicBaseDevicetype = basicBaseDevicetypeMapper.selectOne(Wrappers.lambdaQuery(BasicBaseDeviceType.class)
                .eq(BasicBaseDeviceType::getId, addVO.getTypeId())
                .eq(BasicBaseDeviceType::getDeleteTime, 0));
        if (basicBaseDevicetype == null) {
            throw new QslException(MessageKey.DEVICE_TYPE_NOT_EXIST);
        }

        BasicBaseDevice add = new BasicBaseDevice();
        BeanUtils.copyProperties(addVO, add);
        long userId = Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request));
        add.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        add.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        add.setCreateTime(System.currentTimeMillis());
        add.setCreateBy(userId);
        add.setTenantId(tenantId);
        String gisAddress = addVO.getGisAddress();
        if (StringUtils.isNotBlank(gisAddress)) {
            if (gisAddress.contains(",")) {
                String[] split = gisAddress.split(",");
                if (split.length == 2) {
                    add.setLongitude(split[0]);
                    add.setLatitude(split[1]);
                }
            }
        }
        add.setCurrentVersion(basicBaseDevicetype.getCurrentVersion());
        add.setUpdateBy(userId);
        add.setUpdateTime(System.currentTimeMillis());
        return basicBaseDeviceMapper.createBasicBaseDevice(add);
    }

    /**
     * Update方法
     *
     * @param updateVO
     * @return
     */
    public int updateBasicBaseDevice(BasicBaseDeviceUpdateVO updateVO) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);

        Long count = basicBaseDeviceMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDevice.class)
                .eq(BasicBaseDevice::getDeviceCode, updateVO.getDeviceCode())
                .eq(BasicBaseDevice::getDeleteTime, 0)
                .eq(BasicBaseDevice::getTenantId, tenantId)
                .ne(BasicBaseDevice::getId, updateVO.getId())
        );
        if (count > 0) {
            throw new QslException(MessageKey.DEVICE_CODE_NOT_REPEAT);
        }
        if (StringUtils.isNotEmpty(updateVO.getDeviceIp())) {
            Long ipCount = basicBaseDeviceMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDevice.class)
                    .eq(BasicBaseDevice::getDeviceIp, updateVO.getDeviceIp())
                    .ne(BasicBaseDevice::getId, updateVO.getId())
                    .eq(BasicBaseDevice::getDeleteTime, 0));
            if (ipCount > 0) {
                throw new QslException(MessageKey.BASE_DEVICE_IP_REPEAT);
            }
        }
        if (StringUtils.isNotEmpty(updateVO.getDeviceMac())) {
            Long macCount = basicBaseDeviceMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDevice.class)
                    .eq(BasicBaseDevice::getDeviceMac, updateVO.getDeviceMac())
                    .ne(BasicBaseDevice::getId, updateVO.getId())
                    .eq(BasicBaseDevice::getDeleteTime, 0));
            if (macCount > 0) {
                throw new QslException(MessageKey.BASE_DEVICE_MAC_REPEAT);
            }
        }
        BasicBaseDevice update = new BasicBaseDevice();
        BeanUtils.copyProperties(updateVO, update);
        update.setUpdateTime(System.currentTimeMillis());
        update.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
        if (StringUtils.isNotBlank(updateVO.getGisAddress())) {
            String[] split = updateVO.getGisAddress().split(",");
            if (split.length == 2) {
                update.setLongitude(split[0]);
                update.setLatitude(split[1]);
            }
        }
        return basicBaseDeviceMapper.updateById(update);
    }

    /**
     * 删除方法
     *
     * @param id
     * @return
     */
    public int delBasicBaseDeviceById(Long id) {
        int count = basicBaseDeviceMapper.selectCountFromPrint(id);
        if (count > 0) {
            throw new QslException(MessageKey.BASE_DEVICE_USED);
        }
        BasicBaseDevice basicBaseDevice = basicBaseDeviceMapper.selectById(id);
        if (basicBaseDevice == null) {
            throw new QslException(MessageKey.BASE_DEVICE_NOT_EXIST);
        }
        BasicBaseDeviceType type = basicBaseDeviceTypeMapper.selectOne(Wrappers.lambdaQuery(BasicBaseDeviceType.class)
                .eq(BasicBaseDeviceType::getId, basicBaseDevice.getTypeId())
                .eq(BasicBaseDeviceType::getDeleteTime, 0));
        Long ruleCount = basicBaseDeviceRuleMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDeviceRule.class)
                .eq(BasicBaseDeviceRule::getTypeId, type.getId())
                .eq(BasicBaseDeviceRule::getIsRecordLog, true)
                .eq(BasicBaseDeviceRule::getDeleteTime, 0));
        if (ruleCount > 0) {
            throw new QslException(MessageKey.BASE_DEVICE_USED);
        }
        return basicBaseDeviceMapper.delBasicBaseDeviceById(id);
    }


    /***
     * DownloadImport模版
     */
    public void downLoadTemplate() {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        commonService.createTemplate(response, BasicBaseDeviceExeclVO.class, "device", " Device Import模版", tenantId);
    }

    /**
     * 检查xlsx文件 Wether 含有图片
     *
     * @param file
     * @return
     */
    public static boolean containsImage(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(is)) {
            List<XSSFPictureData> pictures = ((XSSFWorkbook) workbook).getAllPictures();
            return !pictures.isEmpty();
        } catch (Exception e) {
            throw new QslException(MessageKey.XLSX_FILE_INCORRECT_FORMAT);
        }
    }

    /**
     * Import Data 
     */
    public void importData(MultipartFile file) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        Long userId = Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request));
        boolean b = containsImage(file);
        if (b) {
            throw new QslException(MessageKey.XLSX_FILE_INCORRECT_FORMAT);
        }
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, BasicBaseDeviceExeclVO.class, new BasicBaseDeviceExeclListener(basicBaseDeviceMapper, basicBaseDevicetypeMapper, userId, request, tenantId, this))
                    .sheet().doRead();
        } catch (Exception e) {
            throw new QslException(e.getMessage());
        }

    }

    /**
     *  Device 功率下拉选
     */
    public List<DbmVO> getDbmOption() {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        List<DbmVO> dbmList = new ArrayList<>();
        List<CommonDict> commonDictList = commonService.getDeviceDbmDict(tenantId);
        if (commonDictList.size() != 2) {
            return dbmList;
        }
        String maxValue;
        String minValue;
        if ("max".equals(commonDictList.get(0).getDictKey())) {
            maxValue = commonDictList.get(0).getDictValue();
            minValue = commonDictList.get(1).getDictValue();
        } else {
            maxValue = commonDictList.get(1).getDictValue();
            minValue = commonDictList.get(0).getDictValue();
        }
        int minValueNumber = Integer.valueOf(minValue);
        int maxValueNumber = Integer.valueOf(maxValue);
        for (int i = minValueNumber; i <= maxValueNumber; i++) {
            DbmVO vo = new DbmVO();
            vo.setLabel(String.valueOf(i));
            vo.setValue(String.valueOf(i));
            dbmList.add(vo);
        }
        return dbmList;
    }

    /**
     *  Device 编码（ Device 厂家/ Device Type /  Device 型号）下拉选
     */
    public List<DeviceCodeInfo> getCodeOption(CodeOptionListVO vo) {
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
        List<DeviceCodeInfo> list = basicBaseDeviceMapper.getCodeOptionList(vo);
        list.stream().forEach(item -> {
            item.setLabel(item.getDeviceCode() + "(" + item.getDeviceFactory() + "/" + item.getTypeName() + "/" + item.getDeviceModel() + ")");
        });
        return list;
    }

    /**
     *  Print 机 - 下拉选列表
     *
     * @param
     */
    public List<PrintOptionInfo> getPrintOptionList() {
        QueryDataIsolation vo = new QueryDataIsolation();
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
        List<PrintOptionInfo> optionList = basicBaseDeviceMapper.getPrintOptionList(vo);
        optionList.stream().forEach(item -> {
            item.setLabel(item.getTypeName() + "(" + item.getDeviceCode() + ")");
        });
        return optionList;
    }

}
