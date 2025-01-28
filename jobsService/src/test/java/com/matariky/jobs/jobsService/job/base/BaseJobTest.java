package com.matariky.jobs.jobsService.job.base;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@SpringBootTest
public class BaseJobTest {

    @InjectMocks
    private BaseJob baseJob = new BaseJob() {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            // Implementation for testing
        }
    };

    @Mock
    private JobExecutionContext jobExecutionContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        assertDoesNotThrow(() -> baseJob.execute(jobExecutionContext));
    }

    // Add more test methods for other methods in BaseJob
}
