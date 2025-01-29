package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseGoods;
import com.matariky.commonservice.base.vo.BasicBaseGoodsListVO;
import com.matariky.commonservice.base.vo.GoodsOptionInfo;
import com.matariky.model.QueryDataIsolation;
import org.mockito.Mock;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class BasicBaseGoodsMapperTest {

    @InjectMocks
    private BasicBaseGoodsMapper basicbasegoodsmapper;

    @Mock
    private BasicBaseGoodsMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSelectFieldExtend() {
        // Given
        Long tenantId = 1L;
        List<String> expectedFields = List.of("field1", "field2");
        when(mockMapper.selectFieldExtend(tenantId)).thenReturn(expectedFields);

        // When
        List<String> actualFields = basicbasegoodsmapper.selectFieldExtend(tenantId);

        // Then
        assertEquals(expectedFields, actualFields);
    }

    @Test
    void testGetBasicBaseGoodsAll() {
        // Given
        BasicBaseGoodsListVO vo = new BasicBaseGoodsListVO();
        List<HashMap> expectedGoods = List.of(new HashMap());
        when(mockMapper.getBasicBaseGoodsAll(vo)).thenReturn(expectedGoods);

        // When
        List<HashMap> actualGoods = basicbasegoodsmapper.getBasicBaseGoodsAll(vo);

        // Then
        assertEquals(expectedGoods, actualGoods);
    }

    @Test
    void testCreateBasicBaseGoods() {
        // Given
        BasicBaseGoods bean = new BasicBaseGoods();
        when(mockMapper.createBasicBaseGoods(bean)).thenReturn(1);

        // When
        int result = basicbasegoodsmapper.createBasicBaseGoods(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseGoodsById() {
        // Given
        Long id = 1L;
        when(mockMapper.delBasicBaseGoodsById(id)).thenReturn(1);

        // When
        int result = basicbasegoodsmapper.delBasicBaseGoodsById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetOptionList() {
        // Given
        QueryDataIsolation queryDataIsolation = new QueryDataIsolation();
        List<GoodsOptionInfo> expectedOptions = List.of(new GoodsOptionInfo());
        when(mockMapper.getOptionList(queryDataIsolation)).thenReturn(expectedOptions);

        // When
        List<GoodsOptionInfo> actualOptions = basicbasegoodsmapper.getOptionList(queryDataIsolation);

        // Then
        assertEquals(expectedOptions, actualOptions);
    }

    @Test
    void testGetGoodsCountFromRfidfactory() {
        // Given
        Long goodsId = 1L;
        Integer expectedCount = 10;
        when(mockMapper.getGoodsCountFromRfidfactory(goodsId)).thenReturn(expectedCount);

        // When
        Integer actualCount = basicbasegoodsmapper.getGoodsCountFromRfidfactory(goodsId);

        // Then
        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testGetGoodsCountFromRfidPrint() {
        // Given
        Long goodsId = 1L;
        Integer expectedCount = 5;
        when(mockMapper.getGoodsCountFromRfidPrint(goodsId)).thenReturn(expectedCount);

        // When
        Integer actualCount = basicbasegoodsmapper.getGoodsCountFromRfidPrint(goodsId);

        // Then
        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testGetBasicBaseGoodsById() {
        // Given
        Long id = 1L;
        BasicBaseGoods expectedGoods = new BasicBaseGoods();
        when(mockMapper.getBasicBaseGoodsById(id)).thenReturn(expectedGoods);

        // When
        BasicBaseGoods actualGoods = basicbasegoodsmapper.getBasicBaseGoodsById(id);

        // Then
        assertEquals(expectedGoods, actualGoods);
    }
}
