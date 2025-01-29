package com.matariky.commonservice.base.controller;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseDeviceCorrelation;
import com.matariky.commonservice.base.service.BasicBaseDeviceCorrelationService;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.constant.PermissionConstant;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class BasicBaseDeviceCorrelationControllerTest {

    @InjectMocks
    private BasicBaseDeviceCorrelationController basicbasedevicecorrelationcontroller;

    @Mock
    private BasicBaseDeviceCorrelationService basicBaseDeviceCorrelationService;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseDeviceCorrelation bean = new BasicBaseDeviceCorrelation();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        List<BasicBaseDeviceCorrelation> list = Collections.singletonList(bean);
        PageInfo<BasicBaseDeviceCorrelation> pageInfo = new PageInfo<>(list);
        when(basicBaseDeviceCorrelationService.getBasicBaseDeviceCorrelationAll(bean)).thenReturn(list);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecorrelationcontroller.list(request, bean, tenantId, pageIndex,
                perPage, jwt);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(pageInfo);
    }

    @Test
    void testDaclist() {
        // Given
        String tenantId = "tenant1";
        String jwt = "jwtToken";
        when(request.getHeader("id")).thenReturn("id123");
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(1L);
        when(commonDictTypeService.getDictTypeByKey(anyString(), eq(PermissionConstant.DATA_ACCESS_PERMISSION)))
                .thenReturn(commonDictType);
        CommonDict dict = new CommonDict();
        dict.setDictValue("value");
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), eq(tenantId), eq(1L)))
                .thenReturn(dict);
        Map<String, Object> params = mock(Map.class);
        when(params.get("index")).thenReturn("1");
        when(params.get("perPage")).thenReturn("10");
        List<BasicBaseDeviceCorrelation> list = Collections.singletonList(new BasicBaseDeviceCorrelation());
        when(basicBaseDeviceCorrelationService.getBasicBaseDeviceCorrelationDAC(anyMap(), eq(request)))
                .thenReturn(list);
        when(basicBaseDeviceCorrelationService.getBasicBaseDeviceCorrelationDACCount(anyMap(), eq(request)))
                .thenReturn(1L);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecorrelationcontroller.daclist(request, params, tenantId, jwt);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
    }

    @Test
    void testSave() {
        // Given
        BasicBaseDeviceCorrelation bean = new BasicBaseDeviceCorrelation();
        when(basicBaseDeviceCorrelationService.createBasicBaseDeviceCorrelationWithOrg(eq(bean), eq(request)))
                .thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecorrelationcontroller.save(bean, request, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseDeviceCorrelation bean = new BasicBaseDeviceCorrelation();
        when(basicBaseDeviceCorrelationService.updateBasicBaseDeviceCorrelation(eq(bean))).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecorrelationcontroller.update(bean, request, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(basicBaseDeviceCorrelationService.deleteById(eq(Long.parseLong(id)))).thenReturn(true);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecorrelationcontroller.del(id, request, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseDeviceCorrelation bean = new BasicBaseDeviceCorrelation();
        when(basicBaseDeviceCorrelationService.selectById(eq(id))).thenReturn(bean);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecorrelationcontroller.getOne(id, request, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(bean);
    }
}
