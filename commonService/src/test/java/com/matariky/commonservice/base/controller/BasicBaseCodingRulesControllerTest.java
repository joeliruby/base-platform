package com.matariky.commonservice.base.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.bean.BasicBaseCodingRules;
import com.matariky.commonservice.base.service.BasicBaseCodingRulesService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import java.util.Collections;

@SpringBootTest
public class BasicBaseCodingRulesControllerTest {

    @InjectMocks
    private BasicBaseCodingRulesController basicbasecodingrulescontroller;

    @Mock
    private BasicBaseCodingRulesService basicBaseCodingrulesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseCodingRulesListVO vo = new BasicBaseCodingRulesListVO();
        when(basicBaseCodingrulesService.getBasicBaseCodingrulesAll(vo)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = basicbasecodingrulescontroller.list(vo);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertNotNull(result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSave() {
        // Given
        BasicBaseCodingRulesAddVO addVO = new BasicBaseCodingRulesAddVO();

        // When
        AjaxResult result = basicbasecodingrulescontroller.save(addVO);

        // Then
        verify(basicBaseCodingrulesService, times(1)).createBasicBaseCodingrulesWithOrg(addVO);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetCodingrulesCode() {
        // Given
        String expectedCode = "CODE123";
        when(basicBaseCodingrulesService.getCodingrulesCode()).thenReturn(expectedCode);

        // When
        AjaxResult result = basicbasecodingrulescontroller.getCodingrulesCode();

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(expectedCode, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseCodingRulesUpdateVO updateVO = new BasicBaseCodingRulesUpdateVO();

        // When
        AjaxResult result = basicbasecodingrulescontroller.update(updateVO);

        // Then
        verify(basicBaseCodingrulesService, times(1)).updateBasicBaseCodingrules(updateVO);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdateStatus() {
        // Given
        BasicBaseCodingRulesUpdateStatusVO vo = new BasicBaseCodingRulesUpdateStatusVO();

        // When
        AjaxResult result = basicbasecodingrulescontroller.update(vo);

        // Then
        verify(basicBaseCodingrulesService, times(1)).updateBasicBaseCodingrulesStatus(vo);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDel() {
        // Given
        Long id = 1L;

        // When
        AjaxResult result = basicbasecodingrulescontroller.del(id);

        // Then
        verify(basicBaseCodingrulesService, times(1)).delBasicBaseCodingrulesById(id);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseCodingRules expectedObj = new BasicBaseCodingRules();
        when(basicBaseCodingrulesService.selectById(id)).thenReturn(expectedObj);

        // When
        AjaxResult result = basicbasecodingrulescontroller.getOne(id);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(expectedObj, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testListOptions() {
        // Given
        when(basicBaseCodingrulesService.optionList()).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = basicbasecodingrulescontroller.list();

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertNotNull(result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSaveWithInvalidData() {
        // Given
        BasicBaseCodingRulesAddVO addVO = new BasicBaseCodingRulesAddVO();
        doThrow(new IllegalArgumentException("Invalid data")).when(basicBaseCodingrulesService)
                .createBasicBaseCodingrulesWithOrg(addVO);

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            basicbasecodingrulescontroller.save(addVO);
        });

        // Then
        assertEquals("Invalid data", exception.getMessage());
    }

    @Test
    void testUpdateWithInvalidData() {
        // Given
        BasicBaseCodingRulesUpdateVO updateVO = new BasicBaseCodingRulesUpdateVO();
        doThrow(new IllegalArgumentException("Invalid data")).when(basicBaseCodingrulesService)
                .updateBasicBaseCodingrules(updateVO);

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            basicbasecodingrulescontroller.update(updateVO);
        });

        // Then
        assertEquals("Invalid data", exception.getMessage());
    }

    @Test
    void testUpdateStatusWithInvalidData() {
        // Given
        BasicBaseCodingRulesUpdateStatusVO vo = new BasicBaseCodingRulesUpdateStatusVO();
        doThrow(new IllegalArgumentException("Invalid data")).when(basicBaseCodingrulesService)
                .updateBasicBaseCodingrulesStatus(vo);

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            basicbasecodingrulescontroller.update(vo);
        });

        // Then
        assertEquals("Invalid data", exception.getMessage());
    }

    @Test
    void testDelWithInvalidId() {
        // Given
        Long id = -1L;
        doThrow(new IllegalArgumentException("Invalid ID")).when(basicBaseCodingrulesService)
                .delBasicBaseCodingrulesById(id);

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            basicbasecodingrulescontroller.del(id);
        });

        // Then
        assertEquals("Invalid ID", exception.getMessage());
    }

    @Test
    void testGetOneWithInvalidId() {
        // Given
        Long id = -1L;
        when(basicBaseCodingrulesService.selectById(id)).thenThrow(new IllegalArgumentException("Invalid ID"));

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            basicbasecodingrulescontroller.getOne(id);
        });

        // Then
        assertEquals("Invalid ID", exception.getMessage());
    }
}
