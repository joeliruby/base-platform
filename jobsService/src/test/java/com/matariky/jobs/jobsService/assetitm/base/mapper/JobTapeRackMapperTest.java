package com.matariky.jobs.jobsService.assetitm.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRack;
import com.matariky.mybatis.EnhanceBaseMapper;

@SpringBootTest
public class JobTapeRackMapperTest {

    @InjectMocks
    private JobTapeRackMapper jobTapeRackMapper;

    @Mock
    private EnhanceBaseMapper<TapeRack> enhanceBaseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        TapeRack tapeRack = new TapeRack();
        tapeRack.setId(1L);
        when(enhanceBaseMapper.selectById(1L)).thenReturn(tapeRack);

        Optional<TapeRack> result = Optional.ofNullable(jobTapeRackMapper.selectById(1L));
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testInsert() {
        TapeRack tapeRack = new TapeRack();
        tapeRack.setId(1L);
        doNothing().when(enhanceBaseMapper).insert(tapeRack);

        jobTapeRackMapper.insert(tapeRack);
        verify(enhanceBaseMapper, times(1)).insert(tapeRack);
    }

    @Test
    void testUpdate() {
        TapeRack tapeRack = new TapeRack();
        tapeRack.setId(1L);
        doNothing().when(enhanceBaseMapper).updateById(tapeRack);

        jobTapeRackMapper.updateById(tapeRack);
        verify(enhanceBaseMapper, times(1)).updateById(tapeRack);
    }

    @Test
    void testDelete() {
        doNothing().when(enhanceBaseMapper).deleteById(1L);

        jobTapeRackMapper.deleteById(1L);
        verify(enhanceBaseMapper, times(1)).deleteById(1L);
    }
}
