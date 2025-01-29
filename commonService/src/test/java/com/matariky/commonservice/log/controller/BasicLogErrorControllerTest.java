package com.matariky.commonservice.log.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.matariky.commonservice.log.service.BasicLogErrorService;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.log.bean.BasicLogError;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@SpringBootTest
public class BasicLogErrorControllerTest {

    @InjectMocks
    private BasicLogErrorController basiclogerrorcontroller;

    @Mock
    private BasicLogErrorService basicLogErrorService;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        when(basicLogErrorService.getBasicLogErrorAll()).thenReturn(Collections.emptyList());

        // When
        var result = basiclogerrorcontroller.list(request, new BasicLogError(), "tenantId", 1, 10, "jwtToken");

        // Then
        assertNotNull(result);
        assertEquals(0, result.getList().size());
    }

    @Test
    void testSave() {
        // Given
        BasicLogError logError = new BasicLogError();
        when(basicLogErrorService.createBasicLogErrorWithOrg(any(BasicLogError.class), any(HttpServletRequest.class)))
                .thenReturn(1);

        // When
        ResponseEntity<String> response = basiclogerrorcontroller.save(logError, request, this.response);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
    }

    @Test
    void testUpdate() {
        // Given
        BasicLogError logError = new BasicLogError();
        when(basicLogErrorService.updateBasicLogError(any(BasicLogError.class))).thenReturn(1);

        // When
        ResponseEntity<String> response = basiclogerrorcontroller.update(logError, request, this.response);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
    }

    @Test
    void testDelete() {
        // Given
        when(basicLogErrorService.deleteById(anyLong())).thenReturn(true);

        // When
        ResponseEntity<String> response = basiclogerrorcontroller.del("1", request, this.response);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
    }

    @Test
    void testGetOne() {
        // Given
        BasicLogError logError = new BasicLogError();
        when(basicLogErrorService.selectById(anyLong())).thenReturn(logError);

        // When
        Object result = basiclogerrorcontroller.getOne(1L, request, response);

        // Then
        assertNotNull(result);
        assertEquals(logError, result);
    }

    // Add more test methods for other methods in BasicLogErrorController
}
