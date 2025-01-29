package com.matariky.commonservice.commondict.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class CommonDictTypeControllerTest {

    @InjectMocks
    private CommonDictTypeController commondicttypecontroller;

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
        Map<String, Object> params = new HashMap<>();
        params.put("index", "1");
        params.put("perPage", "20");
        String tenantId = "tenant1";
        Page<CommonDictType> page = mock(Page.class);
        when(commonDictTypeService.getCommonDictTypeAll(params)).thenReturn(page);
        when(page.getResult()).thenReturn(new ArrayList<>());

        // When
        AjaxResult result = (AjaxResult) commondicttypecontroller.list(request, params, tenantId);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.MSG_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertTrue(result.get(AjaxResult.DATA_TAG) instanceof PageInfo);
    }

    @Test
    void testEdit() {
        // Given
        String id = "1";
        CommonDictType commonDictType = new CommonDictType();
        when(commonDictTypeService.getCommonDictTypeById(id)).thenReturn(commonDictType);

        // When
        AjaxResult result = (AjaxResult) commondicttypecontroller.edit(request, id);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(commonDictType, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSave() {
        // Given
        CommonDictType commonDictType = new CommonDictType();
        when(commonDictTypeService.createCommonDictType(commonDictType)).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) commondicttypecontroller.save(commonDictType, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(commonDictType, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        CommonDictType commonDictType = new CommonDictType();
        when(commonDictTypeService.updateCommonDictType(commonDictType)).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) commondicttypecontroller.update(commonDictType, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(commonDictType, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(commonDictTypeService.updateDeleteTimeById(new String[] { id })).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) commondicttypecontroller.del(id, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertNull(result.get(AjaxResult.DATA_TAG));
    }
}
