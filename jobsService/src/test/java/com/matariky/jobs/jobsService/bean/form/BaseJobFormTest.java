package com.matariky.jobs.jobsService.bean.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BaseJobFormTest {

    @InjectMocks
    private BaseJobForm basejobform;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTaskId() {
        // Given
        Long expectedTaskId = 123L;
        basejobform.setTaskId(expectedTaskId);

        // When
        Long actualTaskId = basejobform.getTaskId();

        // Then
        assertThat(actualTaskId).isEqualTo(expectedTaskId);
    }

    @Test
    void testGetStartTime() {
        // Given
        Long expectedStartTime = 456L;
        basejobform.setStartTime(expectedStartTime);

        // When
        Long actualStartTime = basejobform.getStartTime();

        // Then
        assertThat(actualStartTime).isEqualTo(expectedStartTime);
    }

    @Test
    void testGetTaskType() {
        // Given
        Integer expectedTaskType = 1;
        basejobform.setTaskType(expectedTaskType);

        // When
        Integer actualTaskType = basejobform.getTaskType();

        // Then
        assertThat(actualTaskType).isEqualTo(expectedTaskType);
    }

    @Test
    void testGetCronExpression() {
        // Given
        String expectedCronExpression = "0 0 12 * * ?";
        basejobform.setCronExpression(expectedCronExpression);

        // When
        String actualCronExpression = basejobform.getCronExpression();

        // Then
        assertThat(actualCronExpression).isEqualTo(expectedCronExpression);
    }

    @Test
    void testGetJobClassName() {
        // Given
        String expectedJobClassName = "com.example.JobClass";
        basejobform.setJobClassName(expectedJobClassName);

        // When
        String actualJobClassName = basejobform.getJobClassName();

        // Then
        assertThat(actualJobClassName).isEqualTo(expectedJobClassName);
    }

    @Test
    void testGetJobGroupName() {
        // Given
        String expectedJobGroupName = "JobGroup";
        basejobform.setJobGroupName(expectedJobGroupName);

        // When
        String actualJobGroupName = basejobform.getJobGroupName();

        // Then
        assertThat(actualJobGroupName).isEqualTo(expectedJobGroupName);
    }
}
