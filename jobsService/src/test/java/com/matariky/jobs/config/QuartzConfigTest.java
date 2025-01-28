package com.matariky.jobs.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

@SpringBootTest
public class QuartzConfigTest {

    @InjectMocks
    private QuartzConfig quartzConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetScheduler() throws SchedulerException {
        // Given
        // No additional setup required

        // When
        Scheduler scheduler = quartzConfig.getScheduler();

        // Then
        assertThat(scheduler).isNotNull();
        assertThat(scheduler.isStarted()).isFalse();
    }

    // Add more test methods for other methods in QuartzConfig if needed
}
