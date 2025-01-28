package com.matariky.jobs.jobsService.assetitm.base.job;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.quartz.JobExecutionContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class OrderExpireJobTest {

    @InjectMocks
    private OrderExpireJob orderExpireJob;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private JobExecutionContext jobExecutionContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() throws Exception {
        // Given
        when(jdbcTemplate.update(anyString())).thenReturn(1);

        // When
        orderExpireJob.execute(jobExecutionContext);

        // Then
        verify(jdbcTemplate, times(1)).update(anyString());
    }

    @Test
    void testExecuteNoUpdates() throws Exception {
        // Given
        when(jdbcTemplate.update(anyString())).thenReturn(0);

        // When
        orderExpireJob.execute(jobExecutionContext);

        // Then
        verify(jdbcTemplate, times(1)).update(anyString());
    }

    // Add more test methods for other scenarios in OrderExpireJob
}
