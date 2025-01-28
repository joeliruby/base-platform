package com.matariky.bizservice.assetitm.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidPrint;

@SpringBootTest
public class BasicBaseRfidPrintMapperTest {

    @InjectMocks
    private BasicBaseRfidPrintMapper basicBaseRfidPrintMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidprintAll() {
        // Given
        BasicBaseRfidPrint bean = new BasicBaseRfidPrint();
        when(basicBaseRfidPrintMapper.getBasicBaseRfidprintAll(bean)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseRfidPrint> result = basicBaseRfidPrintMapper.getBasicBaseRfidprintAll(bean);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseRfidprint() {
        // Given
        BasicBaseRfidPrint bean = new BasicBaseRfidPrint();
        when(basicBaseRfidPrintMapper.createBasicBaseRfidprint(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidPrintMapper.createBasicBaseRfidprint(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidprint() {
        // Given
        BasicBaseRfidPrint bean = new BasicBaseRfidPrint();
        when(basicBaseRfidPrintMapper.updateBasicBaseRfidprint(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidPrintMapper.updateBasicBaseRfidprint(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidprintById() {
        // Given
        int id = 1;
        when(basicBaseRfidPrintMapper.delBasicBaseRfidprintById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidPrintMapper.delBasicBaseRfidprintById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidprintById() {
        // Given
        Long id = 1L;
        BasicBaseRfidPrint expected = new BasicBaseRfidPrint();
        when(basicBaseRfidPrintMapper.getBasicBaseRfidprintById(id)).thenReturn(expected);

        // When
        BasicBaseRfidPrint result = basicBaseRfidPrintMapper.getBasicBaseRfidprintById(id);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testPrintLock() {
        // Given
        Long printId = 1L;
        Long deviceId = 1L;
        when(basicBaseRfidPrintMapper.printLock(printId, deviceId)).thenReturn(1);

        // When
        int result = basicBaseRfidPrintMapper.printLock(printId, deviceId);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testPrintUnlock() {
        // Given
        Long printId = 1L;
        when(basicBaseRfidPrintMapper.printUnlock(printId)).thenReturn(1);

        // When
        int result = basicBaseRfidPrintMapper.printUnlock(printId);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testSelectLock() {
        // Given
        Long printId = 1L;
        when(basicBaseRfidPrintMapper.selectLock(printId)).thenReturn(1);

        // When
        int result = basicBaseRfidPrintMapper.selectLock(printId);

        // Then
        assertEquals(1, result);
    }
}
