package com.matariky.userservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.matariky.userservice.bean.UserApplication;
import com.matariky.userservice.mapper.UserApplicationMapper;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class UserApplicationServiceTest {

    @InjectMocks
    private UserApplicationService userApplicationService;

    @Mock
    private UserApplicationMapper userApplicationMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserApplicationAll() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        when(userApplicationMapper.getUserApplicationAll(params)).thenReturn(null);

        // When
        var result = userApplicationService.getUserApplicationAll(params);

        // Then
        assertNull(result);
        verify(userApplicationMapper).getUserApplicationAll(params);
    }

    @Test
    void testCreateUserApplication() {
        // Given
        UserApplication userApplication = new UserApplication();
        when(userApplicationMapper.createUserApplication(userApplication)).thenReturn(1);

        // When
        int result = userApplicationService.createUserApplication(userApplication);

        // Then
        assertEquals(1, result);
        verify(userApplicationMapper).createUserApplication(userApplication);
    }

    @Test
    void testUpdateUserApplication() {
        // Given
        UserApplication userApplication = new UserApplication();
        when(userApplicationMapper.updateUserApplication(userApplication)).thenReturn(1);

        // When
        int result = userApplicationService.updateUserApplication(userApplication);

        // Then
        assertEquals(1, result);
        verify(userApplicationMapper).updateUserApplication(userApplication);
    }

    @Test
    void testDelUserApplicationById() {
        // Given
        int id = 1;
        when(userApplicationMapper.delUserApplicationById(id)).thenReturn(1);

        // When
        int result = userApplicationService.delUserApplicationById(id);

        // Then
        assertEquals(1, result);
        verify(userApplicationMapper).delUserApplicationById(id);
    }

    @Test
    void testGetUserApplicationById() {
        // Given
        Long id = 1L;
        UserApplication userApplication = new UserApplication();
        when(userApplicationMapper.getUserApplicationById(id)).thenReturn(userApplication);

        // When
        UserApplication result = userApplicationService.getUserApplicationById(id);

        // Then
        assertEquals(userApplication, result);
        verify(userApplicationMapper).getUserApplicationById(id);
    }

    @Test
    void testGetDefaultApplicationByTenantIdandUserId() {
        // Given
        String tenantId = "tenantId";
        Long userId = 1L;
        Long expectedApplicationId = 1L;
        when(userApplicationMapper.getDefaultApplicationByTenantIdandUserId(tenantId, userId))
                .thenReturn(expectedApplicationId);

        // When
        Long result = userApplicationService.getDefaultApplicationByTenantIdandUserId(tenantId, userId);

        // Then
        assertEquals(expectedApplicationId, result);
        verify(userApplicationMapper).getDefaultApplicationByTenantIdandUserId(tenantId, userId);
    }

    @Test
    void testSelectApplication() {
        // Given
        String tenantId = "tenantId";
        List<UserApplication> expectedApplications = Collections.emptyList();
        when(userApplicationMapper.selectApplication(tenantId)).thenReturn(expectedApplications);

        // When
        List<UserApplication> result = userApplicationService.selectApplication(tenantId);

        // Then
        assertEquals(expectedApplications, result);
        verify(userApplicationMapper).selectApplication(tenantId);
    }

    // Add more test methods for other methods in UserApplicationService
}
