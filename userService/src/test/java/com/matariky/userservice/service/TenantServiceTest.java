package com.matariky.userservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.matariky.userservice.bean.Tenant;
import com.matariky.userservice.mapper.TenantMapper;

@SpringBootTest
public class TenantServiceTest {

    @InjectMocks
    private TenantService tenantService;

    @Mock
    private TenantMapper tenantMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTenantById() {
        // Given
        String tenantId = "123l";
        Tenant tenant = new Tenant();
        tenant.setTenantCode(tenantId);
        when(tenantMapper.selectById(tenantId)).thenReturn(tenant);

        // When
        Tenant result = tenantService.getTenantById(tenantId);

        // Then
        assertNotNull(result);
        assertEquals(tenantId, result.getId());
        verify(tenantMapper, times(1)).selectById(tenantId);
    }

    @Test
    void testSelectBytenantCode() {
        // Given
        String tenantCode = "ABC123";
        Tenant tenant = new Tenant();
        tenant.setTenantCode(tenantCode);
        when(tenantMapper.selectBytenantCode(tenantCode)).thenReturn(tenant);

        // When
        Tenant result = tenantService.selectBytenantCode(tenantCode);

        // Then
        assertNotNull(result);
        assertEquals(tenantCode, result.getTenantCode());
        verify(tenantMapper, times(1)).selectBytenantCode(tenantCode);
    }

    // Add more test methods for other methods in TenantService if needed
}
