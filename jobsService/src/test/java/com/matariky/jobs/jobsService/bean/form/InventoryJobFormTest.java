package com.matariky.jobs.jobsService.bean.form;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InventoryJobFormTest {

    @InjectMocks
    private InventoryJobForm inventoryjobform;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTaskId() {
        inventoryjobform.setTaskId(123L);
        assertEquals(123L, inventoryjobform.getTaskId());
    }

    @Test
    void testStartTime() {
        inventoryjobform.setStartTime(1609459200000L); // January 1, 2021 00:00:00 GMT
        assertEquals(1609459200000L, inventoryjobform.getStartTime());
    }

    @Test
    void testEndTime() {
        inventoryjobform.setEndTime(1609545600000L); // January 2, 2021 00:00:00 GMT
        assertEquals(1609545600000L, inventoryjobform.getEndTime());
    }

    @Test
    void testTaskType() {
        inventoryjobform.setTaskType(1);
        assertEquals(1, inventoryjobform.getTaskType());
    }

    @Test
    void testTimeInterval() {
        inventoryjobform.setTimeInterval(30);
        assertEquals(30, inventoryjobform.getTimeInterval());
    }

    @Test
    void testIntervalUnit() {
        inventoryjobform.setIntervalUnit(2);
        assertEquals(2, inventoryjobform.getIntervalUnit());
    }
}
