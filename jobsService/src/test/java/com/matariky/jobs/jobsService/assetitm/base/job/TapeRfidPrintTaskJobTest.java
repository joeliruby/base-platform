package com.matariky.jobs.jobsService.assetitm.base.job;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.matariky.jobs.jobsService.assetitm.base.service.TapeRfidPrintTaskJobService;

@SpringBootTest
public class TapeRfidPrintTaskJobTest {

    @InjectMocks
    private TapeRfidPrintTaskJob taperfidprinttaskjob;

    @Mock
    private TapeRfidPrintTaskJobService tapeRfidPrintTaskJobService;

    @Mock
    private JobExecutionContext jobExecutionContext;

    @Mock
    private Trigger trigger;

    private static final Logger logger = LoggerFactory.getLogger(TapeRfidPrintTaskJob.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(jobExecutionContext.getTrigger()).thenReturn(trigger);
    }

    @Test
    void testExecuteSuccess() throws JobExecutionException {
        // Given
        long taskId = 123L;
        when(trigger.getJobKey().getGroup()).thenReturn(String.valueOf(taskId));

        // When
        taperfidprinttaskjob.execute(jobExecutionContext);

        // Then
        try {
            verify(tapeRfidPrintTaskJobService, times(1)).start(taskId);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
        verify(logger).info("=============== Start implement Label  Print  Task taskId={}==============", taskId);
        verify(logger).info(
                "=============== Label  Print  Task taskId={}When the execution is completed and shared{}Second==============",
                eq(taskId), anyLong());
    }

    @Test
    void testExecuteException() throws Exception {
        // Given
        long taskId = 123L;
        when(trigger.getJobKey().getGroup()).thenReturn(String.valueOf(taskId));
        doThrow(new RuntimeException("Test Exception")).when(tapeRfidPrintTaskJobService).start(taskId);

        // When
        try {
            taperfidprinttaskjob.execute(jobExecutionContext);
        } catch (Exception e) {
            // Handle the exception
        }

        // Then
        verify(tapeRfidPrintTaskJobService, times(1)).start(taskId);
        verify(logger).error(eq(" Label  Print  Task abnormal,taskId={}"), eq(taskId), any(RuntimeException.class));
    }

    // Add more test methods for other scenarios in TapeRfidPrintTaskJob
}
