package com.matariky.commonservice.log.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicLogNetworkTest {

    @InjectMocks
    private BasicLogNetwork basiclognetwork;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetId() {
        // Given
        Long expectedId = 1L;

        // When
        basiclognetwork.setId(expectedId);

        // Then
        assertThat(basiclognetwork.getId()).isEqualTo(expectedId);
    }

    @Test
    void testSetAndGetAccessTerminals() {
        // Given
        String expectedAccessTerminals = "Terminal1";

        // When
        basiclognetwork.setAccessTerminals(expectedAccessTerminals);

        // Then
        assertThat(basiclognetwork.getAccessTerminals()).isEqualTo(expectedAccessTerminals);
    }

    @Test
    void testSetAndGetAccessModule() {
        // Given
        String expectedAccessModule = "Module1";

        // When
        basiclognetwork.setAccessModule(expectedAccessModule);

        // Then
        assertThat(basiclognetwork.getAccessModule()).isEqualTo(expectedAccessModule);
    }

    @Test
    void testSetAndGetNetworkSignal() {
        // Given
        String expectedNetworkSignal = "Strong";

        // When
        basiclognetwork.setNetworkSignal(expectedNetworkSignal);

        // Then
        assertThat(basiclognetwork.getNetworkSignal()).isEqualTo(expectedNetworkSignal);
    }

    @Test
    void testSetAndGetBusinessTime() {
        // Given
        Long expectedBusinessTime = 123456789L;

        // When
        basiclognetwork.setBusinessTime(expectedBusinessTime);

        // Then
        assertThat(basiclognetwork.getBusinessTime()).isEqualTo(expectedBusinessTime);
    }

    @Test
    void testSetAndGetAccessAccount() {
        // Given
        String expectedAccessAccount = "Account1";

        // When
        basiclognetwork.setAccessAccount(expectedAccessAccount);

        // Then
        assertThat(basiclognetwork.getAccessAccount()).isEqualTo(expectedAccessAccount);
    }

    @Test
    void testSetAndGetLogTime() {
        // Given
        Long expectedLogTime = 987654321L;

        // When
        basiclognetwork.setLogTime(expectedLogTime);

        // Then
        assertThat(basiclognetwork.getLogTime()).isEqualTo(expectedLogTime);
    }

    @Test
    void testSetAndGetRemark() {
        // Given
        String expectedRemark = "This is a remark";

        // When
        basiclognetwork.setRemark(expectedRemark);

        // Then
        assertThat(basiclognetwork.getRemark()).isEqualTo(expectedRemark);
    }

    @Test
    void testSetAndGetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;

        // When
        basiclognetwork.setCreateTime(expectedCreateTime);

        // Then
        assertThat(basiclognetwork.getCreateTime()).isEqualTo(expectedCreateTime);
    }

    @Test
    void testSetAndGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 987654321L;

        // When
        basiclognetwork.setUpdateTime(expectedUpdateTime);

        // Then
        assertThat(basiclognetwork.getUpdateTime()).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testSetAndGetDeleteTime() {
        // Given
        Long expectedDeleteTime = 123456789L;

        // When
        basiclognetwork.setDeleteTime(expectedDeleteTime);

        // Then
        assertThat(basiclognetwork.getDeleteTime()).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testSetAndGetCreateBy() {
        // Given
        Long expectedCreateBy = 1L;

        // When
        basiclognetwork.setCreateBy(expectedCreateBy);

        // Then
        assertThat(basiclognetwork.getCreateBy()).isEqualTo(expectedCreateBy);
    }

    @Test
    void testSetAndGetUpdateBy() {
        // Given
        Long expectedUpdateBy = 2L;

        // When
        basiclognetwork.setUpdateBy(expectedUpdateBy);

        // Then
        assertThat(basiclognetwork.getUpdateBy()).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testSetAndGetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "OrgCode1";

        // When
        basiclognetwork.setOperatorOrgCode(expectedOperatorOrgCode);

        // Then
        assertThat(basiclognetwork.getOperatorOrgCode()).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testSetAndGetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "SelfOrgCode1";

        // When
        basiclognetwork.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // Then
        assertThat(basiclognetwork.getOperatorSelfOrgCode()).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testSetAndGetTenantId() {
        // Given
        String expectedTenantId = "Tenant1";

        // When
        basiclognetwork.setTenantId(expectedTenantId);

        // Then
        assertThat(basiclognetwork.getTenantId()).isEqualTo(expectedTenantId);
    }
}
