package com.matariky.commonservice.base.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.matariky.commonservice.base.bean.BasicBaseAppVersion;
import com.matariky.commonservice.base.mapper.BasicBaseAppVersionMapper;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionAddVO;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionListVO;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionQueryVO;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionUpdateVO;
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
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 * Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBaseAppVersionService extends BaseServiceImpl<BasicBaseAppVersionMapper, BasicBaseAppVersion> {

    @Autowired
    private BasicBaseAppVersionMapper basicBaseAppversionMapper;
    @Autowired
    private CommonDictMapper commonDictMapper;
    @Autowired
    private CommonDictTypeMapper commonDictTypeMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CommonDictService commonDictService;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Value("${message.locale}")
    private String locale;

    /**
     * Query All
     */
    public List<BasicBaseAppVersionListVO> getBasicBaseAppversionAll(BasicBaseAppVersionQueryVO vo) {
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
        PageHelper.startPage(vo.getIndex(), vo.getPerPage());
        return basicBaseAppversionMapper.getBasicBaseAppversionAll(vo);
    }

    /**
     * New Method
     */
    public int createBasicBaseAppversionWithOrg(BasicBaseAppVersionAddVO addVO, MultipartFile fileUpload) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);

        CommonDict appTypeCodeDict = commonService.getAPPTypeCodeDict(addVO.getUpgradeType(), tenantId);
        BasicBaseAppVersion add = new BasicBaseAppVersion();
        BeanUtils.copyProperties(addVO, add);
        /** Retrieve File Name **/
        String fileName = fileUpload.getOriginalFilename();
        String bucket = "app-upload-package";
        try {
            minioUtil.createBucket(bucket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            InputStream inputStream = fileUpload.getInputStream();
            minioUtil.uploadFile(inputStream, bucket, fileName);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long userId = Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request));
        String uploadUrl = "api/v1/tenant/" + tenantId + "/file/downloadFile?bucket=" + bucket + "&objectName="
                + fileName;
        add.setUpgradeFile(fileName);
        add.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        add.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        add.setCreateTime(System.currentTimeMillis());
        add.setCreateBy(userId);
        add.setTenantId(tenantId);
        add.setAppName(appTypeCodeDict.getDictName());
        String QRCodeImageUrl = commonService.generateQRCodeImage(uploadUrl, tenantId, "app-package-qrcode");
        add.setDownloadQrcode(QRCodeImageUrl);
        add.setUpdateBy(userId);
        add.setUpdateTime(System.currentTimeMillis());
        return basicBaseAppversionMapper.createBasicBaseAppversion(add);
    }

    public int updateBasicBaseAppversion(BasicBaseAppVersionUpdateVO addVO, MultipartFile fileUpload) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);

        BasicBaseAppVersion update = new BasicBaseAppVersion();
        BeanUtils.copyProperties(addVO, update);
        if (fileUpload != null) {
            /** Retrieve File Name **/
            String fileName = fileUpload.getOriginalFilename();
            String bucket = "app-upload-package";
            try {
                minioUtil.createBucket(bucket);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                InputStream inputStream = fileUpload.getInputStream();
                minioUtil.uploadFile(inputStream, bucket, fileName);
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            update.setUpgradeFile(fileName);
            String uploadUrl = "api/v1/tenant/" + tenantId + "/file/downloadFile?bucket=" + bucket + "&objectName="
                    + fileName;
            String QRCodeImageUrl = commonService.generateQRCodeImage(uploadUrl, tenantId, "app-package-qrcode");
            update.setDownloadQrcode(QRCodeImageUrl);
        }

        CommonDictType appUpgradeType = commonDictTypeMapper.selectOne(Wrappers.lambdaQuery(CommonDictType.class)
                .eq(CommonDictType::getDictTypeKey, locale + "_APP_UPGRADE_TYPE")
                .eq(CommonDictType::getTenantId, tenantId));
        CommonDict deviceTypeCode = commonDictMapper.selectOne(Wrappers.lambdaQuery(CommonDict.class)
                .eq(CommonDict::getDictTypeId, appUpgradeType.getId())
                .eq(CommonDict::getDeleteTime, 0)
                .eq(CommonDict::getDictKey, addVO.getUpgradeType()));
        if (deviceTypeCode == null) {
            throw new QslException(MessageKey.APP_UPGRADE_TYPE_DICT_NOT_EXIST);
        }
        update.setUpdateTime(System.currentTimeMillis());
        update.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
        update.setAppName(deviceTypeCode.getDictName());

        return basicBaseAppversionMapper.updateById(update);
    }

    /**
     * Delete Method
     */
    public int delBasicBaseAppversionById(Long id) {
        return basicBaseAppversionMapper.delBasicBaseAppversionById(id);
    }

    /**
     * Retrieve Printer App
     * 
     * @return
     */
    public BasicBaseAppVersion getBasicBasePrintApp() {
        BasicBaseAppVersion bean = basicBaseAppversionMapper.getBasicBasePrintApp();
        bean.setUpgradeFile(
                "api/v1/tenant/1/file/downloadFile?bucket=app-upload-package&objectName=" + bean.getUpgradeFile());
        return bean;
    }

}
