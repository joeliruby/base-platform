package com.matariky.jobs.jobsService.bean.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RfidPrintJobFormTest {

    @InjectMocks
    private RfidPrintJobForm rfidprintjobform;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTaskId() {
        // Given
        Long expectedTaskId = 123L;
        rfidprintjobform.setTaskId(expectedTaskId);

        // When
        Long actualTaskId = rfidprintjobform.getTaskId();

        // Then
        assertThat(actualTaskId).isEqualTo(expectedTaskId);
    }

    @Test
    void testGetStartTime() {
        // Given
        Long expectedStartTime = 456L;
        rfidprintjobform.setStartTime(expectedStartTime);

        // When
        Long actualStartTime = rfidprintjobform.getStartTime();

        // Then
        assertThat(actualStartTime).isEqualTo(expectedStartTime);
    }

    @Test
    void testGetTaskType() {
        // Given
        Integer expectedTaskType = 2;
        rfidprintjobform.setTaskType(expectedTaskType);

        // When
        Integer actualTaskType = rfidprintjobform.getTaskType();

        // Then
        assertThat(actualTaskType).isEqualTo(expectedTaskType);
    }

    @Test
    void testSetTaskId() {
        // Given
        Long expectedTaskId = 789L;

        // When
        rfidprintjobform.setTaskId(expectedTaskId);

        // Then
        assertThat(rfidprintjobform.getTaskId()).isEqualTo(expectedTaskId);
    }

    @Test
    void testSetStartTime() {
        // Given
        Long expectedStartTime = 101112L;

        // When
        rfidprintjobform.setStartTime(expectedStartTime);

        // Then
        assertThat(rfidprintjobform.getStartTime()).isEqualTo(expectedStartTime);
    }

    @Test
    void testSetTaskType() {
        // Given
        Integer expectedTaskType = 3;

        // When
        rfidprintjobform.setTaskType(expectedTaskType);

        // Then
        assertThat(rfidprintjobform.getTaskType()).isEqualTo(expectedTaskType);
    }
}
