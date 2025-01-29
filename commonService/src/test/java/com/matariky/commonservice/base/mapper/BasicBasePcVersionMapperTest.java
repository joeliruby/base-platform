package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBasePcVersion;
import com.matariky.commonservice.base.vo.BasicBasePcVersionListVO;
import com.matariky.commonservice.base.vo.BasicBasePcVersionQueryVO;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBasePcVersionMapperTest {

    @InjectMocks
    private BasicBasePcVersionMapper basicbasepcversionmapper;

    @Mock
    private BasicBasePcVersionMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBasePcversionAll() {
        // Given
        BasicBasePcVersionQueryVO queryVO = new BasicBasePcVersionQueryVO();
        List<BasicBasePcVersionListVO> expectedList = Collections.singletonList(new BasicBasePcVersionListVO());
        when(mockMapper.getBasicBasePcversionAll(queryVO)).thenReturn(expectedList);

        // When
        List<BasicBasePcVersionListVO> result = basicbasepcversionmapper.getBasicBasePcversionAll(queryVO);

        // Then
        assertNotNull(result);
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBasePcversion() {
        // Given
        BasicBasePcVersion pcVersion = new BasicBasePcVersion();
        when(mockMapper.createBasicBasePcversion(pcVersion)).thenReturn(1);

        // When
        int result = basicbasepcversionmapper.createBasicBasePcversion(pcVersion);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBasePcversionById() {
        // Given
        Long id = 1L;
        when(mockMapper.delBasicBasePcversionById(id)).thenReturn(1);

        // When
        int result = basicbasepcversionmapper.delBasicBasePcversionById(id);

        // Then
        assertEquals(1, result);
    }

    // Add more test methods for other methods in BasicBasePcVersionMapper
}
