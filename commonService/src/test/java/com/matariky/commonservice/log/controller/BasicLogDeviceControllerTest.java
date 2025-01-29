package com.matariky.commonservice.log.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.log.bean.BasicLogDevice;
import com.matariky.commonservice.log.service.BasicLogDeviceService;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;

@SpringBootTest
public class BasicLogDeviceControllerTest {

    @InjectMocks
    private BasicLogDeviceController basiclogdevicecontroller;

    @Mock
    private BasicLogDeviceService basicLogDeviceService;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        when(basicLogDeviceService.getBasicLogDeviceAll()).thenReturn(new PageInfo<BasicLogDevice>().getList());

        // When
        PageInfo<BasicLogDevice> result = basiclogdevicecontroller.list(request, new BasicLogDevice(), "tenantId", 1,
                10, "jwt");

        // Then
        assertNotNull(result);
        verify(basicLogDeviceService, times(1)).getBasicLogDeviceAll();
    }

    @Test
    void testDaclist() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("index", 1);
        params.put("perPage", 10);
        when(request.getHeader("id")).thenReturn("12345");
        when(commonDictTypeService.getDictTypeByKey(anyString(), anyString())).thenReturn(null);
        when(commonDictService.getCommonDictByIdTenantIdAndDictType(anyString(), anyString(), anyLong()))
                .thenReturn(null);
        when(basicLogDeviceService.getBasicLogDeviceDAC(anyMap(), any(HttpServletRequest.class))).thenReturn(null);
        when(basicLogDeviceService.getBasicLogDeviceDACCount(anyMap(), any(HttpServletRequest.class))).thenReturn(0L);

        // When
        Object result = basiclogdevicecontroller.daclist(request, params, "tenantId", "jwt");

        // Then
        assertNotNull(result);
    }

    @Test
    void testSave() {
        // Given
        BasicLogDevice device = new BasicLogDevice();
        when(basicLogDeviceService.createBasicLogDeviceWithOrg(any(BasicLogDevice.class),
                any(HttpServletRequest.class))).thenReturn(1);

        // When
        ResponseEntity<String> response = basiclogdevicecontroller.save(device, request, null);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
    }

    @Test
    void testUpdate() {
        // Given
        BasicLogDevice device = new BasicLogDevice();
        when(basicLogDeviceService.updateBasicLogDevice(any(BasicLogDevice.class))).thenReturn(1);

        // When
        ResponseEntity<String> response = basiclogdevicecontroller.update(device, request, null);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
    }

    @Test
    void testDelete() {
        // Given
        when(basicLogDeviceService.deleteById(anyLong())).thenReturn(true);

        // When
        ResponseEntity<String> response = basiclogdevicecontroller.del("1", request, null);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
    }

    @Test
    void testGetOne() {
        // Given
        when(basicLogDeviceService.selectById(anyLong())).thenReturn(new BasicLogDevice());

        // When
        Object result = basiclogdevicecontroller.getOne(1L, request, null);

        // Then
        assertNotNull(result);
    }
}
