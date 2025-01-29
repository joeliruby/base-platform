package com.matariky.commonservice.backup.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.github.pagehelper.Page;
import com.matariky.commonservice.backup.bean.SystemMysqlBackups;
import com.matariky.commonservice.backup.service.SystemMysqlBackupsService;
import com.matariky.exception.QslException;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class SystemMysqlBackupsControllerTest {

    @InjectMocks
    private SystemMysqlBackupsController systemMysqlBackupsController;

    @Mock
    private SystemMysqlBackupsService systemMysqlBackupsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBackupsList() {
        // Given
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "some-jwt-token";
        when(systemMysqlBackupsService.selectBackupsList()).thenReturn(new Page<>());

        // When
        AjaxResult result = (AjaxResult) systemMysqlBackupsController.backupsList(pageIndex, perPage, jwt);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testMysqlBackups() {
        // Given
        when(systemMysqlBackupsService.mysqlBackups()).thenReturn(new Object());

        // When
        AjaxResult result = (AjaxResult) systemMysqlBackupsController.mysqlBackups();

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testRollback() {
        // Given
        Map<String, Object> map = new HashMap<>();
        map.put("id", "1");
        when(systemMysqlBackupsService.selectListId(1L)).thenReturn(new SystemMysqlBackups());
        when(systemMysqlBackupsService.rollback(any(SystemMysqlBackups.class))).thenReturn(new Object());

        // When
        AjaxResult result = (AjaxResult) systemMysqlBackupsController.rollback(map);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testRollbackWithInvalidId() {
        // Given
        Map<String, Object> map = new HashMap<>();
        map.put("id", "");

        // When / Then
        assertThrows(QslException.class, () -> systemMysqlBackupsController.rollback(map));
    }

    // Add more test methods for other methods in SystemMysqlBackupsController
}
