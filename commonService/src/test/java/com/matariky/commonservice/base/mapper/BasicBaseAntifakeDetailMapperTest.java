package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseAntifakeDetail;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseAntifakeDetailMapperTest {

    @InjectMocks
    private BasicBaseAntifakeDetailMapper basicbaseantifakedetailmapper;

    @Mock
    private BasicBaseAntifakeDetailMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseAntifakeDetailAll() {
        // Given
        BasicBaseAntifakeDetail bean = new BasicBaseAntifakeDetail();
        List<BasicBaseAntifakeDetail> expectedList = Collections.singletonList(bean);
        when(mockMapper.getBasicBaseAntifakeDetailAll(bean)).thenReturn(expectedList);

        // When
        List<BasicBaseAntifakeDetail> result = basicbaseantifakedetailmapper.getBasicBaseAntifakeDetailAll(bean);

        // Then
        assertNotNull(result);
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseAntifakeDetail() {
        // Given
        BasicBaseAntifakeDetail bean = new BasicBaseAntifakeDetail();
        when(mockMapper.createBasicBaseAntifakeDetail(bean)).thenReturn(1);

        // When
        int result = basicbaseantifakedetailmapper.createBasicBaseAntifakeDetail(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseAntifakeDetailById() {
        // Given
        Long id = 1L;
        when(mockMapper.delBasicBaseAntifakeDetailById(id)).thenReturn(1);

        // When
        int result = basicbaseantifakedetailmapper.delBasicBaseAntifakeDetailById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseAntifakeDetailById() {
        // Given
        Long id = 1L;
        BasicBaseAntifakeDetail expectedDetail = new BasicBaseAntifakeDetail();
        when(mockMapper.getBasicBaseAntifakeDetailById(id)).thenReturn(expectedDetail);

        // When
        BasicBaseAntifakeDetail result = basicbaseantifakedetailmapper.getBasicBaseAntifakeDetailById(id);

        // Then
        assertNotNull(result);
        assertEquals(expectedDetail, result);
    }

    // Add more test methods for other methods in BasicBaseAntifakeDetailMapper
}
