package com.matariky.commonservice.base.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBasePcVersion;
import com.matariky.commonservice.base.service.BasicBasePcVersionService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import java.util.Collections;

@SpringBootTest
public class BasicBasePcVersionControllerTest {

    @InjectMocks
    private BasicBasePcVersionController basicBasePcVersionController;

    @Mock
    private BasicBasePcVersionService basicBasePcVersionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBasePcVersionQueryVO vo = new BasicBasePcVersionQueryVO();
        PageInfo<BasicBasePcVersionListVO> pageInfo = new PageInfo<>(Collections.emptyList());
        when(basicBasePcVersionService.getBasicBasePcversionAll(vo)).thenReturn(pageInfo.getList());

        // When
        AjaxResult result = basicBasePcVersionController.list(vo);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(pageInfo, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSave() {
        // Given
        BasicBasePcVersionAddVO addVO = new BasicBasePcVersionAddVO();

        // When
        AjaxResult result = basicBasePcVersionController.save(addVO);

        // Then
        verify(basicBasePcVersionService, times(1)).createBasicBasePcversionWithOrg(addVO);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        BasicBasePcVersionUpdateVO updateVO = new BasicBasePcVersionUpdateVO();

        // When
        AjaxResult result = basicBasePcVersionController.update(updateVO);

        // Then
        verify(basicBasePcVersionService, times(1)).updateBasicBasePcversion(updateVO);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDelete() {
        // Given
        Long id = 1L;

        // When
        AjaxResult result = basicBasePcVersionController.del(id);

        // Then
        verify(basicBasePcVersionService, times(1)).delBasicBasePcversionById(id);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testBatchDelete() {
        // Given
        BasicBasePcVersionDelVO delVO = new BasicBasePcVersionDelVO();
        delVO.setIds(Collections.singletonList(1L));

        // When
        AjaxResult result = basicBasePcVersionController.del(delVO);

        // Then
        verify(basicBasePcVersionService, times(1)).delBasicBasePcversionByIds(delVO.getIds());
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBasePcVersion pcVersion = new BasicBasePcVersion();
        when(basicBasePcVersionService.selectById(id)).thenReturn(pcVersion);

        // When
        AjaxResult result = basicBasePcVersionController.getOne(id);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(pcVersion, result.get(AjaxResult.DATA_TAG));
    }
}
