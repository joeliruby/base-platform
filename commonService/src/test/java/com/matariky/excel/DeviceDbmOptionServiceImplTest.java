package com.matariky.excel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.service.BasicBaseDeviceService;
import com.matariky.commonservice.base.service.ExcelService;
import com.matariky.commonservice.base.vo.DbmVO;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class DeviceDbmOptionServiceImplTest {

    @InjectMocks
    private DeviceDbmOptionServiceImpl devicedbmoptionserviceimpl;

    @Mock
    private ExcelService excelService;

    @Mock
    private BasicBaseDeviceService basicBaseDeviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(excelService.getBasicBaseDeviceService()).thenReturn(basicBaseDeviceService);
    }

    @Test
    void testGetSource() {
        // Given
        DbmVO dbmVO1 = new DbmVO();
        dbmVO1.setLabel("Option1");
        DbmVO dbmVO2 = new DbmVO();
        dbmVO2.setLabel("Option2");
        List<DbmVO> dbmOptionList = Arrays.asList(dbmVO1, dbmVO2);
        when(basicBaseDeviceService.getDbmOption()).thenReturn(dbmOptionList);

        // When
        String[] result = devicedbmoptionserviceimpl.getSource(excelService);

        // Then
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals("Option1", result[0]);
        assertEquals("Option2", result[1]);
    }

    @Test
    void testGetSourceEmptyList() {
        // Given
        when(basicBaseDeviceService.getDbmOption()).thenReturn(Arrays.asList());

        // When
        String[] result = devicedbmoptionserviceimpl.getSource(excelService);

        // Then
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    // Add more test methods for other scenarios if needed
}
