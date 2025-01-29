package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

@SpringBootTest
public class BasicBaseGoodsBatchUpdateVOTest {

    @InjectMocks
    private BasicBaseGoodsBatchUpdateVO basicbasegoodsbatchupdatevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetId() {
        // Given
        Long id = 1L;

        // When
        basicbasegoodsbatchupdatevo.setId(id);

        // Then
        assertEquals(id, basicbasegoodsbatchupdatevo.getId());
    }

    @Test
    void testSetAndGetGoodsId() {
        // Given
        Long goodsId = 2L;

        // When
        basicbasegoodsbatchupdatevo.setGoodsId(goodsId);

        // Then
        assertEquals(goodsId, basicbasegoodsbatchupdatevo.getGoodsId());
    }

    @Test
    void testSetAndGetBatchCode() {
        // Given
        String batchCode = "BATCH123";

        // When
        basicbasegoodsbatchupdatevo.setBatchCode(batchCode);

        // Then
        assertEquals(batchCode, basicbasegoodsbatchupdatevo.getBatchCode());
    }

    @Test
    void testSetAndGetProductionDate() {
        // Given
        Long productionDate = 1622520000000L;

        // When
        basicbasegoodsbatchupdatevo.setProductionDate(productionDate);

        // Then
        assertEquals(productionDate, basicbasegoodsbatchupdatevo.getProductionDate());
    }

    @Test
    void testSetAndGetValidityDate() {
        // Given
        Long validityDate = 1622520000000L;

        // When
        basicbasegoodsbatchupdatevo.setValidityDate(validityDate);

        // Then
        assertEquals(validityDate, basicbasegoodsbatchupdatevo.getValidityDate());
    }

    @Test
    void testSetAndGetSupplier() {
        // Given
        String supplier = "SupplierName";

        // When
        basicbasegoodsbatchupdatevo.setSupplier(supplier);

        // Then
        assertEquals(supplier, basicbasegoodsbatchupdatevo.getSupplier());
    }

    @Test
    void testSetAndGetAmount() {
        // Given
        BigDecimal amount = new BigDecimal("100.00");

        // When
        basicbasegoodsbatchupdatevo.setAmount(amount);

        // Then
        assertEquals(amount, basicbasegoodsbatchupdatevo.getAmount());
    }
}
