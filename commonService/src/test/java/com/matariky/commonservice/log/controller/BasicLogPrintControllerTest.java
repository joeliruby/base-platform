package com.matariky.commonservice.log.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.log.bean.BasicLogPrint;
import com.matariky.commonservice.log.service.BasicLogPrintService;

@SpringBootTest
public class BasicLogPrintControllerTest {

    @InjectMocks
    private BasicLogPrintController basicLogPrintController;

    @Mock
    private BasicLogPrintService basicLogPrintService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        List<BasicLogPrint> logPrints = Collections.singletonList(new BasicLogPrint());
        when(basicLogPrintService.getBasicLogPrintAll()).thenReturn(logPrints);

        // When
        PageInfo<BasicLogPrint> result = basicLogPrintController.list(null, null, "tenantId", 1, 10, "jwt");

        // Then
        assertNotNull(result);
        assertEquals(1, result.getList().size());
        verify(basicLogPrintService, times(1)).getBasicLogPrintAll();
    }

    @Test
    void testSave() {
        // Given
        BasicLogPrint logPrint = new BasicLogPrint();
        when(basicLogPrintService.createBasicLogPrintWithOrg(any(BasicLogPrint.class), any())).thenReturn(1);

        // When
        ResponseEntity<String> response = basicLogPrintController.save(logPrint, null, null);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
        verify(basicLogPrintService, times(1)).createBasicLogPrintWithOrg(any(BasicLogPrint.class), any());
    }

    @Test
    void testUpdate() {
        // Given
        BasicLogPrint logPrint = new BasicLogPrint();
        when(basicLogPrintService.updateBasicLogPrint(any(BasicLogPrint.class))).thenReturn(1);

        // When
        ResponseEntity<String> response = basicLogPrintController.update(logPrint, null, null);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
        verify(basicLogPrintService, times(1)).updateBasicLogPrint(any(BasicLogPrint.class));
    }

    @Test
    void testDelete() {
        // Given
        when(basicLogPrintService.deleteById(anyLong())).thenReturn(true);

        // When
        ResponseEntity<String> response = basicLogPrintController.del("1", null, null);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
        verify(basicLogPrintService, times(1)).deleteById(anyLong());
    }

    @Test
    void testGetOne() {
        // Given
        BasicLogPrint logPrint = new BasicLogPrint();
        when(basicLogPrintService.selectById(anyLong())).thenReturn(logPrint);

        // When
        Object result = basicLogPrintController.getOne(1L, null, null);

        // Then
        assertNotNull(result);
        assertEquals(logPrint, result);
        verify(basicLogPrintService, times(1)).selectById(anyLong());
    }
}
