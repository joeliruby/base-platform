package com.matariky.jobs.jobsService.assetitm.base.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseCodingRules;
import com.matariky.commonservice.base.mapper.BasicBaseCodingRulesMapper;
import com.matariky.commonservice.base.mapper.BasicBaseCreaterfidPrintMapper;
import com.matariky.commonservice.base.mapper.BasicBaseRfidInfoMapper;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidPrintTask;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidPrintParameterTask;
import com.matariky.jobs.jobsService.assetitm.base.mapper.JobRfidPrintTaskMapper;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class TapeRfidPrintTaskJobServiceTest {

    @InjectMocks
    private TapeRfidPrintTaskJobService taperfidprinttaskjobservice;

    @Mock
    private BasicBaseCodingRulesMapper basicBaseCodingrulesMapper;

    @Mock
    private JobRfidPrintTaskMapper jobRfidPrintTaskMapper;

    @Mock
    private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

    @Mock
    private BasicBaseCreaterfidPrintMapper basicBaseCreaterfidPrintMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStartTaskWithValidData() throws Exception {
        // Given
        Long taskId = 1L;
        TapeRfidPrintTask task = new TapeRfidPrintTask();
        task.setId(taskId);
        task.setOdFixedContent("odContent");
        task.setQrFixedContent("qrContent");
        task.setPrintNum(1);
        task.setIsLockEpc(1);
        task.setEpcPassword("0");
        task.setEpcRule("1");

        when(jobRfidPrintTaskMapper.getBasicBaseRfidPrintById(taskId)).thenReturn(task);
        when(jobRfidPrintTaskMapper.getBasicBaseRfidPrintParameterById(taskId)).thenReturn(Collections.emptyList());
        when(basicBaseCodingrulesMapper.selectById(anyLong())).thenReturn(new BasicBaseCodingRules());

        // When
        taperfidprinttaskjobservice.start(taskId);

        // Then
        verify(jobRfidPrintTaskMapper, times(1)).getBasicBaseRfidPrintById(taskId);
        verify(jobRfidPrintTaskMapper, times(1)).getBasicBaseRfidPrintParameterById(taskId);
    }

    @Test
    void testStartTaskWithNoTaskFound() throws Exception {
        // Given
        Long taskId = 1L;
        when(jobRfidPrintTaskMapper.getBasicBaseRfidPrintById(taskId)).thenReturn(null);

        // When
        taperfidprinttaskjobservice.start(taskId);

        // Then
        verify(jobRfidPrintTaskMapper, times(1)).getBasicBaseRfidPrintById(taskId);
        verify(jobRfidPrintTaskMapper, never()).getBasicBaseRfidPrintParameterById(taskId);
    }

    @Test
    void testStartTaskWithParameters() throws Exception {
        // Given
        Long taskId = 1L;
        TapeRfidPrintTask task = new TapeRfidPrintTask();
        task.setId(taskId);
        task.setOdFixedContent("odContent");
        task.setQrFixedContent("qrContent");
        task.setPrintNum(1);
        task.setIsLockEpc(1);
        task.setEpcPassword("0");
        task.setEpcRule("1");

        TapeRfidPrintParameterTask parameterTask = new TapeRfidPrintParameterTask();
        parameterTask.setParameterContent("paramContent");
        parameterTask.setType("od");

        when(jobRfidPrintTaskMapper.getBasicBaseRfidPrintById(taskId)).thenReturn(task);
        when(jobRfidPrintTaskMapper.getBasicBaseRfidPrintParameterById(taskId)).thenReturn(List.of(parameterTask));
        when(basicBaseCodingrulesMapper.selectById(anyString())).thenReturn(new BasicBaseCodingRules());

        // When
        taperfidprinttaskjobservice.start(taskId);

        // Then
        verify(jobRfidPrintTaskMapper, times(1)).getBasicBaseRfidPrintById(taskId);
        verify(jobRfidPrintTaskMapper, times(1)).getBasicBaseRfidPrintParameterById(taskId);
    }

    // Add more test methods for other scenarios in TapeRfidPrintTaskJobService
}
