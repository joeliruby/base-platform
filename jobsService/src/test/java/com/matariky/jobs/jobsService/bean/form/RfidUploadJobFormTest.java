package com.matariky.jobs.jobsService.bean.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.vo.TapeRfidCreateCNExeclReqVo;

@SpringBootTest
public class RfidUploadJobFormTest {

    @InjectMocks
    private RfidUploadJobForm rfidUploadJobForm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTaskId() {
        Long taskId = 123L;
        rfidUploadJobForm.setTaskId(taskId);
        assertEquals(taskId, rfidUploadJobForm.getTaskId());
    }

    @Test
    void testStartTime() {
        Long startTime = 456L;
        rfidUploadJobForm.setStartTime(startTime);
        assertEquals(startTime, rfidUploadJobForm.getStartTime());
    }

    @Test
    void testTaskType() {
        Integer taskType = 1;
        rfidUploadJobForm.setTaskType(taskType);
        assertEquals(taskType, rfidUploadJobForm.getTaskType());
    }

    @Test
    void testTapeRfidCreateCNExeclReqVos() {
        List<TapeRfidCreateCNExeclReqVo> tapeRfidCreateCNExeclReqVos = new ArrayList<>();
        rfidUploadJobForm.setTapeRfidCreateCNExeclReqVos(tapeRfidCreateCNExeclReqVos);
        assertEquals(tapeRfidCreateCNExeclReqVos, rfidUploadJobForm.getTapeRfidCreateCNExeclReqVos());
    }

    // Add more test methods for other methods in RfidUploadJobForm
}
