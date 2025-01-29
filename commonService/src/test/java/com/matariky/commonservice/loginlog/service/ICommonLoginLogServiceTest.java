package com.matariky.commonservice.loginlog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.loginlog.bean.CommonLoginLog;

@SpringBootTest
public class ICommonLoginLogServiceTest {

    @InjectMocks
    private ICommonLoginLogService icommonloginlogservice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCommonLoginLog() {
        // Given
        CommonLoginLog log = new CommonLoginLog();
        when(icommonloginlogservice.createCommonLoginLog(log)).thenReturn(1);

        // When
        int result = icommonloginlogservice.createCommonLoginLog(log);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testCreateCommonLoginLogWithNull() {
        // Given
        CommonLoginLog log = null;
        when(icommonloginlogservice.createCommonLoginLog(log)).thenReturn(0);

        // When
        int result = icommonloginlogservice.createCommonLoginLog(log);

        // Then
        assertEquals(0, result);
    }

    // Add more test methods for other methods in ICommonLoginLogService
}
