package com.matariky.commonservice.base.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseFormConfig;
import com.matariky.commonservice.base.service.BasicBaseFormConfigService;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseFormConfigControllerTest {

    @InjectMocks
    private BasicBaseFormConfigController basicbaseformconfigcontroller;

    @Mock
    private BasicBaseFormConfigService basicBaseFormConfigService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseFormConfig bean = new BasicBaseFormConfig();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        List<BasicBaseFormConfig> configs = Collections.singletonList(bean);
        when(basicBaseFormConfigService.getBasicBaseFormConfigAll(bean)).thenReturn(configs);

        // When
        AjaxResult result = (AjaxResult) basicbaseformconfigcontroller.list(request, bean, tenantId, pageIndex, perPage,
                jwt);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertNotNull(result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSave() {
        // Given
        BasicBaseFormConfig bean = new BasicBaseFormConfig();
        when(basicBaseFormConfigService.createBasicBaseFormConfigWithOrg(bean, request)).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicbaseformconfigcontroller.save(bean, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseFormConfig bean = new BasicBaseFormConfig();
        when(basicBaseFormConfigService.updateBasicBaseFormConfig(bean)).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicbaseformconfigcontroller.update(bean, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(basicBaseFormConfigService.deleteById(Long.parseLong(id))).thenReturn(true);

        // When
        AjaxResult result = (AjaxResult) basicbaseformconfigcontroller.del(id, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseFormConfig config = new BasicBaseFormConfig();
        when(basicBaseFormConfigService.selectById(id)).thenReturn(config);

        // When
        AjaxResult result = (AjaxResult) basicbaseformconfigcontroller.getOne(id, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(config, result.get(AjaxResult.DATA_TAG));
    }

    // Add more test methods for other methods in BasicBaseFormConfigController
}
