package com.matariky.commonservice.sqlog.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.commonservice.sqlog.bean.CommonSqlLog;

@SpringBootTest
public class CommonSqlLogMapperTest {

    @InjectMocks
    private CommonSqlLogMapper commonsqllogmapper;

    @Mock
    private CommonSqlLogMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonSqlLogAll() {
        Page<CommonSqlLog> page = new Page<>();
        when(mockMapper.getCommonSqlLogAll()).thenReturn(page);
        Page<CommonSqlLog> result = commonsqllogmapper.getCommonSqlLogAll();
        assertNotNull(result);
        verify(mockMapper).getCommonSqlLogAll();
    }

    @Test
    void testGetCommonSqlLogAllCount() {
        when(mockMapper.getCommonSqlLogAllCount()).thenReturn(10);
        int count = commonsqllogmapper.getCommonSqlLogAllCount();
        assertEquals(10, count);
        verify(mockMapper).getCommonSqlLogAllCount();
    }

    @Test
    void testCreateCommonSqlLog() {
        CommonSqlLog log = new CommonSqlLog();
        when(mockMapper.createCommonSqlLog(log)).thenReturn(1);
        int result = commonsqllogmapper.createCommonSqlLog(log);
        assertEquals(1, result);
        verify(mockMapper).createCommonSqlLog(log);
    }

    @Test
    void testUpdateCommonSqlLog() {
        CommonSqlLog log = new CommonSqlLog();
        when(mockMapper.updateCommonSqlLog(log)).thenReturn(1);
        int result = commonsqllogmapper.updateCommonSqlLog(log);
        assertEquals(1, result);
        verify(mockMapper).updateCommonSqlLog(log);
    }

    @Test
    void testDelCommonSqlLogById() {
        when(mockMapper.delCommonSqlLogById(1)).thenReturn(1);
        int result = commonsqllogmapper.delCommonSqlLogById(1);
        assertEquals(1, result);
        verify(mockMapper).delCommonSqlLogById(1);
    }

    @Test
    void testGetCommonSqlLogById() {
        CommonSqlLog log = new CommonSqlLog();
        when(mockMapper.getCommonSqlLogById(1)).thenReturn(log);
        CommonSqlLog result = commonsqllogmapper.getCommonSqlLogById(1);
        assertNotNull(result);
        verify(mockMapper).getCommonSqlLogById(1);
    }

    @Test
    void testInsert() {
        CommonSqlLog log = new CommonSqlLog();
        when(mockMapper.insert(log)).thenReturn(1);
        int result = commonsqllogmapper.insert(log);
        assertEquals(1, result);
        verify(mockMapper).insert(log);
    }

    @Test
    void testDeleteById() {
        when(mockMapper.deleteById(1)).thenReturn(1);
        int result = commonsqllogmapper.deleteById(1);
        assertEquals(1, result);
        verify(mockMapper).deleteById(1);
    }

    @Test
    void testSelectById() {
        CommonSqlLog log = new CommonSqlLog();
        when(mockMapper.selectById(1)).thenReturn(log);
        CommonSqlLog result = commonsqllogmapper.selectById(1);
        assertNotNull(result);
        verify(mockMapper).selectById(1);
    }

    // Add more test methods for other methods in CommonSqlLogMapper
}
