package com.matariky.jobs.jobsService.assetitm.inout.mapper;

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

import com.matariky.jobs.jobsService.assetitm.inout.bean.TapeInout;
import com.matariky.mybatis.EnhanceBaseMapper;

@SpringBootTest
public class JobTapeInoutMapperTest {

    @InjectMocks
    private JobTapeInoutMapper jobTapeInoutMapper;

    @Mock
    private EnhanceBaseMapper<TapeInout> enhanceBaseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        TapeInout tapeInout = new TapeInout();
        when(enhanceBaseMapper.insert(tapeInout)).thenReturn(1);

        int result = jobTapeInoutMapper.insert(tapeInout);

        assertEquals(1, result);
        verify(enhanceBaseMapper, times(1)).insert(tapeInout);
    }

    @Test
    void testUpdate() {
        TapeInout tapeInout = new TapeInout();
        when(enhanceBaseMapper.updateById(tapeInout)).thenReturn(1);

        int result = jobTapeInoutMapper.updateById(tapeInout);

        assertEquals(1, result);
        verify(enhanceBaseMapper, times(1)).updateById(tapeInout);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        when(enhanceBaseMapper.deleteById(id)).thenReturn(1);

        int result = jobTapeInoutMapper.deleteById(id);

        assertEquals(1, result);
        verify(enhanceBaseMapper, times(1)).deleteById(id);
    }

    @Test
    void testSelectById() {
        Long id = 1L;
        TapeInout tapeInout = new TapeInout();
        when(enhanceBaseMapper.selectById(id)).thenReturn(tapeInout);

        TapeInout result = jobTapeInoutMapper.selectById(id);

        assertEquals(tapeInout, result);
        verify(enhanceBaseMapper, times(1)).selectById(id);
    }

    // Add more test methods for other methods in JobTapeInoutMapper
}
