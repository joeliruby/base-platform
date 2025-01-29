package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;

@SpringBootTest
public class BasicBaseGoodsBatchAddVOTest {

    @InjectMocks
    private BasicBaseGoodsBatchAddVO basicBaseGoodsBatchAddVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long goodsId = 1L;
        String batchCode = "BATCH123";
        Long productionDate = 1625097600000L;
        Long validityDate = 1656633600000L;
        String supplier = "SupplierName";
        BigDecimal amount = new BigDecimal("100.00");

        // When
        basicBaseGoodsBatchAddVO.setGoodsId(goodsId);
        basicBaseGoodsBatchAddVO.setBatchCode(batchCode);
        basicBaseGoodsBatchAddVO.setProductionDate(productionDate);
        basicBaseGoodsBatchAddVO.setValidityDate(validityDate);
        basicBaseGoodsBatchAddVO.setSupplier(supplier);
        basicBaseGoodsBatchAddVO.setAmount(amount);

        // Then
        assertThat(basicBaseGoodsBatchAddVO.getGoodsId()).isEqualTo(goodsId);
        assertThat(basicBaseGoodsBatchAddVO.getBatchCode()).isEqualTo(batchCode);
        assertThat(basicBaseGoodsBatchAddVO.getProductionDate()).isEqualTo(productionDate);
        assertThat(basicBaseGoodsBatchAddVO.getValidityDate()).isEqualTo(validityDate);
        assertThat(basicBaseGoodsBatchAddVO.getSupplier()).isEqualTo(supplier);
        assertThat(basicBaseGoodsBatchAddVO.getAmount()).isEqualTo(amount);
    }

    @Test
    void testNoArgsConstructor() {
        // Given
        BasicBaseGoodsBatchAddVO vo = new BasicBaseGoodsBatchAddVO();

        // Then
        assertThat(vo).isNotNull();
    }

    @Test
    void testAllArgsConstructor() {
        // Given
        Long goodsId = 1L;
        String batchCode = "BATCH123";
        Long productionDate = 1625097600000L;
        Long validityDate = 1656633600000L;
        String supplier = "SupplierName";
        BigDecimal amount = new BigDecimal("100.00");

        // When
        BasicBaseGoodsBatchAddVO vo = new BasicBaseGoodsBatchAddVO();
        vo.setGoodsId(goodsId);
        vo.setBatchCode(batchCode);
        vo.setProductionDate(productionDate);
        vo.setValidityDate(validityDate);
        vo.setSupplier(supplier);
        vo.setAmount(amount);

        // Then
        assertThat(vo.getGoodsId()).isEqualTo(goodsId);
        assertThat(vo.getBatchCode()).isEqualTo(batchCode);
        assertThat(vo.getProductionDate()).isEqualTo(productionDate);
        assertThat(vo.getValidityDate()).isEqualTo(validityDate);
        assertThat(vo.getSupplier()).isEqualTo(supplier);
        assertThat(vo.getAmount()).isEqualTo(amount);
    }
}
