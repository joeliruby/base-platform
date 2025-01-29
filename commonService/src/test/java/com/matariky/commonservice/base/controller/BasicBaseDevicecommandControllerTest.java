package com.matariky.commonservice.base.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseDevicecommand;
import com.matariky.commonservice.base.service.BasicBaseDevicecommandService;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseDevicecommandControllerTest {

    @InjectMocks
    private BasicBaseDevicecommandController basicbasedevicecommandcontroller;

    @Mock
    private BasicBaseDevicecommandService basicBaseDevicecommandService;

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
        BasicBaseDevicecommand bean = new BasicBaseDevicecommand();
        List<BasicBaseDevicecommand> expectedList = Collections.singletonList(bean);
        when(basicBaseDevicecommandService.getBasicBaseDevicecommandAll(bean)).thenReturn(expectedList);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecommandcontroller.list(bean);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(expectedList, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSave() {
        // Given
        BasicBaseDevicecommand bean = new BasicBaseDevicecommand();
        when(basicBaseDevicecommandService.createBasicBaseDevicecommandWithOrg(bean, request)).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecommandcontroller.save(bean, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertNull(result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseDevicecommand bean = new BasicBaseDevicecommand();
        when(basicBaseDevicecommandService.updateBasicBaseDevicecommand(bean)).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecommandcontroller.update(bean, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertNull(result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(basicBaseDevicecommandService.deleteById(Long.parseLong(id))).thenReturn(true);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecommandcontroller.del(id, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertNull(result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseDevicecommand expectedBean = new BasicBaseDevicecommand();
        when(basicBaseDevicecommandService.selectById(id)).thenReturn(expectedBean);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecommandcontroller.getOne(id, request, response);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(expectedBean, result.get(AjaxResult.DATA_TAG));
    }
}
