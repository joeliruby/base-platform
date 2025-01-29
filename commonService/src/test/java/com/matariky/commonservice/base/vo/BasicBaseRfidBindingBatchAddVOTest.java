package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
public class BasicBaseRfidBindingBatchAddVOTest {

    @InjectMocks
    private BasicBaseRfidBindingBatchAddVO basicBaseRfidBindingBatchAddVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGoodsIdNotNull() {
        // Given
        basicBaseRfidBindingBatchAddVO.setGoodsId(1L);

        // When
        Long goodsId = basicBaseRfidBindingBatchAddVO.getGoodsId();

        // Then
        assertNotNull(goodsId);
        assertEquals(1L, goodsId);
    }

    @Test
    void testDeviceIdNotNull() {
        // Given
        basicBaseRfidBindingBatchAddVO.setDeviceId(2L);

        // When
        Long deviceId = basicBaseRfidBindingBatchAddVO.getDeviceId();

        // Then
        assertNotNull(deviceId);
        assertEquals(2L, deviceId);
    }

    @Test
    void testEpcAndTidListNotEmpty() {
        // Given
        EPCAndTIDVO epcAndTIDVO = new EPCAndTIDVO();
        basicBaseRfidBindingBatchAddVO.setEpcAndTidList(Collections.singletonList(epcAndTIDVO));

        // When
        List<EPCAndTIDVO> epcAndTidList = basicBaseRfidBindingBatchAddVO.getEpcAndTidList();

        // Then
        assertNotNull(epcAndTidList);
        assertFalse(epcAndTidList.isEmpty());
    }

    @Test
    void testEpcAndTidListValid() {
        // Given
        EPCAndTIDVO epcAndTIDVO = new EPCAndTIDVO();
        basicBaseRfidBindingBatchAddVO.setEpcAndTidList(Collections.singletonList(epcAndTIDVO));

        // When
        List<EPCAndTIDVO> epcAndTidList = basicBaseRfidBindingBatchAddVO.getEpcAndTidList();

        // Then
        assertNotNull(epcAndTidList);
        assertTrue(epcAndTidList.stream().allMatch(epcAndTIDVO1 -> epcAndTIDVO1 instanceof EPCAndTIDVO));
    }
}
