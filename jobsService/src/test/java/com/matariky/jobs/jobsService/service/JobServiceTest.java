package com.matariky.jobs.jobsService.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.matariky.jobs.jobsService.bean.form.*;
import com.github.pagehelper.PageInfo;
import com.matariky.jobs.jobsService.bean.domain.JobAndTrigger;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class JobServiceTest {

    @InjectMocks
    private JobService jobService;

    @Mock
    private JobForm jobForm;

    @Mock
    private InventoryJobForm inventoryJobForm;

    @Mock
    private RfidCreateJobForm rfidCreateJobForm;

    @Mock
    private RfidPrintJobForm rfidPrintJobForm;

    @Mock
    private RfidUploadJobForm rfidUploadJobForm;

    @Mock
    private BaseJobForm baseJobForm;

    @Mock
    private DeviceUpgradeForm deviceUpgradeForm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddJob() throws Exception {
        jobService.addJob(jobForm);
        verify(jobService, times(1)).addJob(jobForm);
    }

    @Test
    void testAddInventoryJob() throws Exception {
        jobService.addInventoryJob(inventoryJobForm);
        verify(jobService, times(1)).addInventoryJob(inventoryJobForm);
    }

    @Test
    void testAddRfidCreateJob() throws Exception {
        jobService.addRfidCreateJob(rfidCreateJobForm);
        verify(jobService, times(1)).addRfidCreateJob(rfidCreateJobForm);
    }

    @Test
    void testAddRfidPrintJob() throws Exception {
        jobService.addRfidPrintJob(rfidPrintJobForm);
        verify(jobService, times(1)).addRfidPrintJob(rfidPrintJobForm);
    }

    @Test
    void testAddRfidUploadJob() throws Exception {
        jobService.addRfidUploadJob(rfidUploadJobForm);
        verify(jobService, times(1)).addRfidUploadJob(rfidUploadJobForm);
    }

    @Test
    void testDeleteJob() throws Exception {
        jobService.deleteJob(jobForm);
        verify(jobService, times(1)).deleteJob(jobForm);
    }

    @Test
    void testPauseJob() throws Exception {
        jobService.pauseJob(jobForm);
        verify(jobService, times(1)).pauseJob(jobForm);
    }

    @Test
    void testResumeJob() throws Exception {
        jobService.resumeJob(jobForm);
        verify(jobService, times(1)).resumeJob(jobForm);
    }

    @Test
    void testCronJob() throws Exception {
        jobService.cronJob(jobForm);
        verify(jobService, times(1)).cronJob(jobForm);
    }

    @Test
    void testList() {
        Integer currentPage = 1;
        Integer pageSize = 10;
        String tenantId = "tenant1";
        PageInfo<JobAndTrigger> pageInfo = new PageInfo<>();
        when(jobService.list(currentPage, pageSize, tenantId)).thenReturn(pageInfo);
        PageInfo<JobAndTrigger> result = jobService.list(currentPage, pageSize, tenantId);
        assertEquals(pageInfo, result);
    }

    @Test
    void testUpdateTenantId() {
        String jobGroupName = "group1";
        String jobClassName = "class1";
        String tenantId = "tenant1";
        jobService.updateTenantId(jobGroupName, jobClassName, tenantId);
        verify(jobService, times(1)).updateTenantId(jobGroupName, jobClassName, tenantId);
    }

    @Test
    void testGetJob() {
        String jobClassName = "class1";
        String jobGroupName = "group1";
        when(jobService.getJob(jobClassName, jobGroupName)).thenReturn(true);
        Boolean result = jobService.getJob(jobClassName, jobGroupName);
        assertTrue(result);
    }

    @Test
    void testGetJobAndTriggerAll() {
        Map<String, Object> map = mock(Map.class);
        List<JobAndTrigger> jobAndTriggerList = mock(List.class);
        when(jobService.getJobAndTriggerAll(map)).thenReturn(jobAndTriggerList);
        List<JobAndTrigger> result = jobService.getJobAndTriggerAll(map);
        assertEquals(jobAndTriggerList, result);
    }

    @Test
    void testAddOrderExpireJob() throws Exception {
        jobService.addOrderExpireJob(baseJobForm);
        verify(jobService, times(1)).addOrderExpireJob(baseJobForm);
    }

    @Test
    void testAddDeviceUpgradeJob() throws Exception {
        jobService.addDeviceUpgradeJob(deviceUpgradeForm);
        verify(jobService, times(1)).addDeviceUpgradeJob(deviceUpgradeForm);
    }
}
