package com.matariky.commonservice.base.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.matariky.commonservice.base.bean.BasicBaseFormExtend;
import com.matariky.commonservice.base.service.BasicBaseFormExtendService;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class BasicBaseFormExtendControllerTest {

    @InjectMocks
    private BasicBaseFormExtendController basicBaseFormExtendController;

    @Mock
    private BasicBaseFormExtendService basicBaseFormExtendService;

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
        BasicBaseFormExtend bean = new BasicBaseFormExtend();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        List<BasicBaseFormExtend> list = Collections.singletonList(bean);
        when(basicBaseFormExtendService.getBasicBaseFormExtendAll(bean)).thenReturn(list);

        // When
        AjaxResult result = (AjaxResult) basicBaseFormExtendController.list(request, bean, tenantId, pageIndex, perPage,
                jwt);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testDaclist() {
        // Given
        String tenantId = "tenant1";
        String jwt = "jwtToken";
        Map<String, Object> params = mock(Map.class);
        when(request.getHeader("id")).thenReturn("id123");
        CommonDictType commonDictType = new CommonDictType();
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(commonDictType);
        CommonDict dict = new CommonDict();
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(dict);
        List<BasicBaseFormExtend> list = Collections.singletonList(new BasicBaseFormExtend());
        when(basicBaseFormExtendService.getBasicBaseFormExtendDAC(anyMap(), any(HttpServletRequest.class)))
                .thenReturn(list);
        when(basicBaseFormExtendService.getBasicBaseFormExtendDACCount(anyMap(), any(HttpServletRequest.class)))
                .thenReturn(1L);

        // When
        AjaxResult result = (AjaxResult) basicBaseFormExtendController.daclist(request, params, tenantId, jwt);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testSave() {
        // Given
        BasicBaseFormExtend bean = new BasicBaseFormExtend();
        when(basicBaseFormExtendService.createBasicBaseFormExtendWithOrg(any(BasicBaseFormExtend.class),
                any(HttpServletRequest.class))).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicBaseFormExtendController.save(bean, request, response);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseFormExtend bean = new BasicBaseFormExtend();
        when(basicBaseFormExtendService.updateBasicBaseFormExtend(any(BasicBaseFormExtend.class))).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicBaseFormExtendController.update(bean, request, response);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(basicBaseFormExtendService.deleteById(anyLong())).thenReturn(true);

        // When
        AjaxResult result = (AjaxResult) basicBaseFormExtendController.del(id, request, response);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseFormExtend bean = new BasicBaseFormExtend();
        when(basicBaseFormExtendService.selectById(anyLong())).thenReturn(bean);

        // When
        AjaxResult result = (AjaxResult) basicBaseFormExtendController.getOne(id, request, response);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }
}
