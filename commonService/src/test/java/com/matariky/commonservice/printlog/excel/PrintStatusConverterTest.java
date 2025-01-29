package com.matariky.commonservice.printlog.excel;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

@SpringBootTest
public class PrintStatusConverterTest {

    @InjectMocks
    private PrintStatusConverter printStatusConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertToExcelData_StartPrint() throws Exception {
        // Given
        Integer value = 1;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        WriteCellData<?> result = printStatusConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo(" Start  Print ");
    }

    @Test
    void testConvertToExcelData_Printed() throws Exception {
        // Given
        Integer value = 2;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        WriteCellData<?> result = printStatusConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo(" Printed ");
    }

    @Test
    void testConvertToExcelData_OtherValue() throws Exception {
        // Given
        Integer value = 3;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        WriteCellData<?> result = printStatusConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("");
    }
}
