package com.matariky.commonservice.backup.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.commonservice.backup.bean.SystemMysqlBackups;

@SpringBootTest
public class SystemMysqlBackupsMapperTest {

    @InjectMocks
    private SystemMysqlBackupsMapper systemmysqlbackupsmapper;

    @Mock
    private Page<SystemMysqlBackups> mockPage;

    @Mock
    private SystemMysqlBackups mockBackup;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSelectBackupsList() {
        // Given
        when(systemmysqlbackupsmapper.selectBackupsList()).thenReturn(mockPage);

        // When
        Page<SystemMysqlBackups> result = systemmysqlbackupsmapper.selectBackupsList();

        // Then
        assertNotNull(result);
        assertEquals(mockPage, result);
    }

    @Test
    void testSelectListId() {
        // Given
        Long id = 1L;
        when(systemmysqlbackupsmapper.selectListId(id)).thenReturn(mockBackup);

        // When
        SystemMysqlBackups result = systemmysqlbackupsmapper.selectListId(id);

        // Then
        assertNotNull(result);
        assertEquals(mockBackup, result);
    }

    // Add more test methods for other methods in SystemMysqlBackupsMapper
}
