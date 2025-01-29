package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseAntifakeTest {

    @InjectMocks
    private BasicBaseAntifake basicbaseantifake;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbaseantifake.setId(expectedId);

        // When
        Long actualId = basicbaseantifake.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testSetValidateBatchCode() {
        // Given
        String expectedCode = "ABC123";
        basicbaseantifake.setValidateBatchCode(expectedCode);

        // When
        String actualCode = basicbaseantifake.getValidateBatchCode();

        // Then
        assertThat(actualCode).isEqualTo(expectedCode);
    }

    @Test
    void testSetIpAddress() {
        // Given
        String expectedIp = "192.168.1.1";
        basicbaseantifake.setIpAddress(expectedIp);

        // When
        String actualIp = basicbaseantifake.getIpAddress();

        // Then
        assertThat(actualIp).isEqualTo(expectedIp);
    }

    @Test
    void testSetValidateUrl() {
        // Given
        String expectedUrl = "http://example.com";
        basicbaseantifake.setValidateUrl(expectedUrl);

        // When
        String actualUrl = basicbaseantifake.getValidateUrl();

        // Then
        assertThat(actualUrl).isEqualTo(expectedUrl);
    }

    @Test
    void testSetValidateNum() {
        // Given
        Integer expectedNum = 10;
        basicbaseantifake.setValidateNum(expectedNum);

        // When
        Integer actualNum = basicbaseantifake.getValidateNum();

        // Then
        assertThat(actualNum).isEqualTo(expectedNum);
    }

    @Test
    void testSetSuccessNum() {
        // Given
        Integer expectedNum = 8;
        basicbaseantifake.setSuccessNum(expectedNum);

        // When
        Integer actualNum = basicbaseantifake.getSuccessNum();

        // Then
        assertThat(actualNum).isEqualTo(expectedNum);
    }

    @Test
    void testSetFailNum() {
        // Given
        Integer expectedNum = 2;
        basicbaseantifake.setFailNum(expectedNum);

        // When
        Integer actualNum = basicbaseantifake.getFailNum();

        // Then
        assertThat(actualNum).isEqualTo(expectedNum);
    }

    @Test
    void testSetValidateStatus() {
        // Given
        Integer expectedStatus = 1;
        basicbaseantifake.setValidateStatus(expectedStatus);

        // When
        Integer actualStatus = basicbaseantifake.getValidateStatus();

        // Then
        assertThat(actualStatus).isEqualTo(expectedStatus);
    }

    @Test
    void testSetValidateTime() {
        // Given
        Long expectedTime = System.currentTimeMillis();
        basicbaseantifake.setValidateTiime(expectedTime);

        // When
        Long actualTime = basicbaseantifake.getValidateTiime();

        // Then
        assertThat(actualTime).isEqualTo(expectedTime);
    }

    @Test
    void testSetRemark() {
        // Given
        String expectedRemark = "Test Remark";
        basicbaseantifake.setRemark(expectedRemark);

        // When
        String actualRemark = basicbaseantifake.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    // Add more test methods for other fields in BasicBaseAntifake
}
