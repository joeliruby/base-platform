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
import javax.servlet.http.HttpServletResponse;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseTraceabilityDetail;
import com.matariky.commonservice.base.service.BasicBaseTraceabilityDetailService;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.constant.PermissionConstant;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class BasicBaseTraceabilityDetailControllerTest {

    @InjectMocks
    private BasicBaseTraceabilityDetailController basicbasetraceabilitydetailcontroller;

    @Mock
    private BasicBaseTraceabilityDetailService basicBaseTraceabilityDetailService;

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
        BasicBaseTraceabilityDetail bean = new BasicBaseTraceabilityDetail();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        List<BasicBaseTraceabilityDetail> details = Collections.singletonList(bean);
        PageInfo<BasicBaseTraceabilityDetail> pageInfo = new PageInfo<>(details);
        when(basicBaseTraceabilityDetailService.getBasicBaseTraceabilityDetailAll(bean)).thenReturn(details);

        // When
        AjaxResult result = (AjaxResult) basicbasetraceabilitydetailcontroller.list(request, bean, tenantId, pageIndex,
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
        String hid = "hid123";
        Map<String, Object> params = mock(Map.class);
        when(request.getHeader("id")).thenReturn(hid);
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(1L);
        when(commonDictTypeService.getDictTypeByKey(anyString(), eq(PermissionConstant.DATA_ACCESS_PERMISSION)))
                .thenReturn(commonDictType);
        CommonDict dict = new CommonDict();
        dict.setDictValue("value");
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), eq(tenantId), eq(1L)))
                .thenReturn(dict);
        List<BasicBaseTraceabilityDetail> details = Collections.singletonList(new BasicBaseTraceabilityDetail());
        when(basicBaseTraceabilityDetailService.getBasicBaseTraceabilityDetailDAC(anyMap(), eq(request)))
                .thenReturn(details);
        when(basicBaseTraceabilityDetailService.getBasicBaseTraceabilityDetailDACCount(anyMap(), eq(request)))
                .thenReturn(1L);

        // When
        AjaxResult result = (AjaxResult) basicbasetraceabilitydetailcontroller.daclist(request, params, tenantId, jwt);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isInstanceOf(PageInfo.class);
    }

    @Test
    void testSave() {
        // Given
        BasicBaseTraceabilityDetail bean = new BasicBaseTraceabilityDetail();
        when(basicBaseTraceabilityDetailService.createBasicBaseTraceabilityDetailWithOrg(eq(bean), eq(request)))
                .thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicbasetraceabilitydetailcontroller.save(bean, request, response);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseTraceabilityDetail bean = new BasicBaseTraceabilityDetail();
        when(basicBaseTraceabilityDetailService.updateBasicBaseTraceabilityDetail(eq(bean))).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicbasetraceabilitydetailcontroller.update(bean, request, response);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(basicBaseTraceabilityDetailService.deleteById(eq(Long.parseLong(id)))).thenReturn(true);

        // When
        AjaxResult result = (AjaxResult) basicbasetraceabilitydetailcontroller.del(id, request, response);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseTraceabilityDetail detail = new BasicBaseTraceabilityDetail();
        when(basicBaseTraceabilityDetailService.selectById(eq(id))).thenReturn(detail);

        // When
        AjaxResult result = (AjaxResult) basicbasetraceabilitydetailcontroller.getOne(id, request, response);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(detail);
    }
}
