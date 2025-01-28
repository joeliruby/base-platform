package com.matariky.bizservice.assetitm.base.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.bizservice.assetitm.base.service.BasicBaseRfidprintDetailService;
import com.matariky.bizservice.assetitm.base.service.BasicBaseRfidprintService;
import com.matariky.commonservice.minio.utils.MinioUtil;
import org.mockito.Mock;

@SpringBootTest
public class BasicBaseRfidprintControllerTest {

    @InjectMocks
    private BasicBaseRfidprintController basicBaseRfidprintController;

    @Mock
    private BasicBaseRfidprintService basicBaseRfidprintService;

    @Mock
    private MinioUtil minioUtil;

    @Mock
    private BasicBaseRfidprintDetailService baseRfidprintDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        // Initialize your test data and mocks here

        // When
        // Call the method you want to test

        // Then
        // Assert the expected results
        assertThat(basicBaseRfidprintController.list(null, null, "tenantId", 1, 10, "jwt")).isNotNull();
    }

    @Test
    void testAppList() {
        // Given
        // Initialize your test data and mocks here

        // When
        // Call the method you want to test

        // Then
        // Assert the expected results
        assertThat(basicBaseRfidprintController.applist(null, null, "tenantId", "deviceCode", 1, 10, "jwt"))
                .isNotNull();
    }

    @Test
    void testSave() {
        // Given
        // Initialize your test data and mocks here

        // When
        // Call the method you want to test

        // Then
        // Assert the expected results
        assertThat(basicBaseRfidprintController.save(null, null, null, "tenantId", "jwt")).isNotNull();
    }

    @Test
    void testGetOne() {
        // Given
        // Initialize your test data and mocks here

        // When
        // Call the method you want to test

        // Then
        // Assert the expected results
        assertThat(basicBaseRfidprintController.getOne(null, null, 1L)).isNotNull();
    }

    @Test
    void testUploadImg() throws Exception {
        // Given
        // Initialize your test data and mocks here

        // When
        // Call the method you want to test

        // Then
        // Assert the expected results
        assertThat(basicBaseRfidprintController.uploadImg(null, "bucket", "objectName", "tenantId")).isNotNull();
    }

    @Test
    void testPrint() {
        // Given
        // Initialize your test data and mocks here

        // When
        // Call the method you want to test

        // Then
        // Assert the expected results
        assertThat(basicBaseRfidprintController.print(null, null, "tenantId", 1, 10)).isNotNull();
    }

    @Test
    void testSuppPrint() {
        // Given
        // Initialize your test data and mocks here

        // When
        // Call the method you want to test

        // Then
        // Assert the expected results
        assertThat(basicBaseRfidprintController.suppprint(null, null, 1L, "jwt")).isNotNull();
    }

    @Test
    void testSelectPrint() {
        // Given
        // Initialize your test data and mocks here

        // When
        // Call the method you want to test

        // Then
        // Assert the expected results
        assertThat(basicBaseRfidprintController.suppprint("mac")).isNotNull();
    }

    @Test
    void testPrintLock() {
        // Given
        // Initialize your test data and mocks here

        // When
        // Call the method you want to test

        // Then
        // Assert the expected results
        assertThat(basicBaseRfidprintController.printlock(1L, "deviceCode")).isNotNull();
    }

    @Test
    void testPrintUnlock() {
        // Given
        // Initialize your test data and mocks here

        // When
        // Call the method you want to test

        // Then
        // Assert the expected results
        assertThat(basicBaseRfidprintController.printunlock(1L)).isNotNull();
    }

    @Test
    void testSelectLock() {
        // Given
        // Initialize your test data and mocks here

        // When
        // Call the method you want to test

        // Then
        // Assert the expected results
        assertThat(basicBaseRfidprintController.selectlock(1L)).isNotNull();
    }

    // Add more test methods for other methods in BasicBaseRfidprintController
}
