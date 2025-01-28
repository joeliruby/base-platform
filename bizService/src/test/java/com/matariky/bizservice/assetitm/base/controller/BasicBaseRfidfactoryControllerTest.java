package com.matariky.bizservice.assetitm.base.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidfactory;
import com.matariky.bizservice.assetitm.base.service.BasicBaseRfidfactoryService;
import com.matariky.bizservice.assetitm.base.vo.BasicBaseRfidfactoryAddVO;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class BasicBaseRfidfactoryControllerTest {

    @InjectMocks
    private BasicBaseRfidfactoryController basicBaseRfidfactoryController;

    @Mock
    private BasicBaseRfidfactoryService basicBaseRfidfactoryService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private MultipartFile file;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseRfidfactory bean = new BasicBaseRfidfactory();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidfactoryController.list(request, bean, tenantId, pageIndex,
                perPage, jwt);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testSave() {
        // Given
        BasicBaseRfidfactoryAddVO bean = new BasicBaseRfidfactoryAddVO();
        String tenantId = "tenant1";
        String jwt = "jwtToken";

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidfactoryController.save(bean, request, response, tenantId, jwt);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testGetExport() {
        // Given
        Long id = 1L;
        String tenantId = "tenant1";
        String jwt = "jwtToken";

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidfactoryController.getExport(request, response, id, tenantId, jwt);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testUpload() {
        // Given
        String tenantId = "tenant1";
        String jwt = "jwtToken";
        Long id = 1L;

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidfactoryController.upload(file, request, response, jwt, tenantId,
                id);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testDownLoadTemplate() {
        // When
        basicBaseRfidfactoryController.downLoadTemplate();

        // Then
        verify(basicBaseRfidfactoryService, times(1)).downLoadTemplate();
    }
}
