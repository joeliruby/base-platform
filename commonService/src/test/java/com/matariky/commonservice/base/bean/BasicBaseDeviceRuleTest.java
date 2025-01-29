package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseDeviceRuleTest {

    @InjectMocks
    private BasicBaseDeviceRule basicBaseDeviceRule;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicBaseDeviceRule.setId(expectedId);

        // When
        Long actualId = basicBaseDeviceRule.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetTypeId() {
        // Given
        Long expectedTypeId = 2L;
        basicBaseDeviceRule.setTypeId(expectedTypeId);

        // When
        Long actualTypeId = basicBaseDeviceRule.getTypeId();

        // Then
        assertThat(actualTypeId).isEqualTo(expectedTypeId);
    }

    @Test
    void testIsRecordLog() {
        // Given
        Integer expectedIsRecordLog = 1;
        basicBaseDeviceRule.setIsRecordLog(expectedIsRecordLog);

        // When
        Integer actualIsRecordLog = basicBaseDeviceRule.getIsRecordLog();

        // Then
        assertThat(actualIsRecordLog).isEqualTo(expectedIsRecordLog);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "Test Remark";
        basicBaseDeviceRule.setRemark(expectedRemark);

        // When
        String actualRemark = basicBaseDeviceRule.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = System.currentTimeMillis();
        basicBaseDeviceRule.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = basicBaseDeviceRule.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = System.currentTimeMillis();
        basicBaseDeviceRule.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = basicBaseDeviceRule.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = System.currentTimeMillis();
        basicBaseDeviceRule.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = basicBaseDeviceRule.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetCreateBy() {
        // Given
        Long expectedCreateBy = 3L;
        basicBaseDeviceRule.setCreateBy(expectedCreateBy);

        // When
        Long actualCreateBy = basicBaseDeviceRule.getCreateBy();

        // Then
        assertThat(actualCreateBy).isEqualTo(expectedCreateBy);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        Long expectedUpdateBy = 4L;
        basicBaseDeviceRule.setUpdateBy(expectedUpdateBy);

        // When
        Long actualUpdateBy = basicBaseDeviceRule.getUpdateBy();

        // Then
        assertThat(actualUpdateBy).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "ORG123";
        basicBaseDeviceRule.setOperatorOrgCode(expectedOperatorOrgCode);

        // When
        String actualOperatorOrgCode = basicBaseDeviceRule.getOperatorOrgCode();

        // Then
        assertThat(actualOperatorOrgCode).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "SELFORG123";
        basicBaseDeviceRule.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // When
        String actualOperatorSelfOrgCode = basicBaseDeviceRule.getOperatorSelfOrgCode();

        // Then
        assertThat(actualOperatorSelfOrgCode).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "TENANT123";
        basicBaseDeviceRule.setTenantId(expectedTenantId);

        // When
        String actualTenantId = basicBaseDeviceRule.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }
}
