package com.matariky.commonservice.log.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.mockito.Mock;
import com.matariky.commonservice.log.bean.BasicLogDevice;
import com.matariky.commonservice.log.mapper.BasicLogDeviceMapper;
import com.matariky.constant.PermissionConstant;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicLogDeviceServiceTest {

    @InjectMocks
    private BasicLogDeviceService basicLogDeviceService;

    @Mock
    private BasicLogDeviceMapper basicLogDeviceMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicLogDeviceAll() {
        // Given
        List<BasicLogDevice> expectedDevices = List.of(new BasicLogDevice(), new BasicLogDevice());
        when(basicLogDeviceMapper.getBasicLogDeviceAll()).thenReturn(expectedDevices);

        // When
        List<BasicLogDevice> actualDevices = basicLogDeviceService.getBasicLogDeviceAll();

        // Then
        assertEquals(expectedDevices, actualDevices);
    }

    @Test
    void testCreateBasicLogDevice() {
        // Given
        BasicLogDevice device = new BasicLogDevice();
        when(basicLogDeviceMapper.createBasicLogDevice(device)).thenReturn(1);

        // When
        int result = basicLogDeviceService.createBasicLogDevice(device);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicLogDevice() {
        // Given
        BasicLogDevice device = new BasicLogDevice();
        when(basicLogDeviceMapper.updateById(device)).thenReturn(1);

        // When
        int result = basicLogDeviceService.updateBasicLogDevice(device);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicLogDeviceById() {
        // Given
        int id = 1;
        when(basicLogDeviceMapper.delBasicLogDeviceById(id)).thenReturn(1);

        // When
        int result = basicLogDeviceService.delBasicLogDeviceById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicLogDeviceById() {
        // Given
        int id = 1;
        BasicLogDevice expectedDevice = new BasicLogDevice();
        when(basicLogDeviceMapper.getBasicLogDeviceById(id)).thenReturn(expectedDevice);

        // When
        BasicLogDevice actualDevice = basicLogDeviceService.getBasicLogDeviceById(id);

        // Then
        assertEquals(expectedDevice, actualDevice);
    }

    @Test
    void testGetBasicLogDeviceDAC() {
        // Given
        Map<String, Object> params = Map.of("key", "value");
        List<BasicLogDevice> expectedDevices = List.of(new BasicLogDevice(), new BasicLogDevice());
        when(basicLogDeviceMapper.getBasicLogDeviceDAC(params)).thenReturn(expectedDevices);

        // When
        List<BasicLogDevice> actualDevices = basicLogDeviceService.getBasicLogDeviceDAC(params, request);

        // Then
        assertEquals(expectedDevices, actualDevices);
    }

    @Test
    void testGetBasicLogDeviceDACCount() {
        // Given
        Map<String, Object> params = Map.of("strategyCode", PermissionConstant.COMMON_DATA_ACCESS_PRIVATE);
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(basicLogDeviceMapper.getBasicLogDeviceDACCount(params)).thenReturn(10L);

        // When
        Long count = basicLogDeviceService.getBasicLogDeviceDACCount(params, request);

        // Then
        assertEquals(10L, count);
    }
}
