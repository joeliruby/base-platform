package com.matariky.jobs.jobsService.assetitm.base.job;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.jobs.jobsService.assetitm.base.service.TapeRfidCreateTaskJobService;

@SpringBootTest
public class TapeRfidCreateTaskJobTest {

    @InjectMocks
    private TapeRfidCreateTaskJob tapeRfidCreateTaskJob;

    @Mock
    private TapeRfidCreateTaskJobService tapeRfidCreateTaskJobService;

    @Mock
    private JobExecutionContext jobExecutionContext;

    @Mock
    private Trigger trigger;

    @Mock
    private Logger logger;

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
        try {
            tapeRfidCreateTaskJob.execute(jobExecutionContext);
        } catch (Exception e) {
            // Handle the exception
        }

        // Then
        try {
            try {
                verify(tapeRfidCreateTaskJobService, times(1)).start(1L);
            } catch (Exception e) {
                // Handle the exception
            }
        } catch (Exception e) {
            // Handle the exception
        }
    }

    @Test
    void testExecuteException() throws Exception {
        // Given
        when(trigger.getJobKey().getGroup()).thenReturn("1");
        try {
            doThrow(new RuntimeException("Test Exception")).when(tapeRfidCreateTaskJobService).start(1L);
        } catch (Exception e) {
            // Handle the exception
        }

        // When
        tapeRfidCreateTaskJob.execute(jobExecutionContext);

        // Then
        verify(tapeRfidCreateTaskJobService, times(1)).start(1L);
        verify(logger, times(1)).error(anyString(), eq(1L), any(RuntimeException.class));
    }

    // Add more test methods for other scenarios in TapeRfidCreateTaskJob
}
