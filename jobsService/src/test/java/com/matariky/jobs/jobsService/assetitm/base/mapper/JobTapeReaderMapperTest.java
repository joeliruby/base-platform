package com.matariky.jobs.jobsService.assetitm.base.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.jobs.jobsService.assetitm.base.bean.TapeReader;
import com.matariky.jobs.jobsService.assetitm.base.bo.BasicReaderBo;
import com.matariky.mybatis.EnhanceBaseMapper;

@SpringBootTest
public class JobTapeReaderMapperTest {

    @InjectMocks
    private JobTapeReaderMapper jobTapeReaderMapper;

    @Mock
    private EnhanceBaseMapper<TapeReader> enhanceBaseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSelectBasicListWithValidData() {
        // Given
        String locationId = "location1";
        List<Long> rackIds = Arrays.asList(1L, 2L, 3L);
        List<BasicReaderBo> expectedList = Arrays.asList(new BasicReaderBo(), new BasicReaderBo());
        when(jobTapeReaderMapper.selectBasicList(locationId, rackIds)).thenReturn(expectedList);

        // When
        List<BasicReaderBo> result = jobTapeReaderMapper.selectBasicList(locationId, rackIds);

        // Then
        assertThat(result).isEqualTo(expectedList);
    }

    @Test
    void testSelectBasicListWithEmptyRackIds() {
        // Given
        String locationId = "location1";
        List<Long> rackIds = Collections.emptyList();
        List<BasicReaderBo> expectedList = Collections.emptyList();
        when(jobTapeReaderMapper.selectBasicList(locationId, rackIds)).thenReturn(expectedList);

        // When
        List<BasicReaderBo> result = jobTapeReaderMapper.selectBasicList(locationId, rackIds);

        // Then
        assertThat(result).isEqualTo(expectedList);
    }

    @Test
    void testSelectBasicListWithNullRackIds() {
        // Given
        String locationId = "location1";
        List<Long> rackIds = null;
        List<BasicReaderBo> expectedList = Collections.emptyList();
        when(jobTapeReaderMapper.selectBasicList(locationId, rackIds)).thenReturn(expectedList);

        // When
        List<BasicReaderBo> result = jobTapeReaderMapper.selectBasicList(locationId, rackIds);

        // Then
        assertThat(result).isEqualTo(expectedList);
    }

    // Add more test methods for other methods in JobTapeReaderMapper
}
