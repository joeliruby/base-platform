package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.matariky.commonservice.base.bean.BasicBaseTraceability;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.pagehelper.Page;
import java.util.List;

@SpringBootTest
public class BasicBaseTraceabilityMapperTest {

    @InjectMocks
    private BasicBaseTraceabilityMapper basicBaseTraceabilityMapper;

    @Mock
    private Wrapper<BasicBaseTraceability> wrapper;

    @Mock
    private Page<BasicBaseTraceability> page;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseTraceabilityAll() {
        // Given
        BasicBaseTraceability bean = new BasicBaseTraceability();
        List<BasicBaseTraceability> expectedList = List.of(bean);
        when(basicBaseTraceabilityMapper.getBasicBaseTraceabilityAll(bean)).thenReturn(expectedList);

        // When
        List<BasicBaseTraceability> result = basicBaseTraceabilityMapper.getBasicBaseTraceabilityAll(bean);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseTraceability() {
        // Given
        BasicBaseTraceability bean = new BasicBaseTraceability();
        when(basicBaseTraceabilityMapper.createBasicBaseTraceability(bean)).thenReturn(1);

        // When
        int result = basicBaseTraceabilityMapper.createBasicBaseTraceability(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseTraceability() {
        // Given
        BasicBaseTraceability bean = new BasicBaseTraceability();
        when(basicBaseTraceabilityMapper.updateBasicBaseTraceability(bean)).thenReturn(1);

        // When
        int result = basicBaseTraceabilityMapper.updateBasicBaseTraceability(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseTraceabilityById() {
        // Given
        int id = 1;
        when(basicBaseTraceabilityMapper.delBasicBaseTraceabilityById(id)).thenReturn(1);

        // When
        int result = basicBaseTraceabilityMapper.delBasicBaseTraceabilityById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseTraceabilityById() {
        // Given
        int id = 1;
        BasicBaseTraceability expected = new BasicBaseTraceability();
        when(basicBaseTraceabilityMapper.getBasicBaseTraceabilityById(id)).thenReturn(expected);

        // When
        BasicBaseTraceability result = basicBaseTraceabilityMapper.getBasicBaseTraceabilityById(id);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testSelectPage() {
        // Given
        List<BasicBaseTraceability> expectedList = List.of(new BasicBaseTraceability());
        when(basicBaseTraceabilityMapper.selectPage(page, wrapper)).thenReturn(page);
        when(page.getResult()).thenReturn(expectedList);

        // When
        Page<BasicBaseTraceability> result = basicBaseTraceabilityMapper.selectPage(page, wrapper);

        // Then
        assertEquals(expectedList, result.getResult());
    }

    // Add more test methods for other methods in BasicBaseTraceabilityMapper
}
