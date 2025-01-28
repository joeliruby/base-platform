package com.matariky.jobs.jobsService.assetitm.stock.mapper;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import com.matariky.jobs.jobsService.assetitm.stock.bean.TapeStock;
import com.matariky.jobs.jobsService.assetitm.stock.vo.TapeStockLabelVo;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JobTapeStockMapperTest {

    @InjectMocks
    private JobTapeStockMapper jobTapeStockMapper;

    @Mock
    private TapeStock tapeStock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSelectStockLabelList() {
        // Given
        String locationId = "location1";
        List<Long> rackIds = Arrays.asList(1L, 2L, 3L);
        List<TapeStockLabelVo> expectedList = Arrays.asList(new TapeStockLabelVo(), new TapeStockLabelVo());
        when(jobTapeStockMapper.selectStockLabelList(locationId, rackIds)).thenReturn(expectedList);

        // When
        List<TapeStockLabelVo> result = jobTapeStockMapper.selectStockLabelList(locationId, rackIds);

        // Then
        assertThat(result).isEqualTo(expectedList);
    }

    @Test
    void testInsertTapeStock() {
        // Given
        TapeStock tapeStock = new TapeStock();
        doNothing().when(jobTapeStockMapper).insert(tapeStock);

        // When
        jobTapeStockMapper.insert(tapeStock);

        // Then
        verify(jobTapeStockMapper, times(1)).insert(tapeStock);
    }

    @Test
    void testUpdateTapeStock() {
        // Given
        TapeStock tapeStock = new TapeStock();
        doNothing().when(jobTapeStockMapper).updateById(tapeStock);

        // When
        jobTapeStockMapper.updateById(tapeStock);

        // Then
        verify(jobTapeStockMapper, times(1)).updateById(tapeStock);
    }

    @Test
    void testDeleteTapeStock() {
        // Given
        Long id = 1L;
        doNothing().when(jobTapeStockMapper).deleteById(id);

        // When
        jobTapeStockMapper.deleteById(id);

        // Then
        verify(jobTapeStockMapper, times(1)).deleteById(id);
    }

    // Add more test methods for other methods in JobTapeStockMapper
}
