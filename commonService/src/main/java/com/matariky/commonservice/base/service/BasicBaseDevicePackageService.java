package com.matariky.commonservice.base.service;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageHelper;
import com.matariky.commonservice.base.bean.BasicBaseDevicePackage;
import com.matariky.commonservice.base.bean.BasicBaseDeviceType;
import com.matariky.commonservice.base.mapper.BasicBaseDevicePackageMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceTypeMapper;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageAddVO;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageUpdateVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceUpgradeListVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.PermissionConstant;
import com.matariky.exception.QslException;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBaseDevicePackageService extends BaseServiceImpl<BasicBaseDevicePackageMapper, BasicBaseDevicePackage> implements BaseService<BasicBaseDevicePackage> {

    @Autowired
    private BasicBaseDevicePackageMapper basicBaseDevicepackageMapper;
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BasicBaseDeviceTypeMapper basicBaseDevicetypeMapper;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CommonDictService commonDictService;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Value("${job.api.url}")
    private String jobUrl;

    /**
     *  Query 所有分页
     */
    public List<BasicBaseDevicePackageInfoVO> getBasicBaseDevicepackageAll(BasicBaseDeviceUpgradeListVO vo) {
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
        return basicBaseDevicepackageMapper.getBasicBaseDevicepackageAll(vo);
    }


    /**
     * New
     *
     * @param addVO
     * @param fileUpload
     * @return
     */
    public void createBasicBaseDevicepackageWithOrg(BasicBaseDevicePackageAddVO addVO, MultipartFile fileUpload) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        String md5 = commonService.generateMD5(fileUpload);
        BasicBaseDevicePackage add = new BasicBaseDevicePackage();
        BeanUtils.copyProperties(addVO, add);
        /**获取文件名**/
        String fileName = fileUpload.getOriginalFilename();
        String bucket = "device-upload-package";
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
        BasicBaseDeviceType basicBaseDevicetype = basicBaseDevicetypeMapper.selectById(addVO.getTypeId());
        if (basicBaseDevicetype == null) {
            throw new QslException(MessageKey.DEVICE_TYPE_NOT_EXIST);
        }
        String uploadUrl = "api/v1/tenant/" + tenantId + "/file/downloadFile?bucket=" + bucket + "&objectName=" + fileName;
        add.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        add.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        add.setCreateTime(System.currentTimeMillis());
        long userId = Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request));
        add.setCreateBy(userId);
        add.setTenantId(tenantId);
        add.setTypeName(basicBaseDevicetype.getTypeName());
        String QRCodeImageUrl = commonService.generateQRCodeImage(uploadUrl, tenantId, "device-package-qrcode");
        add.setDownloadQrcode(QRCodeImageUrl);
        add.setUpgradeFile(uploadUrl);
        add.setUpdateBy(userId);
        add.setUpdateTime(System.currentTimeMillis());
        add.setMd5(md5);
        basicBaseDevicepackageMapper.createBasicBaseDevicepackage(add);

//        BasicBaseDeviceType deviceType = basicBaseDevicetypeMapper.getBasicBaseDevicetypeById(addVO.getTypeId());
//        //自动升级
//        if ("Y".equals(deviceType.getIsAutoUpgrade())) {
//            if ("immediate".equals(deviceType.getUpgradeType())) {
//            } else if ("scheduler".equals(deviceType.getUpgradeType())) {
//                /** Add Timer Task  **/
//                RestTemplate restTemplate = new RestTemplate();
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("typeId", deviceType.getId());
//                jsonObject.put("packageId", add.getId());
//                HttpEntity<JSONObject> requestEntity = new HttpEntity<>(jsonObject);
//                restTemplate.postForObject(jobUrl + "/api/job/v1/tenant/" + tenantId + "/addDeviceUpgradeJob", requestEntity, String.class);
//            }
//        }

    }


    public int updateBasicBaseDevicepackage(BasicBaseDevicePackageUpdateVO updateVO, MultipartFile fileUpload, String jwt) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        BasicBaseDevicePackage update = new BasicBaseDevicePackage();
        BeanUtils.copyProperties(updateVO, update);
        if (fileUpload != null) {
            String md5 = commonService.generateMD5(fileUpload);
            update.setMd5(md5);
            /**获取文件名**/
            String fileName = fileUpload.getOriginalFilename();
            String bucket = "device-upload-package";
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
            String uploadUrl = "api/v1/tenant/" + tenantId + "/file/downloadFile?bucket=" + bucket + "&objectName=" + fileName;
            String QRCodeImageUrl = commonService.generateQRCodeImage(uploadUrl, tenantId, "device-package-qrcode");
            update.setDownloadQrcode(QRCodeImageUrl);
            update.setUpgradeFile(uploadUrl);
        }
        BasicBaseDeviceType basicBaseDevicetype = basicBaseDevicetypeMapper.selectById(updateVO.getTypeId());
        if (basicBaseDevicetype == null) {
            throw new QslException(MessageKey.DEVICE_TYPE_NOT_EXIST);
        }
        update.setTypeName(basicBaseDevicetype.getTypeName());
        update.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
        update.setUpdateTime(System.currentTimeMillis());
        return basicBaseDevicepackageMapper.updateById(update);
    }

    /**
     * 删除方法
     *
     * @param id
     * @return
     */
    public int delBasicBaseDevicepackageById(Long id) {
        return basicBaseDevicepackageMapper.delBasicBaseDevicepackageById(id);
    }

}
