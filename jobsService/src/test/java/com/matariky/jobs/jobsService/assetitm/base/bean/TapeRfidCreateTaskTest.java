package com.matariky.jobs.jobsService.assetitm.base.bean;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TapeRfidCreateTaskTest {

    @InjectMocks
    private TapeRfidCreateTask taperfidcreatetask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        taperfidcreatetask.setId(expectedId);

        // When
        Long actualId = taperfidcreatetask.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetTaskBatchCode() {
        // Given
        String expectedTaskBatchCode = "batch123";
        taperfidcreatetask.setTaskBatchCode(expectedTaskBatchCode);

        // When
        String actualTaskBatchCode = taperfidcreatetask.getTaskBatchCode();

        // Then
        assertThat(actualTaskBatchCode).isEqualTo(expectedTaskBatchCode);
    }

    @Test
    void testGetTaskName() {
        // Given
        String expectedTaskName = "TaskName";
        taperfidcreatetask.setTaskName(expectedTaskName);

        // When
        String actualTaskName = taperfidcreatetask.getTaskName();

        // Then
        assertThat(actualTaskName).isEqualTo(expectedTaskName);
    }

    @Test
    void testGetGoodsId() {
        // Given
        Long expectedGoodsId = 100L;
        taperfidcreatetask.setGoodsId(expectedGoodsId);

        // When
        Long actualGoodsId = taperfidcreatetask.getGoodsId();

        // Then
        assertThat(actualGoodsId).isEqualTo(expectedGoodsId);
    }

    @Test
    void testGetRfidType() {
        // Given
        String expectedRfidType = "TypeA";
        taperfidcreatetask.setRfidType(expectedRfidType);

        // When
        String actualRfidType = taperfidcreatetask.getRfidType();

        // Then
        assertThat(actualRfidType).isEqualTo(expectedRfidType);
    }

    @Test
    void testGetEpcRule() {
        // Given
        String expectedEpcRule = "Rule123";
        taperfidcreatetask.setEpcRule(expectedEpcRule);

        // When
        String actualEpcRule = taperfidcreatetask.getEpcRule();

        // Then
        assertThat(actualEpcRule).isEqualTo(expectedEpcRule);
    }

    @Test
    void testGetRfidNum() {
        // Given
        Integer expectedRfidNum = 10;
        taperfidcreatetask.setRfidNum(expectedRfidNum);

        // When
        Integer actualRfidNum = taperfidcreatetask.getRfidNum();

        // Then
        assertThat(actualRfidNum).isEqualTo(expectedRfidNum);
    }

    @Test
    void testGetYieldRate() {
        // Given
        BigDecimal expectedYieldRate = new BigDecimal("0.95");
        taperfidcreatetask.setYieldRate(expectedYieldRate);

        // When
        BigDecimal actualYieldRate = taperfidcreatetask.getYieldRate();

        // Then
        assertThat(actualYieldRate).isEqualTo(expectedYieldRate);
    }

    @Test
    void testGetCreateNum() {
        // Given
        Integer expectedCreateNum = 100;
        taperfidcreatetask.setCreateNum(expectedCreateNum);

        // When
        Integer actualCreateNum = taperfidcreatetask.getCreateNum();

        // Then
        assertThat(actualCreateNum).isEqualTo(expectedCreateNum);
    }

    @Test
    void testGetIsLockEpc() {
        // Given
        Integer expectedIsLockEpc = 1;
        taperfidcreatetask.setIsLockEpc(expectedIsLockEpc);

        // When
        Integer actualIsLockEpc = taperfidcreatetask.getIsLockEpc();

        // Then
        assertThat(actualIsLockEpc).isEqualTo(expectedIsLockEpc);
    }

    @Test
    void testGetEpcPassword() {
        // Given
        String expectedEpcPassword = "password123";
        taperfidcreatetask.setEpcPassword(expectedEpcPassword);

        // When
        String actualEpcPassword = taperfidcreatetask.getEpcPassword();

        // Then
        assertThat(actualEpcPassword).isEqualTo(expectedEpcPassword);
    }

    @Test
    void testGetIsOdcode() {
        // Given
        Integer expectedIsOdcode = 1;
        taperfidcreatetask.setIsOdcode(expectedIsOdcode);

        // When
        Integer actualIsOdcode = taperfidcreatetask.getIsOdcode();

        // Then
        assertThat(actualIsOdcode).isEqualTo(expectedIsOdcode);
    }

    @Test
    void testGetOdFixedContent() {
        // Given
        String expectedOdFixedContent = "fixedContent";
        taperfidcreatetask.setOdFixedContent(expectedOdFixedContent);

        // When
        String actualOdFixedContent = taperfidcreatetask.getOdFixedContent();

        // Then
        assertThat(actualOdFixedContent).isEqualTo(expectedOdFixedContent);
    }

    @Test
    void testGetIsQrcode() {
        // Given
        Integer expectedIsQrcode = 1;
        taperfidcreatetask.setIsQrcode(expectedIsQrcode);

        // When
        Integer actualIsQrcode = taperfidcreatetask.getIsQrcode();

        // Then
        assertThat(actualIsQrcode).isEqualTo(expectedIsQrcode);
    }

    @Test
    void testGetQrFixedContent() {
        // Given
        String expectedQrFixedContent = "qrContent";
        taperfidcreatetask.setQrFixedContent(expectedQrFixedContent);

        // When
        String actualQrFixedContent = taperfidcreatetask.getQrFixedContent();

        // Then
        assertThat(actualQrFixedContent).isEqualTo(expectedQrFixedContent);
    }

    @Test
    void testGetIsFileCreate() {
        // Given
        Integer expectedIsFileCreate = 1;
        taperfidcreatetask.setIsFileCreate(expectedIsFileCreate);

        // When
        Integer actualIsFileCreate = taperfidcreatetask.getIsFileCreate();

        // Then
        assertThat(actualIsFileCreate).isEqualTo(expectedIsFileCreate);
    }

    @Test
    void testGetDownloadUrl() {
        // Given
        String expectedDownloadUrl = "http://example.com";
        taperfidcreatetask.setDownloadUrl(expectedDownloadUrl);

        // When
        String actualDownloadUrl = taperfidcreatetask.getDownloadUrl();

        // Then
        assertThat(actualDownloadUrl).isEqualTo(expectedDownloadUrl);
    }

    @Test
    void testGetDownloadNum() {
        // Given
        Integer expectedDownloadNum = 5;
        taperfidcreatetask.setDownloadNum(expectedDownloadNum);

        // When
        Integer actualDownloadNum = taperfidcreatetask.getDownloadNum();

        // Then
        assertThat(actualDownloadNum).isEqualTo(expectedDownloadNum);
    }

    @Test
    void testGetDownloadTime() {
        // Given
        Long expectedDownloadTime = 123456789L;
        taperfidcreatetask.setDownloadTime(expectedDownloadTime);

        // When
        Long actualDownloadTime = taperfidcreatetask.getDownloadTime();

        // Then
        assertThat(actualDownloadTime).isEqualTo(expectedDownloadTime);
    }

    @Test
    void testGetTaskStatus() {
        // Given
        Integer expectedTaskStatus = 1;
        taperfidcreatetask.setTaskStatus(expectedTaskStatus);

        // When
        Integer actualTaskStatus = taperfidcreatetask.getTaskStatus();

        // Then
        assertThat(actualTaskStatus).isEqualTo(expectedTaskStatus);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "This is a remark";
        taperfidcreatetask.setRemark(expectedRemark);

        // When
        String actualRemark = taperfidcreatetask.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;
        taperfidcreatetask.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = taperfidcreatetask.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 987654321L;
        taperfidcreatetask.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = taperfidcreatetask.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = 123123123L;
        taperfidcreatetask.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = taperfidcreatetask.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetCreateBy() {
        // Given
        Long expectedCreateBy = 1L;
        taperfidcreatetask.setCreateBy(expectedCreateBy);

        // When
        Long actualCreateBy = taperfidcreatetask.getCreateBy();

        // Then
        assertThat(actualCreateBy).isEqualTo(expectedCreateBy);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        Long expectedUpdateBy = 2L;
        taperfidcreatetask.setUpdateBy(expectedUpdateBy);

        // When
        Long actualUpdateBy = taperfidcreatetask.getUpdateBy();

        // Then
        assertThat(actualUpdateBy).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "ORG123";
        taperfidcreatetask.setOperatorOrgCode(expectedOperatorOrgCode);

        // When
        String actualOperatorOrgCode = taperfidcreatetask.getOperatorOrgCode();

        // Then
        assertThat(actualOperatorOrgCode).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "SELFORG123";
        taperfidcreatetask.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // When
        String actualOperatorSelfOrgCode = taperfidcreatetask.getOperatorSelfOrgCode();

        // Then
        assertThat(actualOperatorSelfOrgCode).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "TENANT123";
        taperfidcreatetask.setTenantId(expectedTenantId);

        // When
        String actualTenantId = taperfidcreatetask.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }
}
