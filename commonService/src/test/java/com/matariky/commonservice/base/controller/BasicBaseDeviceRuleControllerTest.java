package com.matariky.commonservice.base.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRuleDetail;
import com.matariky.commonservice.base.service.BasicBaseDeviceRuleDetailService;
import com.matariky.commonservice.base.service.BasicBaseDeviceRuleService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class BasicBaseDeviceRuleControllerTest {

    @InjectMocks
    private BasicBaseDeviceRuleController basicbasedevicerulecontroller;

    @Mock
    private BasicBaseDeviceRuleService basicBaseDeviceruleService;

    @Mock
    private BasicBaseDeviceRuleDetailService basicBaseDeviceruleDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseDeviceRuleDetailListVO vo = new BasicBaseDeviceRuleDetailListVO();
        String jwt = "test-jwt";
        BasicBaseDeviceRuleDetailInfo data = new BasicBaseDeviceRuleDetailInfo();
        when(basicBaseDeviceruleDetailService.getBasicBaseDeviceruleDetailAll(vo, jwt)).thenReturn(data);

        // When
        AjaxResult result = basicbasedevicerulecontroller.list(vo, jwt);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(data, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSave() {
        // Given
        BasicBaseDeviceRuleAddVO addVO = new BasicBaseDeviceRuleAddVO();
        String jwt = "test-jwt";
        Long ruleId = 1L;
        when(basicBaseDeviceruleService.createBasicBaseDeviceruleWithOrg(addVO, jwt)).thenReturn(ruleId);

        // When
        AjaxResult result = basicbasedevicerulecontroller.save(addVO, jwt);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(ruleId, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseDeviceRuleUpdateVO updateVO = new BasicBaseDeviceRuleUpdateVO();
        String jwt = "test-jwt";

        // When
        AjaxResult result = basicbasedevicerulecontroller.update(updateVO, jwt);

        // Then
        verify(basicBaseDeviceruleService, times(1)).updateBasicBaseDevicerule(updateVO, jwt);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testSaveDetail() {
        // Given
        BasicBaseDeviceRuleDetailAddVO addVO = new BasicBaseDeviceRuleDetailAddVO();
        String jwt = "test-jwt";

        // When
        AjaxResult result = basicbasedevicerulecontroller.saveDetail(addVO, jwt);

        // Then
        verify(basicBaseDeviceruleService, times(1)).createBasicBaseDeviceruleDetailWithOrg(addVO, jwt);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdateDetail() {
        // Given
        BasicBaseDeviceRuleDetailUpdateByIdVO updateVO = new BasicBaseDeviceRuleDetailUpdateByIdVO();
        String jwt = "test-jwt";

        // When
        AjaxResult result = basicbasedevicerulecontroller.update(updateVO, jwt);

        // Then
        verify(basicBaseDeviceruleDetailService, times(1)).updateBasicBaseDeviceruleDetail(updateVO, jwt);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDeleteDetail() {
        // Given
        Long id = 1L;

        // When
        AjaxResult result = basicbasedevicerulecontroller.del(id);

        // Then
        verify(basicBaseDeviceruleDetailService, times(1)).delBasicBaseDeviceruleDetailById(id);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseDeviceRuleDetail info = new BasicBaseDeviceRuleDetail();
        when(basicBaseDeviceruleDetailService.selectById(id)).thenReturn(info);

        // When
        AjaxResult result = basicbasedevicerulecontroller.getOne(id);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(info, result.get(AjaxResult.DATA_TAG));
    }
}
