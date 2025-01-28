package com.matariky.jobs.jobsService.bean.form;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RfidCreateJobFormTest {

    @InjectMocks
    private RfidCreateJobForm rfidCreateJobForm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTaskId() {
        Long taskId = 123L;
        rfidCreateJobForm.setTaskId(taskId);
        assertEquals(taskId, rfidCreateJobForm.getTaskId());
    }

    @Test
    void testStartTime() {
        Long startTime = 456L;
        rfidCreateJobForm.setStartTime(startTime);
        assertEquals(startTime, rfidCreateJobForm.getStartTime());
    }

    @Test
    void testTaskType() {
        Integer taskType = 2;
        rfidCreateJobForm.setTaskType(taskType);
        assertEquals(taskType, rfidCreateJobForm.getTaskType());
    }

}
