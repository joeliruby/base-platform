package com.matariky.userservice.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.userservice.bean.Permission;
import com.matariky.userservice.dto.PermissionDTO;
import com.matariky.userservice.dto.PermissionInfoVO;
import org.mockito.Mock;
import java.util.*;

@SpringBootTest
public class PermissionMapperTest {

    @InjectMocks
    private PermissionMapper permissionMapper;

    @Mock
    private PermissionMapper mockPermissionMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDirectlyAssignedPermissionIdsByUserId() {
        // Given
        Long userId = 1L;
        String tenantId = "tenant1";
        Long applicationId = 1L;
        List<Permission> expectedPermissions = new ArrayList<>();
        when(mockPermissionMapper.getDirectlyAssignedPermissionIdsByUserId(userId, tenantId, applicationId))
                .thenReturn(expectedPermissions);

        // When
        List<Permission> actualPermissions = permissionMapper.getDirectlyAssignedPermissionIdsByUserId(userId, tenantId,
                applicationId);

        // Then
        assertEquals(expectedPermissions, actualPermissions);
    }

    @Test
    void testGetRoleAssignedPermissionIdsByUserId() {
        // Given
        Long userId = 1L;
        String tenantId = "tenant1";
        Long applicationId = 1L;
        List<Permission> expectedPermissions = new ArrayList<>();
        when(mockPermissionMapper.getRoleAssignedPermissionIdsByUserId(userId, tenantId, applicationId))
                .thenReturn(expectedPermissions);

        // When
        List<Permission> actualPermissions = permissionMapper.getRoleAssignedPermissionIdsByUserId(userId, tenantId,
                applicationId);

        // Then
        assertEquals(expectedPermissions, actualPermissions);
    }

    @Test
    void testGetGroupAssignedPermissionIdsByUserId() {
        // Given
        Long userId = 1L;
        String tenantId = "tenant1";
        Long applicationId = 1L;
        List<Permission> expectedPermissions = new ArrayList<>();
        when(mockPermissionMapper.getGroupAssignedPermissionIdsByUserId(userId, tenantId, applicationId))
                .thenReturn(expectedPermissions);

        // When
        List<Permission> actualPermissions = permissionMapper.getGroupAssignedPermissionIdsByUserId(userId, tenantId,
                applicationId);

        // Then
        assertEquals(expectedPermissions, actualPermissions);
    }

    @Test
    void testGetPermissionList() {
        // Given
        Long userId = 1L;
        String tenantId = "tenant1";
        List<PermissionDTO> expectedPermissions = new ArrayList<>();
        when(mockPermissionMapper.getPermissionList(userId, tenantId)).thenReturn(expectedPermissions);

        // When
        List<PermissionDTO> actualPermissions = permissionMapper.getPermissionList(userId, tenantId);

        // Then
        assertEquals(expectedPermissions, actualPermissions);
    }

    @Test
    void testGetPermissionsById() {
        // Given
        String id = "1";
        PermissionInfoVO expectedPermissionInfo = new PermissionInfoVO();
        when(mockPermissionMapper.getPermissionsById(id)).thenReturn(expectedPermissionInfo);

        // When
        PermissionInfoVO actualPermissionInfo = permissionMapper.getPermissionsById(id);

        // Then
        assertEquals(expectedPermissionInfo, actualPermissionInfo);
    }

    @Test
    void testCreateResourceAllocationToUser() {
        // Given
        Long userId = 1L;
        long permissionId = 1L;

        // When
        permissionMapper.createResourceAllocationToUser(userId, permissionId);

        // Then
        verify(mockPermissionMapper, times(1)).createResourceAllocationToUser(userId, permissionId);
    }

    @Test
    void testCreateResourceAllocationToGroup() {
        // Given
        Long groupId = 1L;
        long permissionId = 1L;

        // When
        permissionMapper.createResourceAllocationToGroup(groupId, permissionId);

        // Then
        verify(mockPermissionMapper, times(1)).createResourceAllocationToGroup(groupId, permissionId);
    }

    @Test
    void testCreateResourceAllocationToRole() {
        // Given
        Long roleId = 1L;
        long permissionId = 1L;

        // When
        permissionMapper.createResourceAllocationToRole(roleId, permissionId);

        // Then
        verify(mockPermissionMapper, times(1)).createResourceAllocationToRole(roleId, permissionId);
    }

    @Test
    void testDeleteRRolePermission() {
        // Given
        Long roleId = 1L;

        // When
        permissionMapper.deleteRRolePermission(roleId);

        // Then
        verify(mockPermissionMapper, times(1)).deleteRRolePermission(roleId);
    }

    @Test
    void testDeleteRUserPermission() {
        // Given
        Long userId = 1L;

        // When
        permissionMapper.deleteRUserPermission(userId);

        // Then
        verify(mockPermissionMapper, times(1)).deleteRUserPermission(userId);
    }
}
