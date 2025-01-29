package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBasePcVersionTest {

    @InjectMocks
    private BasicBasePcVersion basicbasepcversion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbasepcversion.setId(expectedId);

        // When
        Long actualId = basicbasepcversion.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetVersionName() {
        // Given
        String expectedVersionName = "1.0.0";
        basicbasepcversion.setVersionName(expectedVersionName);

        // When
        String actualVersionName = basicbasepcversion.getVersionName();

        // Then
        assertThat(actualVersionName).isEqualTo(expectedVersionName);
    }

    @Test
    void testGetVersionNo() {
        // Given
        String expectedVersionNo = "v1";
        basicbasepcversion.setVersionNo(expectedVersionNo);

        // When
        String actualVersionNo = basicbasepcversion.getVersionNo();

        // Then
        assertThat(actualVersionNo).isEqualTo(expectedVersionNo);
    }

    @Test
    void testGetVersionContent() {
        // Given
        String expectedVersionContent = "Initial release";
        basicbasepcversion.setVersionContent(expectedVersionContent);

        // When
        String actualVersionContent = basicbasepcversion.getVersionContent();

        // Then
        assertThat(actualVersionContent).isEqualTo(expectedVersionContent);
    }

    @Test
    void testGetRequirementDate() {
        // Given
        Long expectedRequirementDate = 1627849200000L;
        basicbasepcversion.setRequirementDate(expectedRequirementDate);

        // When
        Long actualRequirementDate = basicbasepcversion.getRequirementDate();

        // Then
        assertThat(actualRequirementDate).isEqualTo(expectedRequirementDate);
    }

    @Test
    void testGetMessageShutdownTime() {
        // Given
        String expectedMessageShutdownTime = "22:00";
        basicbasepcversion.setMessageShutdownTime(expectedMessageShutdownTime);

        // When
        String actualMessageShutdownTime = basicbasepcversion.getMessageShutdownTime();

        // Then
        assertThat(actualMessageShutdownTime).isEqualTo(expectedMessageShutdownTime);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "No remarks";
        basicbasepcversion.setRemark(expectedRemark);

        // When
        String actualRemark = basicbasepcversion.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 1627849200000L;
        basicbasepcversion.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = basicbasepcversion.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 1627849200000L;
        basicbasepcversion.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = basicbasepcversion.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = 1627849200000L;
        basicbasepcversion.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = basicbasepcversion.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetCreateBy() {
        // Given
        Long expectedCreateBy = 1L;
        basicbasepcversion.setCreateBy(expectedCreateBy);

        // When
        Long actualCreateBy = basicbasepcversion.getCreateBy();

        // Then
        assertThat(actualCreateBy).isEqualTo(expectedCreateBy);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        Long expectedUpdateBy = 1L;
        basicbasepcversion.setUpdateBy(expectedUpdateBy);

        // When
        Long actualUpdateBy = basicbasepcversion.getUpdateBy();

        // Then
        assertThat(actualUpdateBy).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "ORG001";
        basicbasepcversion.setOperatorOrgCode(expectedOperatorOrgCode);

        // When
        String actualOperatorOrgCode = basicbasepcversion.getOperatorOrgCode();

        // Then
        assertThat(actualOperatorOrgCode).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "SELF001";
        basicbasepcversion.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // When
        String actualOperatorSelfOrgCode = basicbasepcversion.getOperatorSelfOrgCode();

        // Then
        assertThat(actualOperatorSelfOrgCode).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "TENANT001";
        basicbasepcversion.setTenantId(expectedTenantId);

        // When
        String actualTenantId = basicbasepcversion.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }
}
