package com.matariky.commonservice.error.excel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import org.mockito.Mock;

@SpringBootTest
public class LongTimeConverterTimeTest {

    @InjectMocks
    private LongTimeConverterTime longtimeconvertertime;

    @Mock
    private ExcelContentProperty contentProperty;

    @Mock
    private GlobalConfiguration globalConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertToExcelData() throws Exception {
        // Given
        Long value = 1633072800000L; // Example timestamp
        String expectedDate = "2021-10-01 00:00:00";

        // When
        WriteCellData<?> result = longtimeconvertertime.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result.getStringValue()).isEqualTo(expectedDate);
    }

    @Test
    void testConvertToExcelDataWithNullValue() throws Exception {
        // Given
        Long value = null;

        // When
        WriteCellData<?> result = longtimeconvertertime.convertToExcelData(value, contentProperty, globalConfiguration);

        // Then
        assertThat(result).isNull();
    }

    // Add more test methods for other scenarios if needed
}
