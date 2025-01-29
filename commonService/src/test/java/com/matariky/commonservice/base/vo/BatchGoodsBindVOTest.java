package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BatchGoodsBindVOTest {

    @InjectMocks
    private BatchGoodsBindVO batchgoodsbindvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGoodsIdNotNull() {
        // Given
        batchgoodsbindvo.setGoodsId(1L);

        // When
        Long goodsId = batchgoodsbindvo.getGoodsId();

        // Then
        assertNotNull(goodsId);
        assertEquals(1L, goodsId);
    }

    @Test
    void testListInitialization() {
        // Given
        batchgoodsbindvo.setList(Collections.emptyList());

        // When
        List<BasicBaseRfidBindingBatchVO> list = batchgoodsbindvo.getList();

        // Then
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    void testAddItemToList() {
        // Given
        BasicBaseRfidBindingBatchVO item = new BasicBaseRfidBindingBatchVO();
        batchgoodsbindvo.setList(Collections.singletonList(item));

        // When
        List<BasicBaseRfidBindingBatchVO> list = batchgoodsbindvo.getList();

        // Then
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(item, list.get(0));
    }

    // Add more test methods for other methods in BatchGoodsBindVO
}
