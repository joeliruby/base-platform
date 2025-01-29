package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;

@SpringBootTest
public class BasicBaseGoodsBatchResVOTest {

    @InjectMocks
    private BasicBaseGoodsBatchResVO basicbasegoodsbatchresvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetId() {
        // Given
        Long expectedId = 1L;

        // When
        basicbasegoodsbatchresvo.setId(expectedId);

        // Then
        assertThat(basicbasegoodsbatchresvo.getId()).isEqualTo(expectedId);
    }

    @Test
    void testSetAndGetGoodsId() {
        // Given
        Long expectedGoodsId = 2L;

        // When
        basicbasegoodsbatchresvo.setGoodsId(expectedGoodsId);

        // Then
        assertThat(basicbasegoodsbatchresvo.getGoodsId()).isEqualTo(expectedGoodsId);
    }

    @Test
    void testSetAndGetBatchCode() {
        // Given
        String expectedBatchCode = "BATCH123";

        // When
        basicbasegoodsbatchresvo.setBatchCode(expectedBatchCode);

        // Then
        assertThat(basicbasegoodsbatchresvo.getBatchCode()).isEqualTo(expectedBatchCode);
    }

    @Test
    void testSetAndGetAmount() {
        // Given
        BigDecimal expectedAmount = new BigDecimal("100.00");

        // When
        basicbasegoodsbatchresvo.setAmount(expectedAmount);

        // Then
        assertThat(basicbasegoodsbatchresvo.getAmount()).isEqualTo(expectedAmount);
    }

    @Test
    void testSetAndGetRemark() {
        // Given
        String expectedRemark = "Test Remark";

        // When
        basicbasegoodsbatchresvo.setRemark(expectedRemark);

        // Then
        assertThat(basicbasegoodsbatchresvo.getRemark()).isEqualTo(expectedRemark);
    }

    @Test
    void testSetAndGetCreateTime() {
        // Given
        Long expectedCreateTime = 1627849200000L;

        // When
        basicbasegoodsbatchresvo.setCreateTime(expectedCreateTime);

        // Then
        assertThat(basicbasegoodsbatchresvo.getCreateTime()).isEqualTo(expectedCreateTime);
    }

    @Test
    void testSetAndGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 1627849200000L;

        // When
        basicbasegoodsbatchresvo.setUpdateTime(expectedUpdateTime);

        // Then
        assertThat(basicbasegoodsbatchresvo.getUpdateTime()).isEqualTo(expectedUpdateTime);
    }

    // Add more test methods for other fields in BasicBaseGoodsBatchResVO
}
