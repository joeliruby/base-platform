package com.matariky.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.service.BasicBaseDeviceTypeService;
import com.matariky.commonservice.base.service.ExcelService;
import com.matariky.commonservice.base.vo.DeviceTypeOption;

@SpringBootTest
public class TypeNameOptionServiceImplTest {

    @InjectMocks
    private TypeNameOptionServiceImpl typenameoptionserviceimpl;

    @Mock
    private ExcelService excelService;

    @Mock
    private BasicBaseDeviceTypeService basicBaseDeviceTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSource() {
        // Given
        DeviceTypeOption option1 = new DeviceTypeOption();
        option1.setLabel("Label1");
        DeviceTypeOption option2 = new DeviceTypeOption();
        option2.setLabel("Label2");
        List<DeviceTypeOption> optionList = Arrays.asList(option1, option2);
        when(excelService.getBasicBaseDevicetypeService()).thenReturn(basicBaseDeviceTypeService);
        when(basicBaseDeviceTypeService.getOptionList()).thenReturn(optionList);

        // When
        String[] result = typenameoptionserviceimpl.getSource(excelService);

        // Then
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals("Label1", result[0]);
        assertEquals("Label2", result[1]);
    }

    @Test
    void testGetSourceEmptyList() {
        // Given
        List<DeviceTypeOption> optionList = Arrays.asList();
        when(excelService.getBasicBaseDevicetypeService()).thenReturn(basicBaseDeviceTypeService);
        when(basicBaseDeviceTypeService.getOptionList()).thenReturn(optionList);

        // When
        String[] result = typenameoptionserviceimpl.getSource(excelService);

        // Then
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    // Add more test methods for other scenarios in TypeNameOptionServiceImpl
}
