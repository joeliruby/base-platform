package com.matariky.jobs.jobsService.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.Serializable;
import java.util.*;
import org.mockito.Mock;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matariky.jobs.jobsService.bean.domain.JobAndTrigger;

@SpringBootTest
public class JobMapperTest {

    @InjectMocks
    private JobMapper jobMapper;

    @Mock
    private JobMapper mockJobMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        String tenantId = "tenant1";
        List<JobAndTrigger> expectedList = new ArrayList<>();
        when(mockJobMapper.list(tenantId)).thenReturn(expectedList);

        List<JobAndTrigger> actualList = jobMapper.list(tenantId);
        assertEquals(expectedList, actualList);
    }

    @Test
    void testUpdateTenantId() {
        String jobGroupName = "group1";
        String jobClassName = "class1";
        String tenantId = "tenant1";

        doNothing().when(mockJobMapper).updateTenantId(jobGroupName, jobClassName, tenantId);
        jobMapper.updateTenantId(jobGroupName, jobClassName, tenantId);

        verify(mockJobMapper, times(1)).updateTenantId(jobGroupName, jobClassName, tenantId);
    }

    @Test
    void testGetJobByClassAndName() {
        String jobClassName = "class1";
        String jobGroupName = "group1";
        int expectedCount = 1;
        when(mockJobMapper.getJobByClassAndName(jobClassName, jobGroupName)).thenReturn(expectedCount);

        int actualCount = jobMapper.getJobByClassAndName(jobClassName, jobGroupName);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testInsert() {
        JobAndTrigger jobAndTrigger = new JobAndTrigger();
        int expectedResult = 1;
        when(mockJobMapper.insert(jobAndTrigger)).thenReturn(expectedResult);

        int actualResult = jobMapper.insert(jobAndTrigger);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testDeleteById() {
        Serializable id = 1L;
        int expectedResult = 1;
        when(mockJobMapper.deleteById(id)).thenReturn(expectedResult);

        int actualResult = jobMapper.deleteById(id);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testSelectById() {
        Serializable id = 1L;
        JobAndTrigger expectedJob = new JobAndTrigger();
        when(mockJobMapper.selectById(id)).thenReturn(expectedJob);

        JobAndTrigger actualJob = jobMapper.selectById(id);
        assertEquals(expectedJob, actualJob);
    }

    @Test
    void testSelectList() {
        Wrapper<JobAndTrigger> queryWrapper = mock(Wrapper.class);
        List<JobAndTrigger> expectedList = new ArrayList<>();
        when(mockJobMapper.selectList(queryWrapper)).thenReturn(expectedList);

        List<JobAndTrigger> actualList = jobMapper.selectList(queryWrapper);
        assertEquals(expectedList, actualList);
    }

    @Test
    void testSelectPage() {
        Page<JobAndTrigger> page = new Page<>(1, 10);
        Wrapper<JobAndTrigger> queryWrapper = mock(Wrapper.class);
        Page<JobAndTrigger> expectedPage = new Page<>();
        when(mockJobMapper.selectPage(page, queryWrapper)).thenReturn(expectedPage);

        Page<JobAndTrigger> actualPage = jobMapper.selectPage(page, queryWrapper);
        assertEquals(expectedPage, actualPage);
    }

    // Add more test methods for other methods in JobMapper
}
