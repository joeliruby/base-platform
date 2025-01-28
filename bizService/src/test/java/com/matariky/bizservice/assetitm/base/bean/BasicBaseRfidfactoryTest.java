package com.matariky.bizservice.assetitm.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;

@SpringBootTest
public class BasicBaseRfidfactoryTest {

    @InjectMocks
    private BasicBaseRfidfactory basicbaserfidfactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbaserfidfactory.setId(expectedId);

        // When
        Long actualId = basicbaserfidfactory.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetTaskBatchCode() {
        // Given
        String expectedTaskBatchCode = "batch123";
        basicbaserfidfactory.setTaskBatchCode(expectedTaskBatchCode);

        // When
        String actualTaskBatchCode = basicbaserfidfactory.getTaskBatchCode();

        // Then
        assertThat(actualTaskBatchCode).isEqualTo(expectedTaskBatchCode);
    }

    @Test
    void testGetYieldRate() {
        // Given
        BigDecimal expectedYieldRate = new BigDecimal("99.99");
        basicbaserfidfactory.setYieldRate(expectedYieldRate);

        // When
        BigDecimal actualYieldRate = basicbaserfidfactory.getYieldRate();

        // Then
        assertThat(actualYieldRate).isEqualTo(expectedYieldRate);
    }

    @Test
    void testIsLockEpc() {
        // Given
        Integer expectedIsLockEpc = 1;
        basicbaserfidfactory.setIsLockEpc(expectedIsLockEpc);

        // When
        Integer actualIsLockEpc = basicbaserfidfactory.getIsLockEpc();

        // Then
        assertThat(actualIsLockEpc).isEqualTo(expectedIsLockEpc);
    }

    @Test
    void testGetDownloadUrl() {
        // Given
        String expectedDownloadUrl = "http://example.com/download";
        basicbaserfidfactory.setDownloadUrl(expectedDownloadUrl);

        // When
        String actualDownloadUrl = basicbaserfidfactory.getDownloadUrl();

        // Then
        assertThat(actualDownloadUrl).isEqualTo(expectedDownloadUrl);
    }

    // Add more test methods for other getters and setters in BasicBaseRfidfactory
}
