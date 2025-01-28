package com.matariky.bizservice.job;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.matariky.jobs.jobsService.bean.form.*;
import com.matariky.utils.JacksonUtils;
import org.mockito.Mock;

@SpringBootTest
public class JobApiServiceTest {

    @InjectMocks
    private JobApiService jobApiService;

    @Mock
    private HttpRequest httpRequest;

    @Mock
    private HttpResponse httpResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddJob() {
        // Given
        InventoryJobForm inventoryJobForm = new InventoryJobForm();
        String tenantId = "tenant1";
        String sourceId = "source1";
        String token = "token1";
        String responseBody = "{\"success\":true}";
        when(httpRequest.execute()).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn(responseBody);

        // When
        jobApiService.addJob(inventoryJobForm, tenantId, sourceId, token);

        // Then
        verify(httpRequest).method(Method.POST);
        verify(httpRequest).header("Authorization", "Bearer " + token);
        verify(httpRequest).header("Id", sourceId);
        verify(httpRequest).body(JacksonUtils.toJsonString(inventoryJobForm));
    }

    @Test
    void testAddRfidJob() {
        // Given
        RfidCreateJobForm rfidJobForm = new RfidCreateJobForm();
        String tenantId = "tenant1";
        String sourceId = "source1";
        String token = "token1";
        String responseBody = "{\"success\":true}";
        when(httpRequest.execute()).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn(responseBody);

        // When
        jobApiService.addRfidJob(rfidJobForm, tenantId, sourceId, token);

        // Then
        verify(httpRequest).method(Method.POST);
        verify(httpRequest).header("Authorization", "Bearer " + token);
        verify(httpRequest).header("Id", sourceId);
        verify(httpRequest).body(JacksonUtils.toJsonString(rfidJobForm));
    }

    @Test
    void testDeleteJob() {
        // Given
        JobForm jobForm = new JobForm();
        jobForm.setJobClassName("jobClass");
        jobForm.setJobGroupName("jobGroup");
        String tenantId = "tenant1";
        String sourceId = "source1";
        String token = "token1";
        String responseBody = "{\"success\":true}";
        when(httpRequest.execute()).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn(responseBody);

        // When
        jobApiService.deleteJob(jobForm, tenantId, sourceId, token);

        // Then
        verify(httpRequest).method(Method.DELETE);
        verify(httpRequest).header("Authorization", "Bearer " + token);
        verify(httpRequest).header("Id", sourceId);
        verify(httpRequest).body(JacksonUtils.toJsonString(jobForm));
    }

    // Add more test methods for other methods in JobApiService
}
