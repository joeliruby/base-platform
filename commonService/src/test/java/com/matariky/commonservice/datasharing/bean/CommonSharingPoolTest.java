package com.matariky.commonservice.datasharing.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CommonSharingPoolTest {

    @InjectMocks
    private CommonSharingPool commonSharingPool;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        commonSharingPool.setId(expectedId);

        // When
        Long actualId = commonSharingPool.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testSetId() {
        // Given
        Long expectedId = 1L;

        // When
        commonSharingPool.setId(expectedId);

        // Then
        assertThat(commonSharingPool.getId()).isEqualTo(expectedId);
    }

    @Test
    void testGetSharerType() {
        // Given
        Long expectedSharerType = 2L;
        commonSharingPool.setSharerType(expectedSharerType);

        // When
        Long actualSharerType = commonSharingPool.getSharerType();

        // Then
        assertThat(actualSharerType).isEqualTo(expectedSharerType);
    }

    @Test
    void testSetSharerType() {
        // Given
        Long expectedSharerType = 2L;

        // When
        commonSharingPool.setSharerType(expectedSharerType);

        // Then
        assertThat(commonSharingPool.getSharerType()).isEqualTo(expectedSharerType);
    }

    @Test
    void testGetReceiverType() {
        // Given
        Long expectedReceiverType = 3L;
        commonSharingPool.setReceiverType(expectedReceiverType);

        // When
        Long actualReceiverType = commonSharingPool.getReceiverType();

        // Then
        assertThat(actualReceiverType).isEqualTo(expectedReceiverType);
    }

    @Test
    void testSetReceiverType() {
        // Given
        Long expectedReceiverType = 3L;

        // When
        commonSharingPool.setReceiverType(expectedReceiverType);

        // Then
        assertThat(commonSharingPool.getReceiverType()).isEqualTo(expectedReceiverType);
    }

    @Test
    void testGetResourceTableName() {
        // Given
        String expectedResourceTableName = "resource_table";
        commonSharingPool.setResourceTableName(expectedResourceTableName);

        // When
        String actualResourceTableName = commonSharingPool.getResourceTableName();

        // Then
        assertThat(actualResourceTableName).isEqualTo(expectedResourceTableName);
    }

    @Test
    void testSetResourceTableName() {
        // Given
        String expectedResourceTableName = "resource_table";

        // When
        commonSharingPool.setResourceTableName(expectedResourceTableName);

        // Then
        assertThat(commonSharingPool.getResourceTableName()).isEqualTo(expectedResourceTableName);
    }

    // Add more test methods for other getters and setters in CommonSharingPool
}
