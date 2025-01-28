package com.matariky.jobs.jobsService.assetitm.inventory.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.jobs.jobsService.assetitm.inventory.bean.TapeInventorySubtask;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JobTapeInventorySubtaskMapperTest {

    @InjectMocks
    private JobTapeInventorySubtaskMapper jobtapeinventorysubtaskmapper;

    @Mock
    private TapeInventorySubtask tapeInventorySubtask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSelectDiscrepancySubtask() {
        // Given
        String firstLocationId = "loc1";
        String locationId = "loc2";
        String arrayRackIds = "rack1,rack2";
        List<TapeInventorySubtask> expectedSubtasks = Arrays.asList(tapeInventorySubtask);

        when(jobtapeinventorysubtaskmapper.selectDiscrepancySubtask(firstLocationId, locationId, arrayRackIds))
                .thenReturn(expectedSubtasks);

        // When
        List<TapeInventorySubtask> result = jobtapeinventorysubtaskmapper.selectDiscrepancySubtask(firstLocationId,
                locationId, arrayRackIds);

        // Then
        assertNotNull(result);
        assertEquals(expectedSubtasks, result);
    }

    // Add more test methods for other methods in JobTapeInventorySubtaskMapper
}
