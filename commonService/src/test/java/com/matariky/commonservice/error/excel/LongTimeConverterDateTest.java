package com.matariky.commonservice.error.excel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

@SpringBootTest
public class LongTimeConverterDateTest {

    @InjectMocks
    private LongTimeConverterDate longtimeconverterdate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertToExcelData() throws Exception {
        // Given
        Long value = 1633072800000L; // Example timestamp
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        WriteCellData<?> result = longtimeconverterdate.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo("2021-10-01");
    }

    @Test
    void testConvertToExcelDataWithNullValue() throws Exception {
        // Given
        Long value = null;
        ExcelContentProperty contentProperty = mock(ExcelContentProperty.class);
        GlobalConfiguration globalConfiguration = mock(GlobalConfiguration.class);

        // When
        WriteCellData<?> result = longtimeconverterdate.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result).isNull();
    }

    // Add more test methods for other scenarios if needed
}
