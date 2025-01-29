package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseFormExtendTest {

    @InjectMocks
    private BasicBaseFormExtend basicbaseformextend;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbaseformextend.setId(expectedId);

        // When
        Long actualId = basicbaseformextend.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetBusinessId() {
        // Given
        Long expectedBusinessId = 2L;
        basicbaseformextend.setBusinessId(expectedBusinessId);

        // When
        Long actualBusinessId = basicbaseformextend.getBusinessId();

        // Then
        assertThat(actualBusinessId).isEqualTo(expectedBusinessId);
    }

    @Test
    void testGetField0() {
        // Given
        String expectedField0 = "testField0";
        basicbaseformextend.setField0(expectedField0);

        // When
        String actualField0 = basicbaseformextend.getField0();

        // Then
        assertThat(actualField0).isEqualTo(expectedField0);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "testRemark";
        basicbaseformextend.setRemark(expectedRemark);

        // When
        String actualRemark = basicbaseformextend.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;
        basicbaseformextend.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = basicbaseformextend.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 987654321L;
        basicbaseformextend.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = basicbaseformextend.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = 1122334455L;
        basicbaseformextend.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = basicbaseformextend.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetCreateBy() {
        // Given
        Long expectedCreateBy = 123L;
        basicbaseformextend.setCreateBy(expectedCreateBy);

        // When
        Long actualCreateBy = basicbaseformextend.getCreateBy();

        // Then
        assertThat(actualCreateBy).isEqualTo(expectedCreateBy);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        Long expectedUpdateBy = 456L;
        basicbaseformextend.setUpdateBy(expectedUpdateBy);

        // When
        Long actualUpdateBy = basicbaseformextend.getUpdateBy();

        // Then
        assertThat(actualUpdateBy).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "orgCode";
        basicbaseformextend.setOperatorOrgCode(expectedOperatorOrgCode);

        // When
        String actualOperatorOrgCode = basicbaseformextend.getOperatorOrgCode();

        // Then
        assertThat(actualOperatorOrgCode).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "selfOrgCode";
        basicbaseformextend.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // When
        String actualOperatorSelfOrgCode = basicbaseformextend.getOperatorSelfOrgCode();

        // Then
        assertThat(actualOperatorSelfOrgCode).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "tenantId";
        basicbaseformextend.setTenantId(expectedTenantId);

        // When
        String actualTenantId = basicbaseformextend.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }
}
