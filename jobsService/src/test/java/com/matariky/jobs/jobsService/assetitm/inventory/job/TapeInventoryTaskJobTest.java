package com.matariky.jobs.jobsService.assetitm.inventory.job;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.jobs.jobsService.assetitm.inventory.service.TapeInventoryTaskJobService;
import com.matariky.redis.redisson.Lock;
import com.matariky.redis.redisson.LockUtil;

@SpringBootTest
public class TapeInventoryTaskJobTest {

    @InjectMocks
    private TapeInventoryTaskJob tapeInventoryTaskJob;

    @Mock
    private TapeInventoryTaskJobService tapeInventoryTaskJobService;

    @Mock
    private JobExecutionContext jobExecutionContext;

    @Mock
    private Trigger trigger;

    @Mock
    private Lock lock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(jobExecutionContext.getTrigger()).thenReturn(trigger);
        when(trigger.getJobKey()).thenReturn(new JobKey("group", "name"));
        when(LockUtil.lock(anyString())).thenReturn(lock);
    }

    @Test
    void testExecuteSuccess() throws JobExecutionException {
        // Given
        when(trigger.getJobKey().getGroup()).thenReturn("123");

        // When
        try {
            tapeInventoryTaskJob.execute(jobExecutionContext);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }

        // Then
        try {
            verify(tapeInventoryTaskJobService, times(1)).start(123L);
            verify(lock, times(1)).unlock();
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    void testExecuteWithException() throws Exception {
        // Given
        when(trigger.getJobKey().getGroup()).thenReturn("123");
        doThrow(new RuntimeException("Test Exception")).when(tapeInventoryTaskJobService).start(123L);

        // When
        try {
            tapeInventoryTaskJob.execute(jobExecutionContext);
        } catch (Exception e) {
            // Handle exception if needed
        }

        // Then
        verify(tapeInventoryTaskJobService, times(1)).start(123L);
        verify(lock, times(1)).unlock();
    }

    @Test
    void testExecuteInvalidGroup() {
        // Given
        when(trigger.getJobKey().getGroup()).thenReturn("invalid");

        // When / Then
        assertThrows(NumberFormatException.class, () -> tapeInventoryTaskJob.execute(jobExecutionContext));
    }
}
