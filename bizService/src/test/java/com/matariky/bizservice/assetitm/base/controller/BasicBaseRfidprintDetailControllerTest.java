package com.matariky.bizservice.assetitm.base.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintDetail;
import com.matariky.bizservice.assetitm.base.service.BasicBaseRfidprintDetailService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class BasicBaseRfidprintDetailControllerTest {

    @InjectMocks
    private BasicBaseRfidprintDetailController basicBaseRfidprintDetailController;

    @Mock
    private BasicBaseRfidprintDetailService basicBaseRfidprintDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateSuccess() {
        // Given
        BasicBaseRfidprintDetail bean = new BasicBaseRfidprintDetail();
        when(basicBaseRfidprintDetailService.updateBasicBaseRfidprintDetail(bean)).thenReturn(1);

        // When
        AjaxResult result = basicBaseRfidprintDetailController.update(bean, null, null);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testUpdateFailure() {
        // Given
        BasicBaseRfidprintDetail bean = new BasicBaseRfidprintDetail();
        when(basicBaseRfidprintDetailService.updateBasicBaseRfidprintDetail(bean)).thenReturn(0);

        // When
        AjaxResult result = basicBaseRfidprintDetailController.update(bean, null, null);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testGetAll() {
        // Given
        Long id = 1L;
        BasicBaseRfidprintDetail detail = new BasicBaseRfidprintDetail();
        when(basicBaseRfidprintDetailService.getBasicBaseRfidprintDetailAllById(id))
                .thenReturn(Collections.singletonList(detail));

        // When
        AjaxResult result = basicBaseRfidprintDetailController.getAll(id, null, null);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testGetUnprintAll() {
        // Given
        Long id = 1L;
        BasicBaseRfidprintDetail detail = new BasicBaseRfidprintDetail();
        when(basicBaseRfidprintDetailService.getBasicBaseRfidprintDetailUnprintById(id))
                .thenReturn(Collections.singletonList(detail));

        // When
        AjaxResult result = basicBaseRfidprintDetailController.getUnprintAll(id, null, null);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    // Add more test methods for other scenarios if needed
}
