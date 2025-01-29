package com.matariky.commonservice.base.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.service.BasicBaseDeviceTypeService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import java.util.Collections;

@SpringBootTest
public class BasicBaseDeviceTypeControllerTest {

    @InjectMocks
    private BasicBaseDeviceTypeController basicbasedevicetypecontroller;

    @Mock
    private BasicBaseDeviceTypeService basicBaseDevicetypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseDeviceTypeListVO vo = new BasicBaseDeviceTypeListVO();
        when(basicBaseDevicetypeService.getBasicBaseDevicetypeAll(vo)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = basicbasedevicetypecontroller.list(vo);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertNotNull(result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSave() {
        // Given
        BasicBaseDeviceTypeAddVO addVO = new BasicBaseDeviceTypeAddVO();
        String jwt = "test-jwt";

        // When
        AjaxResult result = basicbasedevicetypecontroller.save(addVO, jwt);

        // Then
        verify(basicBaseDevicetypeService, times(1)).createBasicBaseDevicetypeWithOrg(addVO, jwt);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseDeviceTypeUpdateVO updateVO = new BasicBaseDeviceTypeUpdateVO();
        String jwt = "test-jwt";

        // When
        AjaxResult result = basicbasedevicetypecontroller.update(updateVO, jwt);

        // Then
        verify(basicBaseDevicetypeService, times(1)).updateBasicBaseDevicetype(updateVO, jwt);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdateStatus() {
        // Given
        BasicBaseDevicetypeStatusVO vo = new BasicBaseDevicetypeStatusVO();

        // When
        AjaxResult result = basicbasedevicetypecontroller.update(vo);

        // Then
        verify(basicBaseDevicetypeService, times(1)).updateBasicBaseDevicetypeStatus(vo);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDelete() {
        // Given
        Long id = 1L;

        // When
        AjaxResult result = basicbasedevicetypecontroller.del(id);

        // Then
        verify(basicBaseDevicetypeService, times(1)).delBasicBaseDevicetypeById(id);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseDeviceTypeInfo type = new BasicBaseDeviceTypeInfo();
        when(basicBaseDevicetypeService.getBasicBaseDevicetypeById(id)).thenReturn(type);

        // When
        AjaxResult result = basicbasedevicetypecontroller.getOne(id);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(type, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testOptionList() {
        // Given
        when(basicBaseDevicetypeService.getOptionList()).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = basicbasedevicetypecontroller.optionList();

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertNotNull(result.get(AjaxResult.DATA_TAG));
    }
}
