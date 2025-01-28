package com.matariky.userservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.Map;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.userservice.bean.UserTenant;
import com.matariky.userservice.mapper.UserTenantMapper;

@SpringBootTest
public class UserTenantServiceTest {

    @InjectMocks
    private UserTenantService userTenantService;

    @Mock
    private UserTenantMapper userTenantMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserTenantAll() {
        // Given
        Map<String, Object> params = new HashMap<>();
        Page<UserTenant> expectedPage = mock(Page.class);
        when(userTenantMapper.getUserTenantAll(params)).thenReturn(expectedPage);

        // When
        Page<UserTenant> result = userTenantService.getUserTenantAll(params);

        // Then
        assertEquals(expectedPage, result);
        verify(userTenantMapper).getUserTenantAll(params);
    }

    @Test
    void testCreateUserTenant() {
        // Given
        UserTenant userTenant = new UserTenant();
        when(userTenantMapper.createUserTenant(userTenant)).thenReturn(1);

        // When
        int result = userTenantService.createUserTenant(userTenant);

        // Then
        assertEquals(1, result);
        verify(userTenantMapper).createUserTenant(userTenant);
    }

    @Test
    void testUpdateUserTenant() {
        // Given
        UserTenant userTenant = new UserTenant();
        when(userTenantMapper.updateUserTenant(userTenant)).thenReturn(1);

        // When
        int result = userTenantService.updateUserTenant(userTenant);

        // Then
        assertEquals(1, result);
        verify(userTenantMapper).updateUserTenant(userTenant);
    }

    @Test
    void testDelUserTenantById() {
        // Given
        int id = 1;
        when(userTenantMapper.delUserTenantById(id)).thenReturn(1);

        // When
        int result = userTenantService.delUserTenantById(id);

        // Then
        assertEquals(1, result);
        verify(userTenantMapper).delUserTenantById(id);
    }

    @Test
    void testGetUserTenantById() {
        // Given
        String id = "1";
        UserTenant expectedUserTenant = new UserTenant();
        when(userTenantMapper.getUserTenantById(id)).thenReturn(expectedUserTenant);

        // When
        UserTenant result = userTenantService.getUserTenantById(id);

        // Then
        assertEquals(expectedUserTenant, result);
        verify(userTenantMapper).getUserTenantById(id);
    }

    // Add more test methods for other methods in UserTenantService
}
