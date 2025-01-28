package com.matariky.userservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserRole;

@SpringBootTest
public class UserRoleMapperTest {

    @InjectMocks
    private UserRoleMapper userRoleMapper;

    @Mock
    private UserRoleMapper mockUserRoleMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserRoleAll() {
        // Given
        Map<String, Object> params = new HashMap<>();
        Page<UserRole> expectedPage = new Page<>();
        when(mockUserRoleMapper.getUserRoleAll(params)).thenReturn(expectedPage);

        // When
        Page<UserRole> result = userRoleMapper.getUserRoleAll(params);

        // Then
        assertEquals(expectedPage, result);
    }

    @Test
    void testCreateUserRole() {
        // Given
        UserRole userRole = new UserRole();
        when(mockUserRoleMapper.createUserRole(userRole)).thenReturn(1);

        // When
        int result = userRoleMapper.createUserRole(userRole);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateUserRole() {
        // Given
        UserRole userRole = new UserRole();
        when(mockUserRoleMapper.updateUserRole(userRole)).thenReturn(1);

        // When
        int result = userRoleMapper.updateUserRole(userRole);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelUserRoleById() {
        // Given
        int id = 1;
        when(mockUserRoleMapper.delUserRoleById(id)).thenReturn(1);

        // When
        int result = userRoleMapper.delUserRoleById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetUserRoleById() {
        // Given
        Long id = 1L;
        UserRole expectedUserRole = new UserRole();
        when(mockUserRoleMapper.getUserRoleById(id)).thenReturn(expectedUserRole);

        // When
        UserRole result = userRoleMapper.getUserRoleById(id);

        // Then
        assertEquals(expectedUserRole, result);
    }

    @Test
    void testGetRoleCountById() {
        // Given
        Long roleId = 1L;
        Integer expectedCount = 5;
        when(mockUserRoleMapper.getRoleCountById(roleId)).thenReturn(expectedCount);

        // When
        Integer result = userRoleMapper.getRoleCountById(roleId);

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testSelectRole() {
        // Given
        String tenantId = "tenant1";
        List<UserRole> expectedRoles = new ArrayList<>();
        when(mockUserRoleMapper.selectRole(tenantId)).thenReturn(expectedRoles);

        // When
        List<UserRole> result = userRoleMapper.selectRole(tenantId);

        // Then
        assertEquals(expectedRoles, result);
    }

    @Test
    void testUpdateDeleteTimeById() {
        // Given
        String[] ids = { "1", "2" };
        when(mockUserRoleMapper.updateDeleteTimeById(ids)).thenReturn(1);

        // When
        int result = userRoleMapper.updateDeleteTimeById(ids);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetRoleIdsByTenantIdAndRoleNames() {
        // Given
        String[] roleNames = { "role1", "role2" };
        String tenantId = "tenant1";
        List<Long> expectedRoleIds = Arrays.asList(1L, 2L);
        when(mockUserRoleMapper.getRoleIdsByTenantIdAndRoleNames(roleNames, tenantId)).thenReturn(expectedRoleIds);

        // When
        List<Long> result = userRoleMapper.getRoleIdsByTenantIdAndRoleNames(roleNames, tenantId);

        // Then
        assertEquals(expectedRoleIds, result);
    }

    @Test
    void testGetPermissionByRole() {
        // Given
        Long roleId = 1L;
        String tenantId = "tenant1";
        Long applicationId = 1L;
        List<TreeModel> expectedPermissions = new ArrayList<>();
        when(mockUserRoleMapper.getPermissionByRole(roleId, tenantId, applicationId)).thenReturn(expectedPermissions);

        // When
        List<TreeModel> result = userRoleMapper.getPermissionByRole(roleId, tenantId, applicationId);

        // Then
        assertEquals(expectedPermissions, result);
    }

    @Test
    void testDeleteByUserId() {
        // Given
        Long userId = 1L;
        doNothing().when(mockUserRoleMapper).deleteByUserId(userId);

        // When
        userRoleMapper.deleteByUserId(userId);

        // Then
        verify(mockUserRoleMapper, times(1)).deleteByUserId(userId);
    }

    @Test
    void testInsertUserRoleRelation() {
        // Given
        Long userId = 1L;
        Long roleId = 1L;
        doNothing().when(mockUserRoleMapper).insertUserRoleRelation(userId, roleId);

        // When
        userRoleMapper.insertUserRoleRelation(userId, roleId);

        // Then
        verify(mockUserRoleMapper, times(1)).insertUserRoleRelation(userId, roleId);
    }

    @Test
    void testGetPermissionIdByRoleId() {
        // Given
        Long roleId = 1L;
        List<Long> expectedPermissionIds = Arrays.asList(1L, 2L);
        when(mockUserRoleMapper.getPermissionIdByRoleId(roleId)).thenReturn(expectedPermissionIds);

        // When
        List<Long> result = userRoleMapper.getPermissionIdByRoleId(roleId);

        // Then
        assertEquals(expectedPermissionIds, result);
    }
}
