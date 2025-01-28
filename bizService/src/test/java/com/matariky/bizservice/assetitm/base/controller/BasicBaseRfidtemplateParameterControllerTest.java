package com.matariky.bizservice.assetitm.base.controller;

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
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidtemplateParameter;
import com.matariky.bizservice.assetitm.base.service.BasicBaseRfidtemplateParameterService;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.constant.PermissionConstant;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class BasicBaseRfidtemplateParameterControllerTest {

    @InjectMocks
    private BasicBaseRfidtemplateParameterController basicBaseRfidtemplateParameterController;

    @Mock
    private BasicBaseRfidtemplateParameterService basicBaseRfidtemplateParameterService;

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
        BasicBaseRfidtemplateParameter bean = new BasicBaseRfidtemplateParameter();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        List<BasicBaseRfidtemplateParameter> list = Collections.singletonList(bean);
        PageInfo<BasicBaseRfidtemplateParameter> pageInfo = new PageInfo<>(list);
        when(basicBaseRfidtemplateParameterService.getBasicBaseRfidtemplateParameterAll(bean)).thenReturn(list);

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidtemplateParameterController.list(request, bean, tenantId,
                pageIndex, perPage, jwt);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(pageInfo);
    }

    @Test
    void testDaclist() {
        // Given
        Map<String, Object> params = mock(Map.class);
        String tenantId = "tenant1";
        String jwt = "jwtToken";
        String hid = "hid123";
        when(request.getHeader("id")).thenReturn(hid);
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(1L);
        when(commonDictTypeService.getDictTypeByKey(anyString(), eq(PermissionConstant.DATA_ACCESS_PERMISSION)))
                .thenReturn(commonDictType);
        CommonDict dict = new CommonDict();
        dict.setDictValue("value");
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), eq(tenantId), eq(1L)))
                .thenReturn(dict);
        List<BasicBaseRfidtemplateParameter> list = Collections.singletonList(new BasicBaseRfidtemplateParameter());
        when(basicBaseRfidtemplateParameterService.getBasicBaseRfidtemplateParameterDAC(anyMap(), eq(request)))
                .thenReturn(list);
        when(basicBaseRfidtemplateParameterService.getBasicBaseRfidtemplateParameterDACCount(anyMap(), eq(request)))
                .thenReturn(1L);

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidtemplateParameterController.daclist(request, params, tenantId,
                jwt);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
    }

    @Test
    void testSave() {
        // Given
        BasicBaseRfidtemplateParameter bean = new BasicBaseRfidtemplateParameter();
        when(basicBaseRfidtemplateParameterService.createBasicBaseRfidtemplateParameterWithOrg(eq(bean), eq(request)))
                .thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidtemplateParameterController.save(bean, request, response);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseRfidtemplateParameter bean = new BasicBaseRfidtemplateParameter();
        when(basicBaseRfidtemplateParameterService.updateBasicBaseRfidtemplateParameter(eq(bean))).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidtemplateParameterController.update(bean, request, response);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(basicBaseRfidtemplateParameterService.deleteById(eq(Long.parseLong(id)))).thenReturn(true);

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidtemplateParameterController.del(id, request, response);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseRfidtemplateParameter bean = new BasicBaseRfidtemplateParameter();
        when(basicBaseRfidtemplateParameterService.selectById(eq(id))).thenReturn(bean);

        // When
        AjaxResult result = (AjaxResult) basicBaseRfidtemplateParameterController.getOne(id, request, response);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(bean);
    }
}
