package com.matariky.commonservice.base.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.matariky.commonservice.base.bean.BasicBaseFormConfig;
import com.matariky.commonservice.base.bean.BasicBaseFormExtend;
import com.matariky.commonservice.base.mapper.BasicBaseFormConfigMapper;
import com.matariky.commonservice.base.mapper.BasicBaseFormExtendMapper;
import com.matariky.commonservice.base.mapper.BasicBaseGoodsMapper;
import com.matariky.commonservice.base.vo.AddExtendFieldDetailVO;
import com.matariky.commonservice.base.vo.AddExtendFieldInfoVO;
import com.matariky.commonservice.base.vo.AddExtendFieldVO;
import com.matariky.commonservice.base.vo.BasicBaseFormExtendQueryVO;
import com.matariky.commonservice.base.vo.BasicBaseGoodsListVO;
import com.matariky.commonservice.base.vo.RfidTagInfo;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.PermissionConstant;
import com.matariky.exception.QslException;
import com.matariky.utils.DrawServer;
import com.matariky.utils.EasyExcelUtil;
import com.matariky.utils.QRCodeGenerator;
import com.matariky.utils.StringUtils;
import com.matariky.utils.TokenUtils;

import cn.hutool.core.lang.UUID;

/**
 * Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
@Component
public class CommonService {
    @Value("${message.locale}")
    private String locale;
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private CommonDictTypeMapper commonDictTypeMapper;
    @Autowired
    private CommonDictMapper commonDictMapper;
    @Value("${web.api.url}")
    private String applicationUrl;
    @Autowired
    private ExcelService excelService;
    @Autowired
    private BasicBaseFormConfigMapper basicBaseFormConfigMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BasicBaseFormExtendMapper basicBaseFormExtendMapper;
    @Autowired
    private BasicBaseGoodsMapper basicBaseGoodsMapper;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Autowired
    private CommonDictService commonDictService;
    @Value("${device.upgrade.url:http://network-signoz-karaf:9192/upgrade}")
    private String deviceUpgradeUrl;
    @Value("${upgrade.file.url:http://120.78.95.25:8012}")
    private String upgradeFileUrl;

    /**
     * Generation QR Code
     *
     * @param text
     * @param tenantId
     * @return
     */
    public String generateQRCodeImage(String text, String tenantId, String bucket) {
        String uploadUrl = StringUtils.EMPTY;
        try {
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
            /** Create Image **/
            String property = System.getProperty("user.dir");
            File file = new File(property + File.separator + fileName);
            /** QR Code overlay on Image **/
            QRCodeGenerator.generateQRCodeImage(applicationUrl + text, 350, 350, file.getAbsolutePath());
            try {
                minioUtil.createBucket(bucket);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                InputStream inputStream = new FileInputStream(file);
                minioUtil.uploadFile(inputStream, bucket, fileName);
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (file.exists()) {
                file.delete();
            }
            uploadUrl = "api/v1/tenant/" + tenantId + "/file/downloadFile?bucket=" + bucket + "&objectName=" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadUrl;
    }

    /**
     * Retrieve Device Type Data Dictionary
     *
     * @param dictKey
     * @return
     */
    public CommonDict getDeviceTypeCodeDict(String dictKey, String tenantId) {
        CommonDictType dictType = commonDictTypeMapper.selectOne(Wrappers.lambdaQuery(CommonDictType.class)
                .eq(CommonDictType::getDictTypeKey, locale + "_DEVICE_TYPE_CODE")
                .eq(CommonDictType::getIsActive, 1)
                .eq(CommonDictType::getTenantId, tenantId));
        if (dictType == null) {
            throw new QslException(MessageKey.DEVICE_TYPE_CODE_DICT_TYPE_NOT_EXIST);
        }
        CommonDict deviceTypeCode = commonDictMapper.selectOne(Wrappers.lambdaQuery(CommonDict.class)
                .eq(CommonDict::getDictTypeId, dictType.getId())
                .eq(CommonDict::getDictKey, dictKey)
                .eq(CommonDict::getIsActive, 1)
                .eq(CommonDict::getTenantId, tenantId)
                .eq(CommonDict::getDeleteTime, 0));
        if (deviceTypeCode == null) {
            throw new QslException(MessageKey.DEVICE_TYPE_CODE_DICT_NOT_EXIST);
        }
        return deviceTypeCode;
    }

    /**
     * Retrieve App Client Type - Data Dictionary
     *
     * @param dictValue
     * @return
     */
    public CommonDict getAPPTypeCodeDict(String dictValue, String tenantId) {
        CommonDictType dictType = commonDictTypeMapper.selectOne(Wrappers.lambdaQuery(CommonDictType.class)
                .eq(CommonDictType::getDictTypeKey, locale + "_APP_UPGRADE_TYPE")
                .eq(CommonDictType::getIsActive, 1)
                .eq(CommonDictType::getTenantId, tenantId));
        if (dictType == null) {
            throw new QslException(MessageKey.APP_UPGRADE_TYPE_DICT_TYPE_NOT_EXIST);
        }
        CommonDict deviceTypeCode = commonDictMapper.selectOne(Wrappers.lambdaQuery(CommonDict.class)
                .eq(CommonDict::getDictTypeId, dictType.getId())
                .eq(CommonDict::getDictValue, dictValue)
                .eq(CommonDict::getIsActive, 1)
                .eq(CommonDict::getTenantId, tenantId)
                .eq(CommonDict::getDeleteTime, 0));
        if (deviceTypeCode == null) {
            throw new QslException(MessageKey.APP_UPGRADE_TYPE_DICT_NOT_EXIST);
        }
        return deviceTypeCode;
    }

    /**
     * Generation excel Import
     *
     * @param response
     * @param classVO
     * @param fileName
     * @param sheetName
     */
    public void createTemplate(HttpServletResponse response, Class<?> classVO, String fileName, String sheetName) {
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(fileName + "_" + System.currentTimeMillis() + ".xlsx", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            EasyExcel.write(outputStream, classVO)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet(sheetName)
                    .doWrite(new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generation excel Import Drop Down Box
     *
     * @param response
     * @param classVO
     * @param fileName
     * @param sheetName
     * @param tenantId
     */
    public void createTemplate(HttpServletResponse response, Class<?> classVO, String fileName, String sheetName,
            String tenantId) {
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(fileName + "_" + System.currentTimeMillis() + ".xlsx", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).excelType(ExcelTypeEnum.XLSX).build();
            WriteSheet writeSheet = EasyExcelUtil.writeSelectedSheet(classVO, 0, sheetName, excelService, tenantId);
            excelWriter.write(new ArrayList<String>(), writeSheet);
            excelWriter.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve Device Power - Data Dictionary
     *
     * @param
     * @return
     */
    public List<CommonDict> getDeviceDbmDict(String tenantId) {
        CommonDictType dictType = commonDictTypeMapper.selectOne(Wrappers.lambdaQuery(CommonDictType.class)
                .eq(CommonDictType::getDictTypeKey, locale + "_DEVICE_DBM_CONFIG")
                .eq(CommonDictType::getIsActive, 1)
                .eq(CommonDictType::getTenantId, tenantId));
        if (dictType == null) {
            return new ArrayList<>();
        }
        List<CommonDict> deviceTypeCode = commonDictMapper.selectList(Wrappers.lambdaQuery(CommonDict.class)
                .eq(CommonDict::getDictTypeId, dictType.getId())
                .eq(CommonDict::getIsActive, 1)
                .eq(CommonDict::getDeleteTime, 0)
                .eq(CommonDict::getTenantId, tenantId));
        return deviceTypeCode;
    }

    /**
     * Retrieve Label Data
     *
     * @return
     */
    public RfidTagInfo getRfidCodes() {
        RfidTagInfo rfidTagInfo = new RfidTagInfo();
        DrawServer ds = new DrawServer();
        String rfidStr = ds.setServer(9998);
        String[] rfids = rfidStr.split(";");
        if (rfids.length == 2) {
            rfidTagInfo.setEpc(rfids[0]);
            rfidTagInfo.setTagId(rfids[1]);
        }
        return rfidTagInfo;
    }

    /**
     * 长传 Image
     *
     * @param file
     * @param bucket
     */
    public String uploadImg(MultipartFile file, String bucket) {
        try {
            minioUtil.createBucket(bucket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /** Retrieve File Name **/
        String fileName = file.getOriginalFilename();
        try {
            minioUtil.uploadFile(file.getInputStream(), bucket, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "api/v1/tenant/1/file/downloadFile?bucket=" + bucket + "&objectName=" + fileName;
    }

    /**
     * generate MD 5 for tar.gz
     *
     * @param gzFile
     * @return
     */
    public String generateMD5(MultipartFile gzFile) {
        String md5 = null;
        try {
            InputStream inputStream = gzFile.getInputStream();
            TarArchiveInputStream tarInputStream = new TarArchiveInputStream(new BufferedInputStream(inputStream));
            TarArchiveEntry entry;
            while ((entry = tarInputStream.getNextTarEntry()) != null) {
                if (!entry.isDirectory()) {
                    try {
                        md5 = calculateMD5(tarInputStream);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return md5;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return md5;
    }

    private String calculateMD5(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5Bytes = md.digest(IOUtils.toByteArray(inputStream));
        StringBuilder md5Hex = new StringBuilder();
        for (byte b : md5Bytes) {
            md5Hex.append(String.format("%02X", b));
        }
        return md5Hex.toString();
    }

    /**
     * map undercore to camel case
     *
     * @param map
     * @return
     */
    public Map<String, Object> mapToCamelCase(HashMap map) {
        HashMap<String, Object> newMap = new HashMap();
        Iterator<Map.Entry> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String key = entry.getKey().toString();
            String camelCaseKey = toCamelCase(key);
            newMap.put(camelCaseKey, entry.getValue());
        }
        return newMap;
    }

    /**
     * underscore to camel case
     *
     * @param input
     * @return
     */
    public String toCamelCase(String input) {
        if (!input.contains("_")) {
            return input;
        }
        /**
         * Use the regular expression to replace the downline line into the space, and
         * then use trim () to remove the front and tail space
         */
        String[] words = input.replaceAll("_", " ").trim().split("\\s+");
        StringBuilder camelCase = new StringBuilder();
        /**
         * Patch the first letters of each word and stitch it up
         */
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                /**
                 * The first word is kept lowercase
                 */
                camelCase.append(words[i].toLowerCase());
            } else {
                camelCase.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));
            }
        }
        return camelCase.toString();
    }

    /**
     * Add Augment Field
     */
    @Transactional(rollbackFor = Exception.class)
    public void addExtendField(AddExtendFieldVO vo) {
        Long userId = Long.valueOf(TokenUtils.extractUserIdFromHttpReqeust(request));
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        List<Long> addIds = new ArrayList<>();

        vo.getList()
                .sort(Comparator.comparing(AddExtendFieldInfoVO::getId, Comparator.nullsLast(Comparable::compareTo)));
        final int[] index = { 0 };
        vo.getList().stream().forEach(item -> {
            if (item.getId() == null) {
                /**
                 * New
                 */
                Long count = basicBaseFormConfigMapper.selectCount(Wrappers.lambdaQuery(BasicBaseFormConfig.class)
                        .eq(BasicBaseFormConfig::getDeleteTime, 0)
                        .eq(BasicBaseFormConfig::getName, vo.getName())
                        .eq(BasicBaseFormConfig::getFieldName, item.getFieldName()));
                if (count > 0) {
                    throw new QslException(MessageKey.FIELD_NAME_NOT_REPEAT);
                }
                BasicBaseFormConfig config = new BasicBaseFormConfig();
                config.setName(vo.getName());
                config.setFieldName(item.getFieldName());
                config.setFieldMap("field_" + index[0]++);
                config.setFieldType(item.getFieldType());
                config.setCreateBy(userId);
                config.setUpdateBy(userId);
                config.setCreateTime(System.currentTimeMillis());
                config.setUpdateTime(System.currentTimeMillis());
                config.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
                config.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
                config.setTenantId(tenantId);
                config.setIsRequired(item.getIsRequired());
                basicBaseFormConfigMapper.createBasicBaseFormConfig(config);
                addIds.add(config.getId());
            } else if (item.getId() != null) {
                /**
                 * Update
                 */
                Long count = basicBaseFormConfigMapper.selectCount(Wrappers.lambdaQuery(BasicBaseFormConfig.class)
                        .eq(BasicBaseFormConfig::getDeleteTime, 0)
                        .eq(BasicBaseFormConfig::getName, vo.getName())
                        .eq(BasicBaseFormConfig::getFieldName, item.getFieldName())
                        .ne(BasicBaseFormConfig::getId, item.getId()));
                if (count > 0) {
                    throw new QslException(MessageKey.FIELD_NAME_NOT_REPEAT);
                }
                BasicBaseFormConfig config = basicBaseFormConfigMapper.getBasicBaseFormConfigById(item.getId());
                BasicBaseFormConfig update = new BasicBaseFormConfig();
                update.setId(item.getId());
                update.setUpdateBy(userId);
                update.setUpdateTime(System.currentTimeMillis());
                update.setFieldName(item.getFieldName());
                update.setFieldType(item.getFieldType());
                update.setIsRequired(item.getIsRequired());
                String fieldMap = config.getFieldMap();
                String[] fieldArry = fieldMap.split("_");
                if (Integer.valueOf(fieldArry[1]).intValue() == index[0]) {
                    index[0]++;
                }
                basicBaseFormConfigMapper.updateBasicBaseFormConfig(update);
            }
        });
        /**
         * Delete
         */
        List<Long> ids = basicBaseFormConfigMapper.selectConfigIds(tenantId);
        ids = ids.stream().filter(item -> !addIds.contains(item)).collect(Collectors.toList());
        List<Long> newIds = vo.getList().stream().filter(item -> item.getId() != null).map(AddExtendFieldInfoVO::getId)
                .collect(Collectors.toList());
        List<Long> deleteIds = ids.stream().filter(item -> !newIds.contains(item)).collect(Collectors.toList());
        if (deleteIds.isEmpty()) {
            return;
        }
        List<BasicBaseFormConfig> deleteConfig = basicBaseFormConfigMapper
                .selectList(Wrappers.lambdaQuery(BasicBaseFormConfig.class)
                        .in(BasicBaseFormConfig::getId, deleteIds));
        deleteIds.stream().forEach(item -> {
            basicBaseFormConfigMapper.delBasicBaseFormConfigById(item);
        });

        /**
         * The old value of the business DATA before empty
         */
        List<BasicBaseFormExtend> basicBaseFormExtends = basicBaseFormExtendMapper.selectExtendClearList();
        basicBaseFormExtends.stream().forEach(item -> {
            BasicBaseFormExtend update = new BasicBaseFormExtend();
            update.setId(item.getId());
            deleteConfig.stream().forEach(config -> {
                try {
                    Class<? extends BasicBaseFormExtend> readerClass = update.getClass();
                    Field field = readerClass.getDeclaredField(config.getFieldMap().replaceAll("_", ""));
                    field.setAccessible(true);
                    field.set(update, "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            basicBaseFormExtendMapper.updateBasicBaseFormExtend(update);
        });
    }

    /**
     * Via form Query Augment Field
     *
     * @param name
     */
    public List<AddExtendFieldDetailVO> getExtendField(String name) {
        List<AddExtendFieldDetailVO> list = basicBaseFormConfigMapper.getBasicBaseFormConfigByName(name);

        BasicBaseGoodsListVO vo = new BasicBaseGoodsListVO();
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
        List<HashMap> basicBaseGoodsAll = basicBaseGoodsMapper.getBasicBaseGoodsAll(vo);

        List<Long> businessIds = basicBaseGoodsAll.stream().map(item -> {
            Long id = Long.valueOf(item.get("id").toString());
            return id;
        }).collect(Collectors.toList());

        list.stream().forEach(item -> {
            /**
             * This field WETHER exists DATA
             */
            BasicBaseFormExtendQueryVO extend = new BasicBaseFormExtendQueryVO();
            extend.setBusinessIds(businessIds);
            try {
                Class<? extends BasicBaseFormExtendQueryVO> readerClass = extend.getClass();
                Field field = readerClass.getDeclaredField(item.getFieldMap().replaceAll("_", ""));
                field.setAccessible(true);
                field.set(extend, "1");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Integer count = basicBaseFormExtendMapper.getBasicBaseFormExtendCount(extend);
            if (count > 0) {
                item.setDisable(true);
            } else {
                item.setDisable(false);
            }
            item.setFieldName(item.getFieldName().replaceAll("_", ""));
            item.setFieldMap(item.getFieldMap().replaceAll("_", ""));
        });
        return list;
    }

    /**
     * 向karaf发送 Upgrade Command
     */
    public void deviceUpgrade(String md5, String vers, String deviceCode) {
        JSONObject jsonObject = new JSONObject();
        RestTemplate restTemplate = new RestTemplate();
        jsonObject.put("cmd", "60005");
        jsonObject.put("messageCode", 7);
        JSONObject data = new JSONObject();
        data.put("md5", md5);
        data.put("vers", vers);
        upgradeFileUrl = "http://172.28.1.17:8012";
        data.put("web", upgradeFileUrl + "/readerFile");
        jsonObject.put("data", data);
        jsonObject.put("deviceCode", deviceCode);
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(jsonObject);
        try {
            restTemplate.postForObject(deviceUpgradeUrl, requestEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new QslException(MessageKey.DEVICE_UPGRAD_FAIL);
        }
    }
}
