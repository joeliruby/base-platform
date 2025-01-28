package com.matariky.jobs.jobsService.bean.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DeviceUpgradeFormTest {

    @InjectMocks
    private DeviceUpgradeForm deviceUpgradeForm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPackageId() {
        // Given
        Long expectedPackageId = 123L;
        deviceUpgradeForm.setPackageId(expectedPackageId);

        // When
        Long actualPackageId = deviceUpgradeForm.getPackageId();

        // Then
        assertThat(actualPackageId).isEqualTo(expectedPackageId);
    }

    @Test
    void testCronExpression() {
        // Given
        String expectedCronExpression = "0 0 12 * * ?";
        deviceUpgradeForm.setCronExpression(expectedCronExpression);

        // When
        String actualCronExpression = deviceUpgradeForm.getCronExpression();

        // Then
        assertThat(actualCronExpression).isEqualTo(expectedCronExpression);
    }

    @Test
    void testJobClassName() {
        // Given
        String expectedJobClassName = "com.example.JobClass";
        deviceUpgradeForm.setJobClassName(expectedJobClassName);

        // When
        String actualJobClassName = deviceUpgradeForm.getJobClassName();

        // Then
        assertThat(actualJobClassName).isEqualTo(expectedJobClassName);
    }

    @Test
    void testJobGroupName() {
        // Given
        String expectedJobGroupName = "JobGroup";
        deviceUpgradeForm.setJobGroupName(expectedJobGroupName);

        // When
        String actualJobGroupName = deviceUpgradeForm.getJobGroupName();

        // Then
        assertThat(actualJobGroupName).isEqualTo(expectedJobGroupName);
    }

    @Test
    void testDeviceId() {
        // Given
        Long expectedDeviceId = 456L;
        deviceUpgradeForm.setDeviceId(expectedDeviceId);

        // When
        Long actualDeviceId = deviceUpgradeForm.getDeviceId();

        // Then
        assertThat(actualDeviceId).isEqualTo(expectedDeviceId);
    }

    @Test
    void testUpgradeTime() {
        // Given
        Long expectedUpgradeTime = 789L;
        deviceUpgradeForm.setUpgradeTime(expectedUpgradeTime);

        // When
        Long actualUpgradeTime = deviceUpgradeForm.getUpgradeTime();

        // Then
        assertThat(actualUpgradeTime).isEqualTo(expectedUpgradeTime);
    }
}
