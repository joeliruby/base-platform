package com.matariky.jobs.jobsService.assetitm.inventory.bo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TapeInventoryTaskBasicInfoTest {

    @InjectMocks
    private TapeInventoryTaskBasicInfo tapeInventoryTaskBasicInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTaskName() {
        // Given
        String taskName = "Inventory Task";
        tapeInventoryTaskBasicInfo.setTaskName(taskName);

        // When
        String result = tapeInventoryTaskBasicInfo.getTaskName();

        // Then
        assertThat(result).isEqualTo(taskName);
    }

    @Test
    void testSetTaskName() {
        // Given
        String taskName = "Inventory Task";

        // When
        tapeInventoryTaskBasicInfo.setTaskName(taskName);

        // Then
        assertThat(tapeInventoryTaskBasicInfo.getTaskName()).isEqualTo(taskName);
    }

    @Test
    void testGetId() {
        // Given
        Long id = 123L;
        tapeInventoryTaskBasicInfo.setId(id);

        // When
        Long result = tapeInventoryTaskBasicInfo.getId();

        // Then
        assertThat(result).isEqualTo(id);
    }

    @Test
    void testSetId() {
        // Given
        Long id = 123L;

        // When
        tapeInventoryTaskBasicInfo.setId(id);

        // Then
        assertThat(tapeInventoryTaskBasicInfo.getId()).isEqualTo(id);
    }
}
