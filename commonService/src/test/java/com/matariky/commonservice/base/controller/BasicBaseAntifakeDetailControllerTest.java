package com.matariky.commonservice.base.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.matariky.commonservice.base.bean.BasicBaseAntifakeDetail;
import com.matariky.commonservice.base.service.BasicBaseAntifakeDetailService;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class BasicBaseAntifakeDetailControllerTest {

    @InjectMocks
    private BasicBaseAntifakeDetailController basicBaseAntifakeDetailController;

    @Mock
    private BasicBaseAntifakeDetailService basicBaseAntifakeDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseAntifakeDetail bean = new BasicBaseAntifakeDetail();
        int pageIndex = 1;
        int perPage = 10;
        when(basicBaseAntifakeDetailService.getBasicBaseAntifakeDetailAll(bean, pageIndex, perPage))
                .thenReturn(new Page<>());

        // When
        AjaxResult result = basicBaseAntifakeDetailController.list(bean, pageIndex, perPage);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        verify(basicBaseAntifakeDetailService, times(1)).getBasicBaseAntifakeDetailAll(bean, pageIndex, perPage);
    }

    @Test
    void testSave() {
        // Given
        BasicBaseAntifakeDetail bean = new BasicBaseAntifakeDetail();

        // When
        AjaxResult result = basicBaseAntifakeDetailController.save(bean);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        verify(basicBaseAntifakeDetailService, times(1)).createBasicBaseAntifakeDetailWithOrg(bean);
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseAntifakeDetail bean = new BasicBaseAntifakeDetail();

        // When
        AjaxResult result = basicBaseAntifakeDetailController.update(bean);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        verify(basicBaseAntifakeDetailService, times(1)).updateBasicBaseAntifakeDetail(bean);
    }

    @Test
    void testDelete() {
        // Given
        Long id = 1L;

        // When
        AjaxResult result = basicBaseAntifakeDetailController.del(id);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        verify(basicBaseAntifakeDetailService, times(1)).delBasicBaseAntifakeDetailById(id);
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseAntifakeDetail detail = new BasicBaseAntifakeDetail();
        when(basicBaseAntifakeDetailService.getBasicBaseAntifakeDetailById(id)).thenReturn(detail);

        // When
        AjaxResult result = basicBaseAntifakeDetailController.getOne(id);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(detail, result.get(AjaxResult.DATA_TAG));
        verify(basicBaseAntifakeDetailService, times(1)).getBasicBaseAntifakeDetailById(id);
    }
}
