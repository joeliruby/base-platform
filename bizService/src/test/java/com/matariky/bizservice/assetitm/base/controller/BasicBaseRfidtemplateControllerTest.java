package com.matariky.bizservice.assetitm.base.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import com.github.pagehelper.PageInfo;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidtemplate;
import com.matariky.bizservice.assetitm.base.service.BasicBaseRfidtemplateService;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class BasicBaseRfidtemplateControllerTest {

    @InjectMocks
    private BasicBaseRfidtemplateController basicBaseRfidtemplateController;

    @Mock
    private BasicBaseRfidtemplateService basicBaseRfidtemplateService;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

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
        BasicBaseRfidtemplate bean = new BasicBaseRfidtemplate();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        List<BasicBaseRfidtemplate> list = Collections.singletonList(bean);
        PageInfo<BasicBaseRfidtemplate> pageInfo = new PageInfo<>(list);
        when(basicBaseRfidtemplateService.getBasicBaseRfidtemplateAll(bean)).thenReturn(list);

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidtemplateController.list(request, bean, tenantId, pageIndex,
                perPage, jwt);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(pageInfo, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSave() {
        // Given
        BasicBaseRfidtemplate bean = new BasicBaseRfidtemplate();
        when(basicBaseRfidtemplateService.createBasicBaseRfidtemplateWithOrg(bean, request)).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidtemplateController.save(bean, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseRfidtemplate bean = new BasicBaseRfidtemplate();
        when(basicBaseRfidtemplateService.updateBasicBaseRfidtemplate(bean)).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidtemplateController.update(bean, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(basicBaseRfidtemplateService.deleteById(Long.parseLong(id))).thenReturn(true);

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidtemplateController.del(id, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseRfidtemplate bean = new BasicBaseRfidtemplate();
        when(basicBaseRfidtemplateService.selectById(id)).thenReturn(bean);

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidtemplateController.getOne(id, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(bean, result.get(AjaxResult.DATA_TAG));
    }

    // Add more test methods for other methods in BasicBaseRfidtemplateController
}
