package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.bean.BasicBaseCreaterfidPrint;

@SpringBootTest
public class BasicBaseCreaterfidPrintMapperTest {

    @InjectMocks
    private BasicBaseCreaterfidPrintMapper basicbasecreaterfidprintmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseCreaterfidPrintAll() {
        // Given
        List<BasicBaseCreaterfidPrint> expectedList = Collections.emptyList();
        when(basicbasecreaterfidprintmapper.getBasicBaseCreaterfidPrintAll()).thenReturn(expectedList);

        // When
        List<BasicBaseCreaterfidPrint> result = basicbasecreaterfidprintmapper.getBasicBaseCreaterfidPrintAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseCreaterfidPrintAllCount() {
        // Given
        int expectedCount = 10;
        when(basicbasecreaterfidprintmapper.getBasicBaseCreaterfidPrintAllCount()).thenReturn(expectedCount);

        // When
        int result = basicbasecreaterfidprintmapper.getBasicBaseCreaterfidPrintAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateBasicBaseCreaterfidPrint() {
        // Given
        BasicBaseCreaterfidPrint bean = new BasicBaseCreaterfidPrint();
        when(basicbasecreaterfidprintmapper.createBasicBaseCreaterfidPrint(bean)).thenReturn(1);

        // When
        int result = basicbasecreaterfidprintmapper.createBasicBaseCreaterfidPrint(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseCreaterfidPrint() {
        // Given
        BasicBaseCreaterfidPrint bean = new BasicBaseCreaterfidPrint();
        when(basicbasecreaterfidprintmapper.updateBasicBaseCreaterfidPrint(bean)).thenReturn(1);

        // When
        int result = basicbasecreaterfidprintmapper.updateBasicBaseCreaterfidPrint(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseCreaterfidPrintById() {
        // Given
        int id = 1;
        when(basicbasecreaterfidprintmapper.delBasicBaseCreaterfidPrintById(id)).thenReturn(1);

        // When
        int result = basicbasecreaterfidprintmapper.delBasicBaseCreaterfidPrintById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseCreaterfidPrintById() {
        // Given
        int id = 1;
        BasicBaseCreaterfidPrint expected = new BasicBaseCreaterfidPrint();
        when(basicbasecreaterfidprintmapper.getBasicBaseCreaterfidPrintById(id)).thenReturn(expected);

        // When
        BasicBaseCreaterfidPrint result = basicbasecreaterfidprintmapper.getBasicBaseCreaterfidPrintById(id);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testGetBasicBaseCreaterfidPrintDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicBaseCreaterfidPrint> expectedList = Collections.emptyList();
        when(basicbasecreaterfidprintmapper.getBasicBaseCreaterfidPrintDAC(params)).thenReturn(expectedList);

        // When
        List<BasicBaseCreaterfidPrint> result = basicbasecreaterfidprintmapper.getBasicBaseCreaterfidPrintDAC(params);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseCreaterfidPrintDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        Long expectedCount = 10L;
        when(basicbasecreaterfidprintmapper.getBasicBaseCreaterfidPrintDACCount(params)).thenReturn(expectedCount);

        // When
        Long result = basicbasecreaterfidprintmapper.getBasicBaseCreaterfidPrintDACCount(params);

        // Then
        assertEquals(expectedCount, result);
    }
}
