package com.matariky.commonservice.base.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import com.matariky.commonservice.base.service.BasicBaseGoodsBatchService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import java.util.Collections;

@SpringBootTest
public class BasicBaseGoodsBatchControllerTest {

    @InjectMocks
    private BasicBaseGoodsBatchController basicBaseGoodsBatchController;

    @Mock
    private BasicBaseGoodsBatchService basicBaseGoodsBatchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseGoodsBatchListVO vo = new BasicBaseGoodsBatchListVO();
        when(basicBaseGoodsBatchService.getBasicBaseGoodsBatchAll(vo)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = basicBaseGoodsBatchController.list(vo);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
    }

    @Test
    void testSave() {
        // Given
        BasicBaseGoodsBatchAddVO vo = new BasicBaseGoodsBatchAddVO();

        // When
        AjaxResult result = basicBaseGoodsBatchController.save(vo);

        // Then
        verify(basicBaseGoodsBatchService, times(1)).createBasicBaseGoodsBatch(vo);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseGoodsBatchUpdateVO updateVO = new BasicBaseGoodsBatchUpdateVO();

        // When
        AjaxResult result = basicBaseGoodsBatchController.update(updateVO);

        // Then
        verify(basicBaseGoodsBatchService, times(1)).updateBasicBaseGoodsBatch(updateVO);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testDelete() {
        // Given
        Long id = 1L;

        // When
        AjaxResult result = basicBaseGoodsBatchController.del(id);

        // Then
        verify(basicBaseGoodsBatchService, times(1)).delBasicBaseGoodsBatchById(id);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseGoodsBatchResVO resVO = new BasicBaseGoodsBatchResVO();
        when(basicBaseGoodsBatchService.getBasicBaseGoodsBatchById(id)).thenReturn(resVO);

        // When
        AjaxResult result = basicBaseGoodsBatchController.getOne(id);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(resVO);
    }

    @Test
    void testBatchBind() {
        // Given
        BatchGoodsBindVO vo = new BatchGoodsBindVO();

        // When
        AjaxResult result = basicBaseGoodsBatchController.batchBind(vo);

        // Then
        verify(basicBaseGoodsBatchService, times(1)).batchGoodsBind(vo);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testGoodsBatchInfo() {
        // Given
        BatchInfoVO vo = new BatchInfoVO();
        when(basicBaseGoodsBatchService.goodsBatchInfo(vo)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = basicBaseGoodsBatchController.goodsBatchInfo(vo);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
    }
}
