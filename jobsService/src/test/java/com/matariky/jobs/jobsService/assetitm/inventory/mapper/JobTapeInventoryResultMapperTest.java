package com.matariky.jobs.jobsService.assetitm.inventory.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.jobs.jobsService.assetitm.inventory.bean.TapeInventoryResult;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class JobTapeInventoryResultMapperTest {

    @InjectMocks
    private JobTapeInventoryResultMapper jobTapeInventoryResultMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        TapeInventoryResult tapeInventoryResult = new TapeInventoryResult();
        tapeInventoryResult.setId(1L);

        jobTapeInventoryResultMapper.insert(tapeInventoryResult);

        verify(jobTapeInventoryResultMapper, times(1)).insert(tapeInventoryResult);
    }

    @Test
    void testSelectById() {
        TapeInventoryResult tapeInventoryResult = new TapeInventoryResult();
        tapeInventoryResult.setId(1L);

        when(jobTapeInventoryResultMapper.selectById(1L)).thenReturn(tapeInventoryResult);

        TapeInventoryResult result = jobTapeInventoryResultMapper.selectById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void testUpdateById() {
        TapeInventoryResult tapeInventoryResult = new TapeInventoryResult();
        tapeInventoryResult.setId(1L);

        jobTapeInventoryResultMapper.updateById(tapeInventoryResult);

        verify(jobTapeInventoryResultMapper, times(1)).updateById(tapeInventoryResult);
    }

    @Test
    void testDeleteById() {
        jobTapeInventoryResultMapper.deleteById(1L);

        verify(jobTapeInventoryResultMapper, times(1)).deleteById(1L);
    }

    // Add more test methods for other methods in JobTapeInventoryResultMapper
}
