package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.matariky.commonservice.base.bean.BasicBaseDevicePackage;
import com.matariky.commonservice.base.mapper.BasicBaseDevicePackageMapper;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageAddVO;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageUpdateVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceUpgradeListVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseDevicePackageServiceTest {

    @InjectMocks
    private BasicBaseDevicePackageService basicbasedevicepackageservice;

    @Mock
    private BasicBaseDevicePackageMapper basicBaseDevicepackageMapper;

    @Mock
    private MinioUtil minioUtil;

    @Mock
    private HttpServletRequest request;

    @Mock
    private CommonService commonService;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDevicepackageAll() {
        // Given
        BasicBaseDeviceUpgradeListVO vo = new BasicBaseDeviceUpgradeListVO();
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(new CommonDictType());
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(new CommonDict());
        when(basicBaseDevicepackageMapper.getBasicBaseDevicepackageAll(vo))
                .thenReturn(List.of(new BasicBaseDevicePackageInfoVO()));

        // When
        List<BasicBaseDevicePackageInfoVO> result = basicbasedevicepackageservice.getBasicBaseDevicepackageAll(vo);

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseDevicepackageWithOrg() throws IOException {
        // Given
        BasicBaseDevicePackageAddVO addVO = new BasicBaseDevicePackageAddVO();
        MultipartFile fileUpload = mock(MultipartFile.class);
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(commonService.generateMD5(fileUpload)).thenReturn("md5");
        when(fileUpload.getOriginalFilename()).thenReturn("fileName");
        when(fileUpload.getInputStream()).thenReturn(mock(InputStream.class));
        try {
            doNothing().when(minioUtil).createBucket(anyString());
            doNothing().when(minioUtil).uploadFile(any(InputStream.class), anyString(), anyString());
        } catch (Exception e) {
            fail("failed to upload file");
        }
        when(basicBaseDevicepackageMapper.createBasicBaseDevicepackage(any(BasicBaseDevicePackage.class)))
                .thenReturn(1);

        // When
        basicbasedevicepackageservice.createBasicBaseDevicepackageWithOrg(addVO, fileUpload);

        // Then
        verify(basicBaseDevicepackageMapper, times(1)).createBasicBaseDevicepackage(any(BasicBaseDevicePackage.class));
    }

    @Test
    void testUpdateBasicBaseDevicepackage() throws Exception {
        // Given
        BasicBaseDevicePackageUpdateVO updateVO = new BasicBaseDevicePackageUpdateVO();
        MultipartFile fileUpload = mock(MultipartFile.class);
        String jwt = "jwt";
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(commonService.generateMD5(fileUpload)).thenReturn("md5");
        when(fileUpload.getOriginalFilename()).thenReturn("fileName");
        when(fileUpload.getInputStream()).thenReturn(mock(InputStream.class));
        doNothing().when(minioUtil).createBucket(anyString());
        doNothing().when(minioUtil).uploadFile(any(InputStream.class), anyString(), anyString());
        when(basicBaseDevicepackageMapper.updateById(any(BasicBaseDevicePackage.class))).thenReturn(1);

        // When
        int result = basicbasedevicepackageservice.updateBasicBaseDevicepackage(updateVO, fileUpload, jwt);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDevicepackageById() {
        // Given
        Long id = 1L;
        when(basicBaseDevicepackageMapper.delBasicBaseDevicepackageById(id)).thenReturn(1);

        // When
        int result = basicbasedevicepackageservice.delBasicBaseDevicepackageById(id);

        // Then
        assertEquals(1, result);
    }

    // Add more test methods for other methods in BasicBaseDevicePackageService
}
