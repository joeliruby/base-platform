package com.matariky.bizservice.assetitm.base.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseRfidfactoryAddVOTest {

    @InjectMocks
    private BasicBaseRfidfactoryAddVO basicbaserfidfactoryaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbaserfidfactoryaddvo.setId(expectedId);

        // When
        Long actualId = basicbaserfidfactoryaddvo.getId();

        // Then
        assertEquals(expectedId, actualId);
    }

    @Test
    void testGetTaskBatchCode() {
        // Given
        String expectedTaskBatchCode = "batch123";
        basicbaserfidfactoryaddvo.setTaskBatchCode(expectedTaskBatchCode);

        // When
        String actualTaskBatchCode = basicbaserfidfactoryaddvo.getTaskBatchCode();

        // Then
        assertEquals(expectedTaskBatchCode, actualTaskBatchCode);
    }

    @Test
    void testGetRfidType() {
        // Given
        String expectedRfidType = "typeA";
        basicbaserfidfactoryaddvo.setRfidType(expectedRfidType);

        // When
        String actualRfidType = basicbaserfidfactoryaddvo.getRfidType();

        // Then
        assertEquals(expectedRfidType, actualRfidType);
    }

    @Test
    void testGetTaskName() {
        // Given
        String expectedTaskName = "taskName";
        basicbaserfidfactoryaddvo.setTaskName(expectedTaskName);

        // When
        String actualTaskName = basicbaserfidfactoryaddvo.getTaskName();

        // Then
        assertEquals(expectedTaskName, actualTaskName);
    }

    @Test
    void testGetEpcRule() {
        // Given
        String expectedEpcRule = "epcRule";
        basicbaserfidfactoryaddvo.setEpcRule(expectedEpcRule);

        // When
        String actualEpcRule = basicbaserfidfactoryaddvo.getEpcRule();

        // Then
        assertEquals(expectedEpcRule, actualEpcRule);
    }

    @Test
    void testGetRfidNum() {
        // Given
        Integer expectedRfidNum = 10;
        basicbaserfidfactoryaddvo.setRfidNum(expectedRfidNum);

        // When
        Integer actualRfidNum = basicbaserfidfactoryaddvo.getRfidNum();

        // Then
        assertEquals(expectedRfidNum, actualRfidNum);
    }

    @Test
    void testGetYieldRate() {
        // Given
        BigDecimal expectedYieldRate = new BigDecimal("0.95");
        basicbaserfidfactoryaddvo.setYieldRate(expectedYieldRate);

        // When
        BigDecimal actualYieldRate = basicbaserfidfactoryaddvo.getYieldRate();

        // Then
        assertEquals(expectedYieldRate, actualYieldRate);
    }

    @Test
    void testGetCreateNum() {
        // Given
        Integer expectedCreateNum = 100;
        basicbaserfidfactoryaddvo.setCreateNum(expectedCreateNum);

        // When
        Integer actualCreateNum = basicbaserfidfactoryaddvo.getCreateNum();

        // Then
        assertEquals(expectedCreateNum, actualCreateNum);
    }

    @Test
    void testGetIsLockEpc() {
        // Given
        Integer expectedIsLockEpc = 1;
        basicbaserfidfactoryaddvo.setIsLockEpc(expectedIsLockEpc);

        // When
        Integer actualIsLockEpc = basicbaserfidfactoryaddvo.getIsLockEpc();

        // Then
        assertEquals(expectedIsLockEpc, actualIsLockEpc);
    }

    @Test
    void testGetEpcPassword() {
        // Given
        String expectedEpcPassword = "password123";
        basicbaserfidfactoryaddvo.setEpcPassword(expectedEpcPassword);

        // When
        String actualEpcPassword = basicbaserfidfactoryaddvo.getEpcPassword();

        // Then
        assertEquals(expectedEpcPassword, actualEpcPassword);
    }

    @Test
    void testGetGoodsId() {
        // Given
        Long expectedGoodsId = 2L;
        basicbaserfidfactoryaddvo.setGoodsId(expectedGoodsId);

        // When
        Long actualGoodsId = basicbaserfidfactoryaddvo.getGoodsId();

        // Then
        assertEquals(expectedGoodsId, actualGoodsId);
    }

    @Test
    void testGetIsOdcode() {
        // Given
        Integer expectedIsOdcode = 1;
        basicbaserfidfactoryaddvo.setIsOdcode(expectedIsOdcode);

        // When
        Integer actualIsOdcode = basicbaserfidfactoryaddvo.getIsOdcode();

        // Then
        assertEquals(expectedIsOdcode, actualIsOdcode);
    }

    @Test
    void testGetOdFixedContent() {
        // Given
        String expectedOdFixedContent = "fixedContent";
        basicbaserfidfactoryaddvo.setOdFixedContent(expectedOdFixedContent);

        // When
        String actualOdFixedContent = basicbaserfidfactoryaddvo.getOdFixedContent();

        // Then
        assertEquals(expectedOdFixedContent, actualOdFixedContent);
    }

    @Test
    void testGetIsQrcode() {
        // Given
        Integer expectedIsQrcode = 1;
        basicbaserfidfactoryaddvo.setIsQrcode(expectedIsQrcode);

        // When
        Integer actualIsQrcode = basicbaserfidfactoryaddvo.getIsQrcode();

        // Then
        assertEquals(expectedIsQrcode, actualIsQrcode);
    }

    @Test
    void testGetQrFixedContent() {
        // Given
        String expectedQrFixedContent = "qrContent";
        basicbaserfidfactoryaddvo.setQrFixedContent(expectedQrFixedContent);

        // When
        String actualQrFixedContent = basicbaserfidfactoryaddvo.getQrFixedContent();

        // Then
        assertEquals(expectedQrFixedContent, actualQrFixedContent);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "remark";
        basicbaserfidfactoryaddvo.setRemark(expectedRemark);

        // When
        String actualRemark = basicbaserfidfactoryaddvo.getRemark();

        // Then
        assertEquals(expectedRemark, actualRemark);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "orgCode";
        basicbaserfidfactoryaddvo.setOperatorOrgCode(expectedOperatorOrgCode);

        // When
        String actualOperatorOrgCode = basicbaserfidfactoryaddvo.getOperatorOrgCode();

        // Then
        assertEquals(expectedOperatorOrgCode, actualOperatorOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "selfOrgCode";
        basicbaserfidfactoryaddvo.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // When
        String actualOperatorSelfOrgCode = basicbaserfidfactoryaddvo.getOperatorSelfOrgCode();

        // Then
        assertEquals(expectedOperatorSelfOrgCode, actualOperatorSelfOrgCode);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "tenantId";
        basicbaserfidfactoryaddvo.setTenantId(expectedTenantId);

        // When
        String actualTenantId = basicbaserfidfactoryaddvo.getTenantId();

        // Then
        assertEquals(expectedTenantId, actualTenantId);
    }

    @Test
    void testGetCreateBy() {
        // Given
        Long expectedCreateBy = 3L;
        basicbaserfidfactoryaddvo.setCreateBy(expectedCreateBy);

        // When
        Long actualCreateBy = basicbaserfidfactoryaddvo.getCreateBy();

        // Then
        assertEquals(expectedCreateBy, actualCreateBy);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        Long expectedUpdateBy = 4L;
        basicbaserfidfactoryaddvo.setUpdateBy(expectedUpdateBy);

        // When
        Long actualUpdateBy = basicbaserfidfactoryaddvo.getUpdateBy();

        // Then
        assertEquals(expectedUpdateBy, actualUpdateBy);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;
        basicbaserfidfactoryaddvo.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = basicbaserfidfactoryaddvo.getCreateTime();

        // Then
        assertEquals(expectedCreateTime, actualCreateTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 987654321L;
        basicbaserfidfactoryaddvo.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = basicbaserfidfactoryaddvo.getUpdateTime();

        // Then
        assertEquals(expectedUpdateTime, actualUpdateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = 123123123L;
        basicbaserfidfactoryaddvo.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = basicbaserfidfactoryaddvo.getDeleteTime();

        // Then
        assertEquals(expectedDeleteTime, actualDeleteTime);
    }
}
