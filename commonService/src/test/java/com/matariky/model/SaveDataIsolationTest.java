package com.matariky.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class SaveDataIsolationTest {

    @InjectMocks
    private SaveDataIsolation savedataisolation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedOrgCode = "ORG123";
        savedataisolation.setOperatorOrgCode(expectedOrgCode);

        // When
        String actualOrgCode = savedataisolation.getOperatorOrgCode();

        // Then
        assertThat(actualOrgCode).isEqualTo(expectedOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        String expectedSelfOrgCode = "SELF123";
        savedataisolation.setOperatorSelfOrgCode(expectedSelfOrgCode);

        // When
        String actualSelfOrgCode = savedataisolation.getOperatorSelfOrgCode();

        // Then
        assertThat(actualSelfOrgCode).isEqualTo(expectedSelfOrgCode);
    }

    @Test
    void testGetOperationIp() {
        // Given
        String expectedIp = "192.168.1.1";
        savedataisolation.setOperationIp(expectedIp);

        // When
        String actualIp = savedataisolation.getOperationIp();

        // Then
        assertThat(actualIp).isEqualTo(expectedIp);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        SaveDataIsolation anotherSaveDataIsolation = new SaveDataIsolation();
        anotherSaveDataIsolation.setOperatorOrgCode("ORG123");
        anotherSaveDataIsolation.setOperatorSelfOrgCode("SELF123");
        anotherSaveDataIsolation.setOperationIp("192.168.1.1");

        savedataisolation.setOperatorOrgCode("ORG123");
        savedataisolation.setOperatorSelfOrgCode("SELF123");
        savedataisolation.setOperationIp("192.168.1.1");

        // When & Then
        assertThat(savedataisolation).isEqualTo(anotherSaveDataIsolation);
        assertThat(savedataisolation.hashCode()).isEqualTo(anotherSaveDataIsolation.hashCode());
    }
}
