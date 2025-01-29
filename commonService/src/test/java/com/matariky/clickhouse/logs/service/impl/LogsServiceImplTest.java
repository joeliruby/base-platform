package com.matariky.clickhouse.logs.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.clickhouse.logs.entity.Logs;
import com.matariky.clickhouse.logs.mapper.LogsMapper;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;
import com.matariky.exception.QslException;

@SpringBootTest
public class LogsServiceImplTest {

    @InjectMocks
    private LogsServiceImpl logsserviceimpl;

    @Mock
    private LogsMapper logsMapper;

    @Mock
    private HttpServletResponse response;

    @Mock
    private CommonDictTypeMapper commonDictTypeMapper;

    @Mock
    private CommonDictMapper commonDictMapper;

    @Mock
    private HttpServletRequest httpServletRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTracesAll() {
        // Given
        Logs logs = new Logs();
        logs.setTimeStartLong(1633046400000L); // Example timestamp
        logs.setTimeEndLong(1633132799000L); // Example timestamp
        Integer index = 1;
        Integer perPage = 10;

        Page<Logs> mockPage = new Page<>();
        mockPage.add(new Logs());
        when(logsMapper.getAppTracesAll(any(Logs.class))).thenReturn(mockPage);

        // When
        Page<Logs> result = logsserviceimpl.getTracesAll(logs, index, perPage);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetAppTracesCount() {
        // Given
        Logs logs = new Logs();
        when(logsMapper.getAppTracesCount(any(Logs.class))).thenReturn(100L);

        // When
        Long count = logsserviceimpl.getAppTracesCount(logs);

        // Then
        assertNotNull(count);
        assertEquals(100L, count);
    }

    @Test
    void testExport() {
        // Given
        Logs logs = new Logs();
        Integer index = 1;
        Integer perPage = 10;

        CommonDictType dictType = new CommonDictType();
        dictType.setId(1L);
        when(commonDictTypeMapper.selectOne(any())).thenReturn(dictType);

        CommonDict maxRow = new CommonDict();
        maxRow.setDictValue("100");
        when(commonDictMapper.selectOne(any())).thenReturn(maxRow);

        Page<Logs> mockPage = new Page<>();
        mockPage.add(new Logs());
        when(logsMapper.getAppTracesAll(any(Logs.class))).thenReturn(mockPage);

        // When / Then
        assertDoesNotThrow(() -> logsserviceimpl.export(logs, index, perPage));
    }

    @Test
    void testExportThrowsExceptionWhenPerPageExceedsMaxRow() {
        // Given
        Logs logs = new Logs();
        Integer index = 1;
        Integer perPage = 200;

        CommonDictType dictType = new CommonDictType();
        dictType.setId(1L);
        when(commonDictTypeMapper.selectOne(any())).thenReturn(dictType);

        CommonDict maxRow = new CommonDict();
        maxRow.setDictValue("100");
        when(commonDictMapper.selectOne(any())).thenReturn(maxRow);

        // When / Then
        assertThrows(QslException.class, () -> logsserviceimpl.export(logs, index, perPage));
    }

    // Add more test methods for other methods in LogsServiceImpl
}
