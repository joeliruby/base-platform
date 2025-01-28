package com.matariky.jobs.jobsService.assetitm.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidPrintDetailTask;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidPrintParameterTask;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidPrintTask;
import org.mockito.Mock;
import java.util.List;

@SpringBootTest
public class JobRfidPrintTaskMapperTest {

    @InjectMocks
    private JobRfidPrintTaskMapper jobRfidPrintTaskMapper;

    @Mock
    private TapeRfidPrintTask tapeRfidPrintTask;

    @Mock
    private TapeRfidPrintParameterTask tapeRfidPrintParameterTask;

    @Mock
    private TapeRfidPrintDetailTask tapeRfidPrintDetailTask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidPrintById() {
        // Given
        Long id = 1L;
        when(jobRfidPrintTaskMapper.getBasicBaseRfidPrintById(id)).thenReturn(tapeRfidPrintTask);

        // When
        TapeRfidPrintTask result = jobRfidPrintTaskMapper.getBasicBaseRfidPrintById(id);

        // Then
        assertNotNull(result);
        assertEquals(tapeRfidPrintTask, result);
    }

    @Test
    void testGetBasicBaseRfidPrintParameterById() {
        // Given
        Long id = 1L;
        List<TapeRfidPrintParameterTask> expectedList = List.of(tapeRfidPrintParameterTask);
        when(jobRfidPrintTaskMapper.getBasicBaseRfidPrintParameterById(id)).thenReturn(expectedList);

        // When
        List<TapeRfidPrintParameterTask> result = jobRfidPrintTaskMapper.getBasicBaseRfidPrintParameterById(id);

        // Then
        assertNotNull(result);
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseRfidprintDetail() {
        // Given
        when(jobRfidPrintTaskMapper.createBasicBaseRfidprintDetail(tapeRfidPrintDetailTask)).thenReturn(1);

        // When
        int result = jobRfidPrintTaskMapper.createBasicBaseRfidprintDetail(tapeRfidPrintDetailTask);

        // Then
        assertEquals(1, result);
    }

    // Add more test methods for other methods in JobRfidPrintTaskMapper
}
