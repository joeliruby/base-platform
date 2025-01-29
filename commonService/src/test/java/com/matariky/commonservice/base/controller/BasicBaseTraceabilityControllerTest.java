package com.matariky.commonservice.base.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseTraceability;
import com.matariky.commonservice.base.service.BasicBaseTraceabilityService;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class BasicBaseTraceabilityControllerTest {

    @InjectMocks
    private BasicBaseTraceabilityController basicBaseTraceabilityController;

    @Mock
    private BasicBaseTraceabilityService basicBaseTraceabilityService;

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
        BasicBaseTraceability bean = new BasicBaseTraceability();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        List<BasicBaseTraceability> traceabilityList = Collections.singletonList(bean);
        PageInfo<BasicBaseTraceability> pageInfo = new PageInfo<>(traceabilityList);
        when(basicBaseTraceabilityService.getBasicBaseTraceabilityAll(bean)).thenReturn(traceabilityList);

        // When
        AjaxResult result = (AjaxResult) basicBaseTraceabilityController.list(request, bean, tenantId, pageIndex,
                perPage, jwt);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(pageInfo);
    }

    @Test
    void testSave() {
        // Given
        BasicBaseTraceability bean = new BasicBaseTraceability();
        when(basicBaseTraceabilityService.createBasicBaseTraceabilityWithOrg(bean, request)).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicBaseTraceabilityController.save(bean, request, response);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseTraceability bean = new BasicBaseTraceability();
        when(basicBaseTraceabilityService.updateBasicBaseTraceability(bean)).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicBaseTraceabilityController.update(bean, request, response);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(basicBaseTraceabilityService.deleteById(Long.parseLong(id))).thenReturn(true);

        // When
        AjaxResult result = (AjaxResult) basicBaseTraceabilityController.del(id, request, response);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseTraceability bean = new BasicBaseTraceability();
        when(basicBaseTraceabilityService.selectById(id)).thenReturn(bean);

        // When
        AjaxResult result = (AjaxResult) basicBaseTraceabilityController.getOne(id, request, response);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(bean);
    }

    // Add more test methods for other methods in BasicBaseTraceabilityController
}
