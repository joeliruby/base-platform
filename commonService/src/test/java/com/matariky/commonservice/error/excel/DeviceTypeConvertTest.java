package com.matariky.commonservice.error.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;

@SpringBootTest
public class DeviceTypeConvertTest {

    @InjectMocks
    private DeviceTypeConvert deviceTypeConvert;

    @Mock
    private ApplicationContext applicationContext;

    @Mock
    private CommonDictTypeMapper commonDictTypeMapper;

    @Mock
    private CommonDictMapper commonDictMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(applicationContext.getBean("commonDictTypeMapper", CommonDictTypeMapper.class))
                .thenReturn(commonDictTypeMapper);
        when(applicationContext.getBean("commonDictMapper", CommonDictMapper.class)).thenReturn(commonDictMapper);
    }

    @Test
    void testConvertToExcelData_withValidValue() throws Exception {
        // Given
        String value = "someValue";
        CommonDictType dictType = new CommonDictType();
        dictType.setId(1L);
        when(commonDictTypeMapper.selectOne(any())).thenReturn(dictType);

        CommonDict commonDict = new CommonDict();
        commonDict.setDictValue(value);
        commonDict.setDictName("someName");
        List<CommonDict> commonDictList = Collections.singletonList(commonDict);
        when(commonDictMapper.selectList(any())).thenReturn(commonDictList);

        // When
        WriteCellData<?> result = deviceTypeConvert.convertToExcelData(value, mock(ExcelContentProperty.class),
                mock(GlobalConfiguration.class));

        // Then
        assertEquals("someName", result.getStringValue());
    }

    @Test
    void testConvertToExcelData_withInvalidValue() throws Exception {
        // Given
        String value = "invalidValue";
        CommonDictType dictType = new CommonDictType();
        dictType.setId(1L);
        when(commonDictTypeMapper.selectOne(any())).thenReturn(dictType);

        when(commonDictMapper.selectList(any())).thenReturn(Collections.emptyList());

        // When
        WriteCellData<?> result = deviceTypeConvert.convertToExcelData(value, mock(ExcelContentProperty.class),
                mock(GlobalConfiguration.class));

        // Then
        assertEquals("", result.getStringValue());
    }

    @Test
    void testConvertToExcelData_withNullDictType() throws Exception {
        // Given
        String value = "someValue";
        when(commonDictTypeMapper.selectOne(any())).thenReturn(null);

        // When
        WriteCellData<?> result = deviceTypeConvert.convertToExcelData(value, mock(ExcelContentProperty.class),
                mock(GlobalConfiguration.class));

        // Then
        assertEquals("", result.getStringValue());
    }

    // Add more test methods for other scenarios if needed
}
