package com.matariky.commonservice.base.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseGoodsBatchTest {

    @InjectMocks
    private BasicBaseGoodsBatch basicbasegoodsbatch;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        Long goodsId = 2L;
        String batchCode = "BATCH123";
        Long productionDate = 1627849200000L;
        Long validityDate = 1627849200000L;
        String supplier = "SupplierName";
        BigDecimal amount = new BigDecimal("100.00");
        String remark = "Remark";
        Long createTime = 1627849200000L;
        Long updateTime = 1627849200000L;
        Long deleteTime = 1627849200000L;
        Long createBy = 1L;
        Long updateBy = 2L;
        String operatorOrgCode = "ORG123";
        String operatorSelfOrgCode = "SELFORG123";
        String tenantId = "TENANT123";

        // When
        basicbasegoodsbatch.setId(id);
        basicbasegoodsbatch.setGoodsId(goodsId);
        basicbasegoodsbatch.setBatchCode(batchCode);
        basicbasegoodsbatch.setProductionDate(productionDate);
        basicbasegoodsbatch.setValidityDate(validityDate);
        basicbasegoodsbatch.setSupplier(supplier);
        basicbasegoodsbatch.setAmount(amount);
        basicbasegoodsbatch.setRemark(remark);
        basicbasegoodsbatch.setCreateTime(createTime);
        basicbasegoodsbatch.setUpdateTime(updateTime);
        basicbasegoodsbatch.setDeleteTime(deleteTime);
        basicbasegoodsbatch.setCreateBy(createBy);
        basicbasegoodsbatch.setUpdateBy(updateBy);
        basicbasegoodsbatch.setOperatorOrgCode(operatorOrgCode);
        basicbasegoodsbatch.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicbasegoodsbatch.setTenantId(tenantId);

        // Then
        assertEquals(id, basicbasegoodsbatch.getId());
        assertEquals(goodsId, basicbasegoodsbatch.getGoodsId());
        assertEquals(batchCode, basicbasegoodsbatch.getBatchCode());
        assertEquals(productionDate, basicbasegoodsbatch.getProductionDate());
        assertEquals(validityDate, basicbasegoodsbatch.getValidityDate());
        assertEquals(supplier, basicbasegoodsbatch.getSupplier());
        assertEquals(amount, basicbasegoodsbatch.getAmount());
        assertEquals(remark, basicbasegoodsbatch.getRemark());
        assertEquals(createTime, basicbasegoodsbatch.getCreateTime());
        assertEquals(updateTime, basicbasegoodsbatch.getUpdateTime());
        assertEquals(deleteTime, basicbasegoodsbatch.getDeleteTime());
        assertEquals(createBy, basicbasegoodsbatch.getCreateBy());
        assertEquals(updateBy, basicbasegoodsbatch.getUpdateBy());
        assertEquals(operatorOrgCode, basicbasegoodsbatch.getOperatorOrgCode());
        assertEquals(operatorSelfOrgCode, basicbasegoodsbatch.getOperatorSelfOrgCode());
        assertEquals(tenantId, basicbasegoodsbatch.getTenantId());
    }

    @Test
    void testToString() {
        // Given
        basicbasegoodsbatch.setId(1L);
        basicbasegoodsbatch.setGoodsId(2L);
        basicbasegoodsbatch.setBatchCode("BATCH123");

        // When
        String result = basicbasegoodsbatch.toString();

        // Then
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("goodsId=2"));
        assertTrue(result.contains("batchCode=BATCH123"));
    }

    // Add more test methods for other methods in BasicBaseGoodsBatch
}
