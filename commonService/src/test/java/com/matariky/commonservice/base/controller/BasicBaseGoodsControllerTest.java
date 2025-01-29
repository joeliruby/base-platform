package com.matariky.commonservice.base.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.service.BasicBaseGoodsService;
import com.matariky.commonservice.base.vo.BasicBaseGoodsListVO;
import com.matariky.commonservice.base.vo.GoodsOptionInfo;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BasicBaseGoodsControllerTest {

    @InjectMocks
    private BasicBaseGoodsController basicBaseGoodsController;

    @Mock
    private BasicBaseGoodsService basicBaseGoodsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseGoodsListVO vo = new BasicBaseGoodsListVO();
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
        when(basicBaseGoodsService.getBasicBaseGoodsAll(vo)).thenReturn(pageInfo);

        // When
        AjaxResult result = basicBaseGoodsController.list(vo);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.MSG_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(pageInfo, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSave() {
        // Given
        HashMap<String, Object> addVO = new HashMap<>();

        // When
        AjaxResult result = basicBaseGoodsController.save(addVO);

        // Then
        verify(basicBaseGoodsService, times(1)).createBasicBaseGoodsWithOrg(addVO);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        HashMap<String, Object> updateVO = new HashMap<>();

        // When
        AjaxResult result = basicBaseGoodsController.update(updateVO);

        // Then
        verify(basicBaseGoodsService, times(1)).updateBasicBaseGoods(updateVO);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDel() {
        // Given
        Long id = 1L;
        Long extendId = 2L;

        // When
        AjaxResult result = basicBaseGoodsController.del(id, extendId);

        // Then
        verify(basicBaseGoodsService, times(1)).delBasicBaseGoodsById(id, extendId);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        Long extendId = 2L;
        Map<String, Object> map = new HashMap<>();
        when(basicBaseGoodsService.getBasicBaseGoodsById(id, extendId)).thenReturn(map);

        // When
        AjaxResult result = basicBaseGoodsController.getOne(id, extendId);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(map, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testOptionList() {
        // Given
        List<GoodsOptionInfo> list = List.of(new GoodsOptionInfo());
        when(basicBaseGoodsService.optionList()).thenReturn(list);

        // When
        AjaxResult result = basicBaseGoodsController.list();

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(list, result.get(AjaxResult.DATA_TAG));
    }
}
