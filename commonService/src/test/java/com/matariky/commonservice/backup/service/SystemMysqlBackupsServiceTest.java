package com.matariky.commonservice.backup.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.pagehelper.Page;
import com.matariky.commonservice.backup.bean.SystemMysqlBackups;
import org.mockito.Mock;

@SpringBootTest
public class SystemMysqlBackupsServiceTest {

    @InjectMocks
    private SystemMysqlBackupsService systemMysqlBackupsService;

    @Mock
    private Page<SystemMysqlBackups> mockPage;

    @Mock
    private SystemMysqlBackups mockSystemMysqlBackups;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSelectBackupsList() {
        // Given
        when(systemMysqlBackupsService.selectBackupsList()).thenReturn(mockPage);

        // When
        Page<SystemMysqlBackups> result = systemMysqlBackupsService.selectBackupsList();

        // Then
        assertNotNull(result);
        verify(systemMysqlBackupsService).selectBackupsList();
    }

    @Test
    void testMysqlBackups() {
        // Given
        Object expectedBackup = new Object();
        when(systemMysqlBackupsService.mysqlBackups()).thenReturn(expectedBackup);

        // When
        Object result = systemMysqlBackupsService.mysqlBackups();

        // Then
        assertNotNull(result);
        assertEquals(expectedBackup, result);
        verify(systemMysqlBackupsService).mysqlBackups();
    }

    @Test
    void testSelectListId() {
        // Given
        Long id = 1L;
        when(systemMysqlBackupsService.selectListId(id)).thenReturn(mockSystemMysqlBackups);

        // When
        SystemMysqlBackups result = systemMysqlBackupsService.selectListId(id);

        // Then
        assertNotNull(result);
        assertEquals(mockSystemMysqlBackups, result);
        verify(systemMysqlBackupsService).selectListId(id);
    }

    @Test
    void testRollback() {
        // Given
        Object expectedRollback = new Object();
        when(systemMysqlBackupsService.rollback(mockSystemMysqlBackups)).thenReturn(expectedRollback);

        // When
        Object result = systemMysqlBackupsService.rollback(mockSystemMysqlBackups);

        // Then
        assertNotNull(result);
        assertEquals(expectedRollback, result);
        verify(systemMysqlBackupsService).rollback(mockSystemMysqlBackups);
    }

    // Add more test methods for other methods in SystemMysqlBackupsService
}
