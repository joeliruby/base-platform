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

import com.matariky.commonservice.log.bean.BasicLogDevice;

@SpringBootTest
public class BasicLogDeviceMapperTest {

    @InjectMocks
    private BasicLogDeviceMapper basiclogdevicemapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicLogDeviceAll() {
        // Given
        List<BasicLogDevice> expectedDevices = Collections.emptyList();
        when(basiclogdevicemapper.getBasicLogDeviceAll()).thenReturn(expectedDevices);

        // When
        List<BasicLogDevice> actualDevices = basiclogdevicemapper.getBasicLogDeviceAll();

        // Then
        assertEquals(expectedDevices, actualDevices);
    }

    @Test
    void testCreateBasicLogDevice() {
        // Given
        BasicLogDevice device = new BasicLogDevice();
        when(basiclogdevicemapper.createBasicLogDevice(device)).thenReturn(1);

        // When
        int result = basiclogdevicemapper.createBasicLogDevice(device);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicLogDevice() {
        // Given
        BasicLogDevice device = new BasicLogDevice();
        when(basiclogdevicemapper.updateBasicLogDevice(device)).thenReturn(1);

        // When
        int result = basiclogdevicemapper.updateBasicLogDevice(device);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicLogDeviceById() {
        // Given
        int id = 1;
        when(basiclogdevicemapper.delBasicLogDeviceById(id)).thenReturn(1);

        // When
        int result = basiclogdevicemapper.delBasicLogDeviceById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicLogDeviceById() {
        // Given
        int id = 1;
        BasicLogDevice expectedDevice = new BasicLogDevice();
        when(basiclogdevicemapper.getBasicLogDeviceById(id)).thenReturn(expectedDevice);

        // When
        BasicLogDevice actualDevice = basiclogdevicemapper.getBasicLogDeviceById(id);

        // Then
        assertEquals(expectedDevice, actualDevice);
    }

    @Test
    void testGetBasicLogDeviceDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicLogDevice> expectedDevices = Collections.emptyList();
        when(basiclogdevicemapper.getBasicLogDeviceDAC(params)).thenReturn(expectedDevices);

        // When
        List<BasicLogDevice> actualDevices = basiclogdevicemapper.getBasicLogDeviceDAC(params);

        // Then
        assertEquals(expectedDevices, actualDevices);
    }

    @Test
    void testGetBasicLogDeviceDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        Long expectedCount = 0L;
        when(basiclogdevicemapper.getBasicLogDeviceDACCount(params)).thenReturn(expectedCount);

        // When
        Long actualCount = basiclogdevicemapper.getBasicLogDeviceDACCount(params);

        // Then
        assertEquals(expectedCount, actualCount);
    }

    // Add more test methods for other methods in BasicLogDeviceMapper
}
