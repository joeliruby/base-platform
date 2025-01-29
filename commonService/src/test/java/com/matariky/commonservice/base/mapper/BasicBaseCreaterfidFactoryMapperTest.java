package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.bean.BasicBaseCreaterfidFactory;

@SpringBootTest
public class BasicBaseCreaterfidFactoryMapperTest {

    @InjectMocks
    private BasicBaseCreaterfidFactoryMapper basicbasecreaterfidfactorymapper;

    @Mock
    private BasicBaseCreaterfidFactoryMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseCreaterfidFactoryAll() {
        // Given
        List<BasicBaseCreaterfidFactory> expectedList = Collections.emptyList();
        when(mockMapper.getBasicBaseCreaterfidFactoryAll()).thenReturn(expectedList);

        // When
        List<BasicBaseCreaterfidFactory> result = basicbasecreaterfidfactorymapper.getBasicBaseCreaterfidFactoryAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseCreaterfidFactoryAllCount() {
        // Given
        int expectedCount = 10;
        when(mockMapper.getBasicBaseCreaterfidFactoryAllCount()).thenReturn(expectedCount);

        // When
        int result = basicbasecreaterfidfactorymapper.getBasicBaseCreaterfidFactoryAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateBasicBaseCreaterfidFactory() {
        // Given
        BasicBaseCreaterfidFactory factory = new BasicBaseCreaterfidFactory();
        when(mockMapper.createBasicBaseCreaterfidFactory(factory)).thenReturn(1);

        // When
        int result = basicbasecreaterfidfactorymapper.createBasicBaseCreaterfidFactory(factory);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseCreaterfidFactory() {
        // Given
        BasicBaseCreaterfidFactory factory = new BasicBaseCreaterfidFactory();
        when(mockMapper.updateBasicBaseCreaterfidFactory(factory)).thenReturn(1);

        // When
        int result = basicbasecreaterfidfactorymapper.updateBasicBaseCreaterfidFactory(factory);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseCreaterfidFactoryById() {
        // Given
        int id = 1;
        when(mockMapper.delBasicBaseCreaterfidFactoryById(id)).thenReturn(1);

        // When
        int result = basicbasecreaterfidfactorymapper.delBasicBaseCreaterfidFactoryById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseCreaterfidFactoryById() {
        // Given
        int id = 1;
        BasicBaseCreaterfidFactory expectedFactory = new BasicBaseCreaterfidFactory();
        when(mockMapper.getBasicBaseCreaterfidFactoryById(id)).thenReturn(expectedFactory);

        // When
        BasicBaseCreaterfidFactory result = basicbasecreaterfidfactorymapper.getBasicBaseCreaterfidFactoryById(id);

        // Then
        assertEquals(expectedFactory, result);
    }

    @Test
    void testGetBasicBaseCreaterfidFactoryDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicBaseCreaterfidFactory> expectedList = Collections.emptyList();
        when(mockMapper.getBasicBaseCreaterfidFactoryDAC(params)).thenReturn(expectedList);

        // When
        List<BasicBaseCreaterfidFactory> result = basicbasecreaterfidfactorymapper
                .getBasicBaseCreaterfidFactoryDAC(params);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseCreaterfidFactoryDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        long expectedCount = 10L;
        when(mockMapper.getBasicBaseCreaterfidFactoryDACCount(params)).thenReturn(expectedCount);

        // When
        long result = basicbasecreaterfidfactorymapper.getBasicBaseCreaterfidFactoryDACCount(params);

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testGetBasicBaseCreaterfidFactoryByFactoryId() {
        // Given
        long id = 1L;
        List<BasicBaseCreaterfidFactory> expectedList = Collections.emptyList();
        when(mockMapper.getBasicBaseCreaterfidFactoryByFactoryId(id)).thenReturn(expectedList);

        // When
        List<BasicBaseCreaterfidFactory> result = basicbasecreaterfidfactorymapper
                .getBasicBaseCreaterfidFactoryByFactoryId(id);

        // Then
        assertEquals(expectedList, result);
    }
}
