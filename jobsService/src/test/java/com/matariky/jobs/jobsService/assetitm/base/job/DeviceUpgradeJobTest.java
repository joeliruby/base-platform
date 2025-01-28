package com.matariky.jobs.jobsService.assetitm.base.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.bean.BasicBaseDevice;
import com.matariky.commonservice.base.bean.BasicBaseDevicePackage;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDevicePackageMapper;
import com.matariky.commonservice.base.service.CommonService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;

@SpringBootTest
public class DeviceUpgradeJobTest {

    @InjectMocks
    private DeviceUpgradeJob deviceUpgradeJob;

    @Mock
    private BasicBaseDevicePackageMapper basicBaseDevicePackageMapper;

    @Mock
    private BasicBaseDeviceMapper basicBaseDeviceMapper;

    @Mock
    private CommonService commonService;

    @Mock
    private JobExecutionContext jobExecutionContext;

    @Mock
    private JobDetail jobDetail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(jobExecutionContext.getJobDetail()).thenReturn(jobDetail);
    }

    @Test
    void testExecuteWithValidData() throws JobExecutionException {
        // Given
        Long packageId = 1L;
        Long deviceId = 1L;
        BasicBaseDevicePackage devicePackage = new BasicBaseDevicePackage();
        devicePackage.setMd5("md5");
        devicePackage.setPackageVersion("1.0");
        BasicBaseDevice device = new BasicBaseDevice();
        device.setDeviceCode("deviceCode");

        when(jobDetail.getJobDataMap().get("packageId")).thenReturn(packageId);
        when(jobDetail.getJobDataMap().get("deviceId")).thenReturn(deviceId);
        when(basicBaseDevicePackageMapper.selectById(packageId)).thenReturn(devicePackage);
        when(basicBaseDeviceMapper.selectById(deviceId)).thenReturn(device);

        // When
        deviceUpgradeJob.execute(jobExecutionContext);

        // Then
        verify(commonService).deviceUpgrade("md5", "1.0", "deviceCode");
    }

    @Test
    void testExecuteWithInvalidPackageId() {
        // Given
        Long packageId = 1L;
        Long deviceId = 1L;

        when(jobDetail.getJobDataMap().get("packageId")).thenReturn(packageId);
        when(jobDetail.getJobDataMap().get("deviceId")).thenReturn(deviceId);
        when(basicBaseDevicePackageMapper.selectById(packageId)).thenReturn(null);

        // When & Then
        QslException exception = assertThrows(QslException.class, () -> {
            deviceUpgradeJob.execute(jobExecutionContext);
        });
        assertEquals(MessageKey.DEVICE_PACKAGE_NOT_EXIST, exception.getMessage());
    }

    @Test
    void testExecuteWithInvalidDeviceId() throws JobExecutionException {
        // Given
        Long packageId = 1L;
        Long deviceId = 1L;
        BasicBaseDevicePackage devicePackage = new BasicBaseDevicePackage();

        when(jobDetail.getJobDataMap().get("packageId")).thenReturn(packageId);
        when(jobDetail.getJobDataMap().get("deviceId")).thenReturn(deviceId);
        when(basicBaseDevicePackageMapper.selectById(packageId)).thenReturn(devicePackage);
        when(basicBaseDeviceMapper.selectById(deviceId)).thenReturn(null);

        // When
        deviceUpgradeJob.execute(jobExecutionContext);

        // Then
        verify(commonService, never()).deviceUpgrade(anyString(), anyString(), anyString());
    }
}
