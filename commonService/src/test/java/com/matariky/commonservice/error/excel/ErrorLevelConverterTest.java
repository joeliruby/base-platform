package com.matariky.commonservice.error.excel;

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
public class ErrorLevelConverterTest {

    @InjectMocks
    private ErrorLevelConverter errorLevelConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertToExcelData_Info() throws Exception {
        // Given
        Integer value = 1;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        WriteCellData<?> result = errorLevelConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("info");
    }

    @Test
    void testConvertToExcelData_Debug() throws Exception {
        // Given
        Integer value = 2;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        WriteCellData<?> result = errorLevelConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("debug");
    }

    @Test
    void testConvertToExcelData_Error() throws Exception {
        // Given
        Integer value = 3;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        WriteCellData<?> result = errorLevelConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("error");
    }

    @Test
    void testConvertToExcelData_Fatal() throws Exception {
        // Given
        Integer value = 4;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        WriteCellData<?> result = errorLevelConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("fatal");
    }

    @Test
    void testConvertToExcelData_Unknown() throws Exception {
        // Given
        Integer value = 5;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        WriteCellData<?> result = errorLevelConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("");
    }
}
