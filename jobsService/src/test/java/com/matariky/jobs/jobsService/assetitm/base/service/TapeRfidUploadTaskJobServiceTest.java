package com.matariky.jobs.jobsService.assetitm.base.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseCodingRules;
import com.matariky.commonservice.base.bean.BasicBaseGoods;
import com.matariky.commonservice.base.mapper.BasicBaseCodingRulesMapper;
import com.matariky.commonservice.base.mapper.BasicBaseCreaterfidFactoryMapper;
import com.matariky.commonservice.base.mapper.BasicBaseGoodsMapper;
import com.matariky.commonservice.base.mapper.BasicBaseRfidInfoMapper;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidCreateTask;
import com.matariky.jobs.jobsService.assetitm.base.mapper.JobRfidCreateTaskMapper;
import org.mockito.Mock;
import java.util.Collections;

@SpringBootTest
public class TapeRfidUploadTaskJobServiceTest {

    @InjectMocks
    private TapeRfidUploadTaskJobService tapeRfidUploadTaskJobService;

    @Mock
    private MinioUtil minioUtil;

    @Mock
    private BasicBaseCodingRulesMapper basicBaseCodingRulesMapper;

    @Mock
    private JobRfidCreateTaskMapper jobRfidCreateTaskMapper;

    @Mock
    private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

    @Mock
    private BasicBaseCreaterfidFactoryMapper baseCreaterfidFactoryMapper;

    @Mock
    private BasicBaseGoodsMapper basicBaseGoodsMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStartWithValidTask() throws Exception {
        // Given
        Long taskId = 1L;
        TapeRfidCreateTask task = new TapeRfidCreateTask();
        task.setId(taskId);
        task.setOdFixedContent("odContent");
        task.setQrFixedContent("qrContent");
        task.setGoodsId(1L);
        task.setCreateNum(1);
        task.setIsLockEpc(1);
        task.setEpcPassword("0");
        task.setEpcRule("1");
        when(jobRfidCreateTaskMapper.getBasicBaseRfidfactoryById(taskId)).thenReturn(task);
        when(basicBaseGoodsMapper.selectById(task.getGoodsId())).thenReturn(new BasicBaseGoods());
        when(jobRfidCreateTaskMapper.getBasicBaseRfidfactoryParameterById(taskId)).thenReturn(Collections.emptyList());
        when(basicBaseCodingRulesMapper.selectById(anyLong())).thenReturn(new BasicBaseCodingRules());

        // When
        tapeRfidUploadTaskJobService.start(taskId);

        // Then
        verify(jobRfidCreateTaskMapper, times(1)).updateBasicBaseRfidfactory(any(TapeRfidCreateTask.class));
    }

    @Test
    void testStartWithInvalidTask() throws Exception {
        // Given
        Long taskId = 1L;
        when(jobRfidCreateTaskMapper.getBasicBaseRfidfactoryById(taskId)).thenReturn(null);

        // When
        tapeRfidUploadTaskJobService.start(taskId);

        // Then
        verify(jobRfidCreateTaskMapper, never()).updateBasicBaseRfidfactory(any(TapeRfidCreateTask.class));
    }

    @Test
    void testStartWithException() {
        // Given
        Long taskId = 1L;
        when(jobRfidCreateTaskMapper.getBasicBaseRfidfactoryById(taskId))
                .thenThrow(new RuntimeException("Database error"));

        // When & Then
        assertThrows(Exception.class, () -> tapeRfidUploadTaskJobService.start(taskId));
    }

    // Add more test methods for other scenarios in TapeRfidUploadTaskJobService
}
