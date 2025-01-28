package com.matariky.jobs.jobsService.assetitm.base.job;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.jobs.jobsService.assetitm.base.service.TapeRfidUploadTaskJobService;
import org.mockito.Mock;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

@SpringBootTest
public class TapeRfidUploadTaskJobTest {

    @InjectMocks
    private TapeRfidUploadTaskJob taperfiduploadtaskjob;

    @Mock
    private TapeRfidUploadTaskJobService tapeRfidUploadTaskJobService;

    @Mock
    private JobExecutionContext jobExecutionContext;

    @Mock
    private Trigger trigger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(jobExecutionContext.getTrigger()).thenReturn(trigger);
    }

    @Test
    void testExecuteSuccess() throws JobExecutionException {
        // Given
        when(trigger.getJobKey().getGroup()).thenReturn("1");

        // When
        taperfiduploadtaskjob.execute(jobExecutionContext);

        // Then
        try {
            verify(tapeRfidUploadTaskJobService, times(1)).start(1L);
        } catch (Exception e) {
            org.junit.jupiter.api.Assertions.fail("Exception thrown during verification: " + e.getMessage());
        }
    }

    @Test
    void testExecuteException() throws JobExecutionException {
        // Given
        when(trigger.getJobKey().getGroup()).thenReturn("1");
        try {
            doThrow(new RuntimeException("Test Exception")).when(tapeRfidUploadTaskJobService).start(1L);
        } catch (Exception e) {
            org.junit.jupiter.api.Assertions.fail("Exception thrown during setup: " + e.getMessage());
        }

        // When
        taperfiduploadtaskjob.execute(jobExecutionContext);

        // Then
        try {
            verify(tapeRfidUploadTaskJobService, times(1)).start(1L);
        } catch (Exception e) {
            org.junit.jupiter.api.Assertions.fail("Exception thrown during verification: " + e.getMessage());
        }
    }

    // Add more test methods for other scenarios in TapeRfidUploadTaskJob
}
