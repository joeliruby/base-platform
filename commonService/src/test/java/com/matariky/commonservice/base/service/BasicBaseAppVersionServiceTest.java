package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.matariky.commonservice.base.bean.BasicBaseAppVersion;
import com.matariky.commonservice.base.mapper.BasicBaseAppVersionMapper;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionListVO;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionQueryVO;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionUpdateVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseAppVersionServiceTest {

    @InjectMocks
    private BasicBaseAppVersionService basicbaseappversionservice;

    @Mock
    private BasicBaseAppVersionMapper basicBaseAppversionMapper;
    @Mock
    private CommonDictService commonDictService;
    @Mock
    private CommonDictTypeService commonDictTypeService;
    @Mock
    private MinioUtil minioUtil;
    @Mock
    private HttpServletRequest request;
    @Mock
    private MultipartFile fileUpload;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseAppversionAll() {
        // Given
        BasicBaseAppVersionQueryVO queryVO = new BasicBaseAppVersionQueryVO();
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(new CommonDictType());
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(null);
        when(basicBaseAppversionMapper.getBasicBaseAppversionAll(queryVO)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseAppVersionListVO> result = basicbaseappversionservice.getBasicBaseAppversionAll(queryVO);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testUpdateBasicBaseAppversion() throws Exception {
        // Given
        BasicBaseAppVersionUpdateVO updateVO = new BasicBaseAppVersionUpdateVO();
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(fileUpload.getOriginalFilename()).thenReturn("file.txt");
        when(fileUpload.getInputStream()).thenReturn(mock(InputStream.class));
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(new CommonDictType());
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(new CommonDict());
        when(basicBaseAppversionMapper.updateById(any())).thenReturn(1);

        // When
        int result = basicbaseappversionservice.updateBasicBaseAppversion(updateVO, fileUpload);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseAppversionById() {
        // Given
        Long id = 1L;
        when(basicBaseAppversionMapper.delBasicBaseAppversionById(id)).thenReturn(1);

        // When
        int result = basicbaseappversionservice.delBasicBaseAppversionById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBasePrintApp() {
        // Given
        BasicBaseAppVersion appVersion = new BasicBaseAppVersion();
        appVersion.setUpgradeFile("file.txt");
        when(basicBaseAppversionMapper.getBasicBasePrintApp()).thenReturn(appVersion);

        // When
        BasicBaseAppVersion result = basicbaseappversionservice.getBasicBasePrintApp();

        // Then
        assertNotNull(result);
        assertEquals("api/v1/tenant/1/file/downloadFile?bucket=app-upload-package&objectName=file.txt",
                result.getUpgradeFile());
    }
}
