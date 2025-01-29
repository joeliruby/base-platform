package com.matariky.commonservice.log.mapper;

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

import com.matariky.commonservice.log.bean.BasicLogPrint;

@SpringBootTest
public class BasicLogPrintMapperTest {

    @InjectMocks
    private BasicLogPrintMapper basiclogprintmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicLogPrintAll() {
        // Given
        List<BasicLogPrint> expectedList = Collections.emptyList();
        when(basiclogprintmapper.getBasicLogPrintAll()).thenReturn(expectedList);

        // When
        List<BasicLogPrint> result = basiclogprintmapper.getBasicLogPrintAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicLogPrint() {
        // Given
        BasicLogPrint logPrint = new BasicLogPrint();
        when(basiclogprintmapper.createBasicLogPrint(logPrint)).thenReturn(1);

        // When
        int result = basiclogprintmapper.createBasicLogPrint(logPrint);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicLogPrint() {
        // Given
        BasicLogPrint logPrint = new BasicLogPrint();
        when(basiclogprintmapper.updateBasicLogPrint(logPrint)).thenReturn(1);

        // When
        int result = basiclogprintmapper.updateBasicLogPrint(logPrint);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicLogPrintById() {
        // Given
        int id = 1;
        when(basiclogprintmapper.delBasicLogPrintById(id)).thenReturn(1);

        // When
        int result = basiclogprintmapper.delBasicLogPrintById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicLogPrintById() {
        // Given
        int id = 1;
        BasicLogPrint expectedLogPrint = new BasicLogPrint();
        when(basiclogprintmapper.getBasicLogPrintById(id)).thenReturn(expectedLogPrint);

        // When
        BasicLogPrint result = basiclogprintmapper.getBasicLogPrintById(id);

        // Then
        assertEquals(expectedLogPrint, result);
    }

    @Test
    void testGetBasicLogPrintDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicLogPrint> expectedList = Collections.emptyList();
        when(basiclogprintmapper.getBasicLogPrintDAC(params)).thenReturn(expectedList);

        // When
        List<BasicLogPrint> result = basiclogprintmapper.getBasicLogPrintDAC(params);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicLogPrintDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        Long expectedCount = 0L;
        when(basiclogprintmapper.getBasicLogPrintDACCount(params)).thenReturn(expectedCount);

        // When
        Long result = basiclogprintmapper.getBasicLogPrintDACCount(params);

        // Then
        assertEquals(expectedCount, result);
    }
}
