package com.matariky.commonservice.log.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.log.bean.BasicLogNetwork;
import com.matariky.commonservice.log.service.BasicLogNetworkService;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;

@SpringBootTest
public class BasicLogNetworkControllerTest {

    @InjectMocks
    private BasicLogNetworkController basiclognetworkcontroller;

    @Mock
    private BasicLogNetworkService basicLogNetworkService;

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
        when(basicLogNetworkService.getBasicLogNetworkAll()).thenReturn(Collections.emptyList());

        // When
        PageInfo<BasicLogNetwork> result = basiclognetworkcontroller.list(request, new BasicLogNetwork(), "tenantId", 1,
                10, "jwt");

        // Then
        assertNotNull(result);
        assertEquals(0, result.getList().size());
    }

    @Test
    void testSave() {
        // Given
        BasicLogNetwork bean = new BasicLogNetwork();
        when(basicLogNetworkService.createBasicLogNetworkWithOrg(bean, request)).thenReturn(1);

        // When
        ResponseEntity<String> response = basiclognetworkcontroller.save(bean, request, this.response);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
    }

    @Test
    void testUpdate() {
        // Given
        BasicLogNetwork bean = new BasicLogNetwork();
        when(basicLogNetworkService.updateBasicLogNetwork(bean)).thenReturn(1);

        // When
        ResponseEntity<String> response = basiclognetworkcontroller.update(bean, request, this.response);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
    }

    @Test
    void testDelete() {
        // Given
        when(basicLogNetworkService.deleteById(1L)).thenReturn(true);

        // When
        ResponseEntity<String> response = basiclognetworkcontroller.del("1", request, this.response);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
    }

    @Test
    void testGetOne() {
        // Given
        BasicLogNetwork expected = new BasicLogNetwork();
        when(basicLogNetworkService.selectById(1L)).thenReturn(expected);

        // When
        Object result = basiclognetworkcontroller.getOne(1L, request, response);

        // Then
        assertEquals(expected, result);
    }

    // Add more test methods for other methods in BasicLogNetworkController
}
