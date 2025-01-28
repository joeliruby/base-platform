package com.matariky.jobs.jobsService.assetitm.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidCreateParameterTask;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidCreateTask;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JobRfidCreateTaskMapperTest {

    @InjectMocks
    private JobRfidCreateTaskMapper jobrfidcreatetaskmapper;

    @Mock
    private JobRfidCreateTaskMapper mockJobRfidCreateTaskMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidfactoryById() {
        // Given
        Long id = 1L;
        TapeRfidCreateTask expectedTask = new TapeRfidCreateTask();
        when(mockJobRfidCreateTaskMapper.getBasicBaseRfidfactoryById(id)).thenReturn(expectedTask);

        // When
        TapeRfidCreateTask actualTask = jobrfidcreatetaskmapper.getBasicBaseRfidfactoryById(id);

        // Then
        assertEquals(expectedTask, actualTask);
    }

    @Test
    void testGetBasicBaseRfidfactoryParameterById() {
        // Given
        Long id = 1L;
        List<TapeRfidCreateParameterTask> expectedParameters = Arrays.asList(new TapeRfidCreateParameterTask(),
                new TapeRfidCreateParameterTask());
        when(mockJobRfidCreateTaskMapper.getBasicBaseRfidfactoryParameterById(id)).thenReturn(expectedParameters);

        // When
        List<TapeRfidCreateParameterTask> actualParameters = jobrfidcreatetaskmapper
                .getBasicBaseRfidfactoryParameterById(id);

        // Then
        assertEquals(expectedParameters, actualParameters);
    }

    @Test
    void testUpdateBasicBaseRfidfactory() {
        // Given
        TapeRfidCreateTask task = new TapeRfidCreateTask();
        when(mockJobRfidCreateTaskMapper.updateBasicBaseRfidfactory(task)).thenReturn(1);

        // When
        int result = jobrfidcreatetaskmapper.updateBasicBaseRfidfactory(task);

        // Then
        assertEquals(1, result);
    }

    // Add more test methods for other methods in JobRfidCreateTaskMapper
}
