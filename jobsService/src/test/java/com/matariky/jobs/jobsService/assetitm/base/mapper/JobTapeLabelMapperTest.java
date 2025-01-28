package com.matariky.jobs.jobsService.assetitm.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.jobs.jobsService.assetitm.base.bean.TapeLabel;
import com.matariky.mybatis.EnhanceBaseMapper;

@SpringBootTest
public class JobTapeLabelMapperTest {

    @InjectMocks
    private JobTapeLabelMapper jobTapeLabelMapper;

    @Mock
    private EnhanceBaseMapper<TapeLabel> enhanceBaseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertTapeLabel() {
        TapeLabel tapeLabel = new TapeLabel();
        when(enhanceBaseMapper.insert(tapeLabel)).thenReturn(1);

        int result = jobTapeLabelMapper.insert(tapeLabel);

        assertEquals(1, result);
        verify(enhanceBaseMapper, times(1)).insert(tapeLabel);
    }

    @Test
    void testUpdateTapeLabel() {
        TapeLabel tapeLabel = new TapeLabel();
        when(enhanceBaseMapper.updateById(tapeLabel)).thenReturn(1);

        int result = jobTapeLabelMapper.updateById(tapeLabel);

        assertEquals(1, result);
        verify(enhanceBaseMapper, times(1)).updateById(tapeLabel);
    }

    @Test
    void testDeleteTapeLabel() {
        Long id = 1L;
        when(enhanceBaseMapper.deleteById(id)).thenReturn(1);

        int result = jobTapeLabelMapper.deleteById(id);

        assertEquals(1, result);
        verify(enhanceBaseMapper, times(1)).deleteById(id);
    }

    @Test
    void testSelectTapeLabelById() {
        Long id = 1L;
        TapeLabel tapeLabel = new TapeLabel();
        when(enhanceBaseMapper.selectById(id)).thenReturn(tapeLabel);

        TapeLabel result = jobTapeLabelMapper.selectById(id);

        assertEquals(tapeLabel, result);
        verify(enhanceBaseMapper, times(1)).selectById(id);
    }

    // Add more test methods for other methods in JobTapeLabelMapper
}
