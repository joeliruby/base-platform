package com.matariky.clickhouse.logs.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.matariky.clickhouse.logs.entity.ServiceLog;
import com.matariky.clickhouse.logs.mapper.ServiceLogMapper;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;
import com.matariky.exception.QslException;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class ServiceLogServiceImplTest {

    @InjectMocks
    private ServiceLogServiceImpl servicelogserviceimpl;

    @Mock
    private ServiceLogMapper logsMapper;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private CommonDictTypeMapper commonDictTypeMapper;

    @Mock
    private CommonDictMapper commonDictMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTracesAll() {
        // Given
        ServiceLog logs = new ServiceLog();
        Integer index = 1;
        Integer perPage = 10;
        when(logsMapper.getAppTracesAll(any(ServiceLog.class))).thenReturn(new Page<>());

        // When
        Page<ServiceLog> result = servicelogserviceimpl.getTracesAll(logs, index, perPage);

        // Then
        assertNotNull(result);
        verify(logsMapper, times(1)).getAppTracesAll(any(ServiceLog.class));
    }

    @Test
    void testGetAppTracesCount() {
        // Given
        ServiceLog logs = new ServiceLog();
        when(logsMapper.getAppTracesCount(any(ServiceLog.class))).thenReturn(100L);

        // When
        Long count = servicelogserviceimpl.getAppTracesCount(logs);

        // Then
        assertEquals(100L, count);
        verify(logsMapper, times(1)).getAppTracesCount(any(ServiceLog.class));
    }

    @Test
    void testExport() {
        // Given
        ServiceLog logs = new ServiceLog();
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(logsMapper.getAppTracesCount(any(ServiceLog.class))).thenReturn(50L);
        CommonDictType dictType = new CommonDictType();
        dictType.setId(1L);
        when(commonDictTypeMapper.selectOne(any())).thenReturn(dictType);
        CommonDict maxRow = new CommonDict();
        maxRow.setDictValue("100");
        when(commonDictMapper.selectOne(any())).thenReturn(maxRow);

        // When
        assertDoesNotThrow(() -> servicelogserviceimpl.export(logs));

        // Then
        verify(logsMapper, times(1)).getAppTracesCount(any(ServiceLog.class));
        verify(commonDictTypeMapper, times(1)).selectOne(any());
        verify(commonDictMapper, times(1)).selectOne(any());
    }

    @Test
    void testExportThrowsExceptionWhenCountExceedsMaxRow() {
        // Given
        ServiceLog logs = new ServiceLog();
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(logsMapper.getAppTracesCount(any(ServiceLog.class))).thenReturn(150L);
        CommonDictType dictType = new CommonDictType();
        dictType.setId(1L);
        when(commonDictTypeMapper.selectOne(any())).thenReturn(dictType);
        CommonDict maxRow = new CommonDict();
        maxRow.setDictValue("100");
        when(commonDictMapper.selectOne(any())).thenReturn(maxRow);

        // When / Then
        assertThrows(QslException.class, () -> servicelogserviceimpl.export(logs));
    }

    // Add more test methods for other methods in ServiceLogServiceImpl
}
