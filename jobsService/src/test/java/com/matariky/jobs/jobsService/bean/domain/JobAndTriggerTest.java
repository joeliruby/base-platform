package com.matariky.jobs.jobsService.bean.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JobAndTriggerTest {

    @InjectMocks
    private JobAndTrigger jobandtrigger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testJobName() {
        // Given
        String jobName = "TestJob";
        jobandtrigger.setJobName(jobName);

        // When
        String result = jobandtrigger.getJobName();

        // Then
        assertThat(result).isEqualTo(jobName);
    }

    @Test
    void testJobGroup() {
        // Given
        String jobGroup = "TestGroup";
        jobandtrigger.setJobGroup(jobGroup);

        // When
        String result = jobandtrigger.getJobGroup();

        // Then
        assertThat(result).isEqualTo(jobGroup);
    }

    @Test
    void testJobClassName() {
        // Given
        String jobClassName = "com.example.TestJobClass";
        jobandtrigger.setJobClassName(jobClassName);

        // When
        String result = jobandtrigger.getJobClassName();

        // Then
        assertThat(result).isEqualTo(jobClassName);
    }

    @Test
    void testTriggerName() {
        // Given
        String triggerName = "TestTrigger";
        jobandtrigger.setTriggerName(triggerName);

        // When
        String result = jobandtrigger.getTriggerName();

        // Then
        assertThat(result).isEqualTo(triggerName);
    }

    @Test
    void testTriggerGroup() {
        // Given
        String triggerGroup = "TestTriggerGroup";
        jobandtrigger.setTriggerGroup(triggerGroup);

        // When
        String result = jobandtrigger.getTriggerGroup();

        // Then
        assertThat(result).isEqualTo(triggerGroup);
    }

    @Test
    void testRepeatInterval() {
        // Given
        BigInteger repeatInterval = BigInteger.valueOf(1000);
        jobandtrigger.setRepeatInterval(repeatInterval);

        // When
        BigInteger result = jobandtrigger.getRepeatInterval();

        // Then
        assertThat(result).isEqualTo(repeatInterval);
    }

    @Test
    void testTimesTriggered() {
        // Given
        BigInteger timesTriggered = BigInteger.valueOf(5);
        jobandtrigger.setTimesTriggered(timesTriggered);

        // When
        BigInteger result = jobandtrigger.getTimesTriggered();

        // Then
        assertThat(result).isEqualTo(timesTriggered);
    }

    @Test
    void testCronExpression() {
        // Given
        String cronExpression = "0 0 12 * * ?";
        jobandtrigger.setCronExpression(cronExpression);

        // When
        String result = jobandtrigger.getCronExpression();

        // Then
        assertThat(result).isEqualTo(cronExpression);
    }

    @Test
    void testTimeZoneId() {
        // Given
        String timeZoneId = "UTC";
        jobandtrigger.setTimeZoneId(timeZoneId);

        // When
        String result = jobandtrigger.getTimeZoneId();

        // Then
        assertThat(result).isEqualTo(timeZoneId);
    }

    @Test
    void testTriggerState() {
        // Given
        String triggerState = "ACTIVE";
        jobandtrigger.setTriggerState(triggerState);

        // When
        String result = jobandtrigger.getTriggerState();

        // Then
        assertThat(result).isEqualTo(triggerState);
    }
}
