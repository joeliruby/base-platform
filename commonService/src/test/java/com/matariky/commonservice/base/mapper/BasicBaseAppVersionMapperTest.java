package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseAppVersion;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionListVO;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionQueryVO;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseAppVersionMapperTest {

    @InjectMocks
    private BasicBaseAppVersionMapper basicbaseappversionmapper;

    @Mock
    private BasicBaseAppVersionQueryVO queryVO;

    @Mock
    private BasicBaseAppVersion basicBaseAppVersion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseAppversionAll() {
        // Given
        List<BasicBaseAppVersionListVO> expectedList = Collections.emptyList();
        when(basicbaseappversionmapper.getBasicBaseAppversionAll(queryVO)).thenReturn(expectedList);

        // When
        List<BasicBaseAppVersionListVO> result = basicbaseappversionmapper.getBasicBaseAppversionAll(queryVO);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseAppversion() {
        // Given
        when(basicbaseappversionmapper.createBasicBaseAppversion(basicBaseAppVersion)).thenReturn(1);

        // When
        int result = basicbaseappversionmapper.createBasicBaseAppversion(basicBaseAppVersion);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseAppversionById() {
        // Given
        Long id = 1L;
        when(basicbaseappversionmapper.delBasicBaseAppversionById(id)).thenReturn(1);

        // When
        int result = basicbaseappversionmapper.delBasicBaseAppversionById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBasePrintApp() {
        // Given
        when(basicbaseappversionmapper.getBasicBasePrintApp()).thenReturn(basicBaseAppVersion);

        // When
        BasicBaseAppVersion result = basicbaseappversionmapper.getBasicBasePrintApp();

        // Then
        assertEquals(basicBaseAppVersion, result);
    }
}
