package com.matariky.clickhouse.logs.excel;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

@SpringBootTest
public class LogTypeConverterTest {

    @InjectMocks
    private LogTypeConverter logTypeConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertToExcelData_NetworkLog() throws Exception {
        // Given
        String value = "1";
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        var result = logTypeConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("Network Log");
    }

    @Test
    void testConvertToExcelData_AppCrashLog() throws Exception {
        // Given
        String value = "2";
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        var result = logTypeConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("App Crash Log");
    }

    @Test
    void testConvertToExcelData_PrintDataLog() throws Exception {
        // Given
        String value = "3";
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        var result = logTypeConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("Print Data Log");
    }

    @Test
    void testConvertToExcelData_DeviceLog() throws Exception {
        // Given
        String value = "4";
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        var result = logTypeConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("Device Log");
    }

    @Test
    void testConvertToExcelData_UnknownLog() throws Exception {
        // Given
        String value = "5";
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        var result = logTypeConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("");
    }
}
