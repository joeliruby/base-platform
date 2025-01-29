package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseDevice;
import com.matariky.commonservice.base.vo.*;
import com.matariky.model.QueryDataIsolation;
import java.util.List;

@SpringBootTest
public class BasicBaseDeviceMapperTest {

    @InjectMocks
    private BasicBaseDeviceMapper basicbasedevicemapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDeviceAll() {
        // Given
        BasicBaseDeviceListVO vo = new BasicBaseDeviceListVO();
        // Initialize your test data and mocks here

        // When
        List<BasicBaseDeviceInfoVO> result = basicbasedevicemapper.getBasicBaseDeviceAll(vo);

        // Then
        assertNotNull(result);
        // Add more assertions based on expected results
    }

    @Test
    void testCreateBasicBaseDevice() {
        // Given
        BasicBaseDevice device = new BasicBaseDevice();
        // Initialize your test data and mocks here

        // When
        int result = basicbasedevicemapper.createBasicBaseDevice(device);

        // Then
        assertEquals(1, result);
        // Add more assertions based on expected results
    }

    @Test
    void testSelectCountFromPrint() {
        // Given
        Long deviceId = 1L;
        // Initialize your test data and mocks here

        // When
        int result = basicbasedevicemapper.selectCountFromPrint(deviceId);

        // Then
        assertEquals(0, result);
        // Add more assertions based on expected results
    }

    @Test
    void testDelBasicBaseDeviceById() {
        // Given
        Long id = 1L;
        // Initialize your test data and mocks here

        // When
        int result = basicbasedevicemapper.delBasicBaseDeviceById(id);

        // Then
        assertEquals(1, result);
        // Add more assertions based on expected results
    }

    @Test
    void testGetPrintOptionList() {
        // Given
        QueryDataIsolation queryDataIsolation = new QueryDataIsolation();
        // Initialize your test data and mocks here

        // When
        List<PrintOptionInfo> result = basicbasedevicemapper.getPrintOptionList(queryDataIsolation);

        // Then
        assertNotNull(result);
        // Add more assertions based on expected results
    }

    @Test
    void testGetCodeOptionList() {
        // Given
        CodeOptionListVO vo = new CodeOptionListVO();
        // Initialize your test data and mocks here

        // When
        List<DeviceCodeInfo> result = basicbasedevicemapper.getCodeOptionList(vo);

        // Then
        assertNotNull(result);
        // Add more assertions based on expected results
    }

    @Test
    void testSelectPrint() {
        // Given
        String mac = "00:00:00:00:00:00";
        // Initialize your test data and mocks here

        // When
        BasicBaseDevice result = basicbasedevicemapper.selectPrint(mac);

        // Then
        assertNotNull(result);
        // Add more assertions based on expected results
    }

    @Test
    void testSelectPrintByCode() {
        // Given
        String code = "deviceCode";
        // Initialize your test data and mocks here

        // When
        BasicBaseDevice result = basicbasedevicemapper.selectPrintByCode(code);

        // Then
        assertNotNull(result);
        // Add more assertions based on expected results
    }
}
