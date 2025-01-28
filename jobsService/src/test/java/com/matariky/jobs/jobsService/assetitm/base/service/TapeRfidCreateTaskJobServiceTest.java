package com.matariky.jobs.jobsService.assetitm.base.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.bean.BasicBaseGoods;
import com.matariky.commonservice.base.mapper.BasicBaseGoodsMapper;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidCreateTask;
import com.matariky.jobs.jobsService.assetitm.base.mapper.JobRfidCreateTaskMapper;

@SpringBootTest
public class TapeRfidCreateTaskJobServiceTest {
    @InjectMocks
    private TapeRfidCreateTaskJobService taperfidcreatetaskjobservice;

    @Mock
    private JobRfidCreateTaskMapper jobRfidCreateTaskMapper;

    @Mock
    private BasicBaseGoodsMapper basicBaseGoodsMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStartTaskWithValidData() throws Exception {
        // Given
        Long taskId = 1L;
        TapeRfidCreateTask task = mock(TapeRfidCreateTask.class);
        when(jobRfidCreateTaskMapper.getBasicBaseRfidfactoryById(taskId)).thenReturn(task);
        when(task.getOdFixedContent()).thenReturn("odContent");
        when(task.getQrFixedContent()).thenReturn("qrContent");
        when(task.getGoodsId()).thenReturn(1L);
        when(basicBaseGoodsMapper.selectById(1L)).thenReturn(mock(BasicBaseGoods.class));
        when(jobRfidCreateTaskMapper.getBasicBaseRfidfactoryParameterById(taskId)).thenReturn(new ArrayList<>());

        // When
        taperfidcreatetaskjobservice.start(taskId);

        // Then
        verify(jobRfidCreateTaskMapper).updateBasicBaseRfidfactory(task);
    }

    @Test
    void testStartTaskWithNullTask() throws Exception {
        // Given
        Long taskId = 1L;
        when(jobRfidCreateTaskMapper.getBasicBaseRfidfactoryById(taskId)).thenReturn(null);

        // When
        taperfidcreatetaskjobservice.start(taskId);

        // Then
        verify(jobRfidCreateTaskMapper, never()).updateBasicBaseRfidfactory(any());
    }

    @Test
    void testStartTaskWithEmptyContents() throws Exception {
        // Given
        Long taskId = 1L;
        TapeRfidCreateTask task = mock(TapeRfidCreateTask.class);
        when(jobRfidCreateTaskMapper.getBasicBaseRfidfactoryById(taskId)).thenReturn(task);
        when(task.getOdFixedContent()).thenReturn("");
        when(task.getQrFixedContent()).thenReturn("");
        when(task.getGoodsId()).thenReturn(1L);
        when(basicBaseGoodsMapper.selectById(1L)).thenReturn(mock(BasicBaseGoods.class));
        when(jobRfidCreateTaskMapper.getBasicBaseRfidfactoryParameterById(taskId)).thenReturn(new ArrayList<>());

        // When
        taperfidcreatetaskjobservice.start(taskId);

        // Then
        verify(jobRfidCreateTaskMapper).updateBasicBaseRfidfactory(task);
    }

    @Test
    void testStartTaskWithException() {
        // Given
        Long taskId = 1L;
        when(jobRfidCreateTaskMapper.getBasicBaseRfidfactoryById(taskId))
                .thenThrow(new RuntimeException("Test Exception"));

        // When & Then
        assertThrows(Exception.class, () -> taperfidcreatetaskjobservice.start(taskId));
    }

    // Add more test methods for other scenarios in TapeRfidCreateTaskJobService
}
