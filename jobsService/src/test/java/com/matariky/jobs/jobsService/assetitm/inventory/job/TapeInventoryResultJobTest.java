package com.matariky.jobs.jobsService.assetitm.inventory.job;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.jobs.jobsService.assetitm.inventory.service.TapeInventoryTaskJobService;
import org.mockito.Mock;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.JobKey;

@SpringBootTest
public class TapeInventoryResultJobTest {

    @InjectMocks
    private TapeInventoryResultJob tapeInventoryResultJob;

    @Mock
    private TapeInventoryTaskJobService tapeInventoryTaskJobService;

    @Mock
    private JobExecutionContext jobExecutionContext;

    @Mock
    private Trigger trigger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteSuccess() throws JobExecutionException {
        // Given
        when(jobExecutionContext.getTrigger()).thenReturn(trigger);
        when(trigger.getJobKey()).thenReturn(new JobKey("testGroup", "testName"));
        doNothing().when(tapeInventoryTaskJobService).end(anyLong());

        // When
        tapeInventoryResultJob.execute(jobExecutionContext);

        // Then
        verify(tapeInventoryTaskJobService, times(1)).end(anyLong());
    }

    @Test
    void testExecuteException() throws JobExecutionException {
        // Given
        when(jobExecutionContext.getTrigger()).thenReturn(trigger);
        when(trigger.getJobKey()).thenReturn(new JobKey("testGroup", "testName"));
        doThrow(new RuntimeException("Test Exception")).when(tapeInventoryTaskJobService).end(anyLong());

        // When
        tapeInventoryResultJob.execute(jobExecutionContext);

        // Then
        verify(tapeInventoryTaskJobService, times(1)).end(anyLong());
    }

    @Test
    void testExecuteWithInvalidJobKey() throws JobExecutionException {
        // Given
        when(jobExecutionContext.getTrigger()).thenReturn(trigger);
        when(trigger.getJobKey()).thenReturn(new JobKey("invalidGroup", "testName"));

        // When
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            tapeInventoryResultJob.execute(jobExecutionContext);
        });

        // Then
        assertNotNull(exception);
    }

    @Test
    void testExecuteWithNullTrigger() throws JobExecutionException {
        // Given
        when(jobExecutionContext.getTrigger()).thenReturn(null);

        // When
        Exception exception = assertThrows(NullPointerException.class, () -> {
            tapeInventoryResultJob.execute(jobExecutionContext);
        });

        // Then
        assertNotNull(exception);
    }

    @Test
    void testExecuteWithLongRunningTask() throws JobExecutionException {
        // Given
        when(jobExecutionContext.getTrigger()).thenReturn(trigger);
        when(trigger.getJobKey()).thenReturn(new JobKey("testGroup", "testName"));
        doAnswer(invocation -> {
            Thread.sleep(2000);
            return null;
        }).when(tapeInventoryTaskJobService).end(anyLong());

        // When
        tapeInventoryResultJob.execute(jobExecutionContext);

        // Then
        verify(tapeInventoryTaskJobService, times(1)).end(anyLong());
    }
}
