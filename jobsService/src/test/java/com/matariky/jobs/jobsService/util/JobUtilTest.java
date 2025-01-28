package com.matariky.jobs.jobsService.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.jobs.jobsService.job.base.BaseJob;

@SpringBootTest
public class JobUtilTest {

    @InjectMocks
    private JobUtil jobutil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetClass_ValidClassName() throws Exception {
        String validClassName = "com.matariky.jobs.jobsService.job.SomeJob";
        BaseJob job = JobUtil.getClass(validClassName);
        assertNotNull(job);
        assertEquals(validClassName, job.getClass().getName());
    }

    @Test
    void testGetClass_InvalidClassName() {
        String invalidClassName = "com.matariky.jobs.jobsService.job.NonExistentJob";
        assertThrows(ClassNotFoundException.class, () -> {
            JobUtil.getClass(invalidClassName);
        });
    }

    @Test
    void testGetClass_NullClassName() {
        assertThrows(NullPointerException.class, () -> {
            JobUtil.getClass(null);
        });
    }

    // Add more test methods for other methods in JobUtil
}
