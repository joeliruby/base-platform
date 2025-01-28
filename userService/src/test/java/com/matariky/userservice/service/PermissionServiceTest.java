package com.matariky.userservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.matariky.userservice.mapper.PermissionMapper;
import com.matariky.userservice.bean.Permission;
import java.util.*;

@SpringBootTest
public class PermissionServiceTest {

    @InjectMocks
    private PermissionService permissionService;

    @Mock
    private PermissionMapper permissionMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPermissionsByUserId() {
        // Given
        Long userId = 1L;
        String tenantId = "tenant1";
        Long applicationId = 1L;
        List<Permission> expectedPermissions = new ArrayList<>();
        when(permissionMapper.getDirectlyAssignedPermissionIdsByUserId(userId, tenantId, applicationId))
                .thenReturn(expectedPermissions);
        when(permissionMapper.getRoleAssignedPermissionIdsByUserId(userId, tenantId, applicationId))
                .thenReturn(expectedPermissions);
        when(permissionMapper.getGroupAssignedPermissionIdsByUserId(userId, tenantId, applicationId))
                .thenReturn(expectedPermissions);
        when(permissionMapper.getGroupRoleAssignedPermissionIdsByUserId(userId, tenantId, applicationId))
                .thenReturn(expectedPermissions);

        // When
        Set<Permission> actualPermissions = permissionService.getPermissionsByUserId(userId, tenantId, applicationId);

        // Then
        assertEquals(expectedPermissions, actualPermissions);
    }

    @Test
    void testGetOrderSuitePermissionByUserId() {
        // Given
        Long applicationId = 1L;
        String orderTenantId = "tenant1";
        List<Permission> expectedPermissions = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("applicationId", applicationId);
        params.put("orderTenantId", orderTenantId);
        when(permissionMapper.getOrderSuitePermissionByUserId(params)).thenReturn(expectedPermissions);

        // When
        Set<Permission> actualPermissions = permissionService.getOrderSuitePermissionByUserId(applicationId,
                orderTenantId);

        // Then
        assertEquals(expectedPermissions, actualPermissions);
    }

    @Test
    void testCreateResourceAllocationToUser() {
        // Given
        Long userId = 1L;
        long permissionId = 1L;

        // When
        permissionService.createResourceAllocationToUser(userId, permissionId);

        // Then
        verify(permissionMapper, times(1)).createResourceAllocationToUser(userId, permissionId);
    }

    @Test
    void testFindPermissionsByPermissionNamePrefix() {
        // Given
        String tenantId = "tenant1";
        Long applicationId = 1L;
        String permissionName = "perm";
        List<Map<Long, String>> expectedPermissions = new ArrayList<>();
        when(permissionMapper.findPermissionsByPermissionNamePrefix(tenantId, applicationId, permissionName))
                .thenReturn(expectedPermissions);

        // When
        List<Map<Long, String>> actualPermissions = permissionService.findPermissionsByPermissionNamePrefix(tenantId,
                applicationId, permissionName);

        // Then
        assertEquals(expectedPermissions, actualPermissions);
    }

    @Test
    void testUpdatePermission() {
        // Given
        Permission permission = new Permission();
        when(permissionMapper.updateById(permission)).thenReturn(1);

        // When
        int result = permissionService.updatePermission(permission);

        // Then
        assertEquals(1, result);
    }

    // Add more test methods for other methods in PermissionService
}
