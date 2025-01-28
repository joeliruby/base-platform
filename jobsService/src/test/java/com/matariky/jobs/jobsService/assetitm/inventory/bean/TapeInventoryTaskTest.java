package com.matariky.jobs.jobsService.assetitm.inventory.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TapeInventoryTaskTest {

    @InjectMocks
    private TapeInventoryTask tapeInventoryTask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetId() {
        // Given
        Long expectedId = 1L;

        // When
        tapeInventoryTask.setId(expectedId);

        // Then
        assertEquals(expectedId, tapeInventoryTask.getId());
    }

    @Test
    void testSetAndGetOperatorOrgCode() {
        // Given
        String expectedCode = "ORG123";

        // When
        tapeInventoryTask.setOperatorOrgCode(expectedCode);

        // Then
        assertEquals(expectedCode, tapeInventoryTask.getOperatorOrgCode());
    }

    @Test
    void testSetAndGetLibraryIds() {
        // Given
        Long[] ids = { 1L, 2L, 3L };
        tapeInventoryTask.setLibraryIds(Arrays.asList(ids));

        // When
        List<Long> libraryIds = tapeInventoryTask.getLibraryIds();

        // Then
        assertEquals(Arrays.asList(ids), libraryIds);
    }

    @Test
    void testSetAndGetTaskName() {
        // Given
        String expectedName = "Inventory Task";

        // When
        tapeInventoryTask.setTaskName(expectedName);

        // Then
        assertEquals(expectedName, tapeInventoryTask.getTaskName());
    }

    @Test
    void testSetAndGetStatus() {
        // Given
        Integer expectedStatus = 1;

        // When
        tapeInventoryTask.setStatus(expectedStatus);

        // Then
        assertEquals(expectedStatus, tapeInventoryTask.getStatus());
    }

    @Test
    void testSetAndGetProcessStatus() {
        // Given
        Integer expectedProcessStatus = 2;

        // When
        tapeInventoryTask.setProcessStatus(expectedProcessStatus);

        // Then
        assertEquals(expectedProcessStatus, tapeInventoryTask.getProcessStatus());
    }

    @Test
    void testSetAndGetIsDiscrepancy() {
        // Given
        Boolean expectedDiscrepancy = true;

        // When
        tapeInventoryTask.setIsDiscrepancy(expectedDiscrepancy);

        // Then
        assertEquals(expectedDiscrepancy, tapeInventoryTask.getIsDiscrepancy());
    }

    // Add more test methods for other getters and setters in TapeInventoryTask
}
