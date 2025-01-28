package com.matariky.jobs.jobsService.bean.form;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JobFormTest {

    @InjectMocks
    private JobForm jobform;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jobform = new JobForm();
    }

    @Test
    void testJobClassName() {
        jobform.setJobClassName("com.example.MyJobClass");
        assertEquals("com.example.MyJobClass", jobform.getJobClassName());
    }

    @Test
    void testJobGroupName() {
        jobform.setJobGroupName("MyJobGroup");
        assertEquals("MyJobGroup", jobform.getJobGroupName());
    }

    @Test
    void testCronExpression() {
        jobform.setCronExpression("0 0 12 * * ?");
        assertEquals("0 0 12 * * ?", jobform.getCronExpression());
    }

    @Test
    void testTenantId() {
        jobform.setTenantId("tenant123");
        assertEquals("tenant123", jobform.getTenantId());
    }

    @Test
    void testChainMethods() {
        jobform.setJobClassName("com.example.MyJobClass")
                .setJobGroupName("MyJobGroup")
                .setCronExpression("0 0 12 * * ?")
                .setTenantId("tenant123");

        assertAll(
                () -> assertEquals("com.example.MyJobClass", jobform.getJobClassName()),
                () -> assertEquals("MyJobGroup", jobform.getJobGroupName()),
                () -> assertEquals("0 0 12 * * ?", jobform.getCronExpression()),
                () -> assertEquals("tenant123", jobform.getTenantId()));
    }
}
