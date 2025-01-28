package com.matariky.jobs.jobsService.assetitm.inventory.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.jobs.jobsService.assetitm.inventory.bean.TapeInventoryTask;
import com.matariky.jobs.jobsService.assetitm.inventory.mapper.JobTapeInventoryTaskMapper;
import org.mockito.Mock;

@SpringBootTest
public class TapeInventoryTaskJobServiceTest {

    @InjectMocks
    private TapeInventoryTaskJobService tapeInventoryTaskJobService;

    @Mock
    private JobTapeInventoryTaskMapper tapeInventoryTaskMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStartTaskNotExist() throws Exception {
        Long taskId = 1L;
        when(tapeInventoryTaskMapper.selectOne(any())).thenReturn(null);

        tapeInventoryTaskJobService.start(taskId);

        verify(tapeInventoryTaskMapper, times(1)).selectOne(any());
    }

    @Test
    void testStartTaskEnded() throws Exception {
        Long taskId = 1L;
        TapeInventoryTask task = new TapeInventoryTask();
        task.setId(taskId);
        task.setProcessStatus(4);
        when(tapeInventoryTaskMapper.selectOne(any())).thenReturn(task);

        tapeInventoryTaskJobService.start(taskId);

        verify(tapeInventoryTaskMapper, times(1)).selectOne(any());
    }

    @Test
    void testStartTaskScanning() throws Exception {
        Long taskId = 1L;
        TapeInventoryTask task = new TapeInventoryTask();
        task.setId(taskId);
        task.setProcessStatus(2);
        when(tapeInventoryTaskMapper.selectOne(any())).thenReturn(task);

        tapeInventoryTaskJobService.start(taskId);

        verify(tapeInventoryTaskMapper, times(1)).selectOne(any());
    }

    @Test
    void testStartTaskDeactivated() throws Exception {
        Long taskId = 1L;
        TapeInventoryTask task = new TapeInventoryTask();
        task.setId(taskId);
        task.setStatus(2);
        when(tapeInventoryTaskMapper.selectOne(any())).thenReturn(task);

        tapeInventoryTaskJobService.start(taskId);

        verify(tapeInventoryTaskMapper, times(1)).selectOne(any());
    }

    // Add more test methods for other methods in TapeInventoryTaskJobService
}
