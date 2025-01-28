package com.matariky.jobs.jobsService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.jobs.jobsService.assetitm.inventory.job.TapeInventoryTaskJob;
import com.matariky.jobs.jobsService.bean.form.InventoryJobForm;
import com.matariky.jobs.jobsService.bean.form.JobForm;
import com.matariky.jobs.jobsService.service.JobService;

@SpringBootTest
public class JobControllerTest {

    @InjectMocks
    private JobController jobController;

    @Mock
    private JobService jobService;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddJob_JobExists() {
        JobForm form = new JobForm();
        form.setJobClassName("TestJobClass");
        form.setJobGroupName("TestJobGroup");
        String tenantId = "tenant1";

        when(jobService.getJob(form.getJobClassName(), form.getJobGroupName())).thenReturn(true);

        ResponseEntity<?> response = (ResponseEntity<?>) jobController.addJob(form, tenantId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testAddJob_Success() throws Exception {
        JobForm form = new JobForm();
        form.setJobClassName("TestJobClass");
        form.setJobGroupName("TestJobGroup");
        String tenantId = "tenant1";

        when(jobService.getJob(form.getJobClassName(), form.getJobGroupName())).thenReturn(false);

        ResponseEntity<?> response = (ResponseEntity<?>) jobController.addJob(form, tenantId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testAddInventoryJob_JobExists() {
        InventoryJobForm form = new InventoryJobForm();
        form.setTaskId(1L);
        String tenantId = "tenant1";

        when(jobService.getJob(TapeInventoryTaskJob.class.getName(), form.getTaskId().toString())).thenReturn(true);

        ResponseEntity<?> response = (ResponseEntity<?>) jobController.addInventoryJob(form, tenantId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testAddInventoryJob_Success() throws Exception {
        InventoryJobForm form = new InventoryJobForm();
        form.setTaskId(1L);
        String tenantId = "tenant1";

        when(jobService.getJob(TapeInventoryTaskJob.class.getName(), form.getTaskId().toString())).thenReturn(false);

        ResponseEntity<?> response = (ResponseEntity<?>) jobController.addInventoryJob(form, tenantId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testDeleteJob_Success() throws Exception {
        JobForm form = new JobForm();
        form.setJobClassName("TestJobClass");
        form.setJobGroupName("TestJobGroup");
        String tenantId = "tenant1";

        ResponseEntity<?> response = jobController.deleteJob(form, tenantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testPauseJob_Success() throws Exception {
        JobForm form = new JobForm();
        form.setJobClassName("TestJobClass");
        form.setJobGroupName("TestJobGroup");
        String tenantId = "tenant1";

        ResponseEntity<?> response = jobController.pauseJob(form, tenantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testResumeJob_Success() throws Exception {
        JobForm form = new JobForm();
        form.setJobClassName("TestJobClass");
        form.setJobGroupName("TestJobGroup");
        String tenantId = "tenant1";

        ResponseEntity<?> response = jobController.resumeJob(form, tenantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCronJob_Success() throws Exception {
        JobForm form = new JobForm();
        form.setJobClassName("TestJobClass");
        form.setJobGroupName("TestJobGroup");
        String tenantId = "tenant1";

        ResponseEntity<?> response = jobController.cronJob(form, tenantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // Add more test methods for other methods in JobController
}
