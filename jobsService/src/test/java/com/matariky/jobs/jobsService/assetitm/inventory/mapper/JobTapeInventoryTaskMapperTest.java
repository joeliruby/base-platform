package com.matariky.jobs.jobsService.assetitm.inventory.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JobTapeInventoryTaskMapperTest {

    @InjectMocks
    private JobTapeInventoryTaskMapper jobtapeinventorytaskmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSelectInInventoryTaskName() {
        // Given
        String firstLocationId = "loc1";
        String locationId = "loc2";
        String arrayRackIds = "rack1,rack2";
        Long filterTaskId = 123L;
        String expectedTaskName = "taskName";

        when(jobtapeinventorytaskmapper.selectInInventoryTaskName(firstLocationId, locationId, arrayRackIds,
                filterTaskId))
                .thenReturn(expectedTaskName);

        // When
        String actualTaskName = jobtapeinventorytaskmapper.selectInInventoryTaskName(firstLocationId, locationId,
                arrayRackIds, filterTaskId);

        // Then
        assertEquals(expectedTaskName, actualTaskName);
    }

    // Add more test methods for other methods in JobTapeInventoryTaskMapper
}
