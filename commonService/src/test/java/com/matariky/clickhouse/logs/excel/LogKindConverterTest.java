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
public class LogKindConverterTest {

    @InjectMocks
    private LogKindConverter logKindConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertToExcelData_Unspecified() throws Exception {
        // Given
        Integer value = 0;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        var result = logKindConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("Unspecified");
    }

    @Test
    void testConvertToExcelData_Internal() throws Exception {
        // Given
        Integer value = 1;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        var result = logKindConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("Internal");
    }

    @Test
    void testConvertToExcelData_Server() throws Exception {
        // Given
        Integer value = 2;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        var result = logKindConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("Server");
    }

    @Test
    void testConvertToExcelData_Client() throws Exception {
        // Given
        Integer value = 3;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        var result = logKindConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("Client");
    }

    @Test
    void testConvertToExcelData_MessageProducer() throws Exception {
        // Given
        Integer value = 4;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        var result = logKindConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("Message Producer");
    }

    @Test
    void testConvertToExcelData_MessageConsumer() throws Exception {
        // Given
        Integer value = 5;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        var result = logKindConverter.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("Message Consumer");
    }
}
