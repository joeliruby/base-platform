package com.matariky.userservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.matariky.userservice.mapper.UserRoleMapper;
import com.matariky.userservice.mapper.PermissionMapper;
import com.matariky.userservice.mapper.UserGroupMapper;
import com.matariky.userservice.bean.UserRole;
import com.matariky.exception.QslException;
import java.util.Collections;
import java.util.Map;

@SpringBootTest
public class UserRoleServiceTest {

    @InjectMocks
    private UserRoleService userRoleService;

    @Mock
    private UserRoleMapper userRoleMapper;

    @Mock
    private TokenService tokenService;

    @Mock
    private PermissionMapper permissionMapper;

    @Mock
    private UserGroupMapper userGroupMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserRoleAll() {
        // Given
        Map<String, Object> map = Collections.emptyMap();
        when(userRoleMapper.getUserRoleAll(map)).thenReturn(null);

        // When
        var result = userRoleService.getUserRoleAll(map);

        // Then
        assertNull(result);
        verify(userRoleMapper).getUserRoleAll(map);
    }

    @Test
    void testCreateUserRole() {
        // Given
        UserRole userRole = new UserRole();
        when(userRoleMapper.createUserRole(userRole)).thenReturn(1);

        // When
        int result = userRoleService.createUserRole(userRole);

        // Then
        assertEquals(1, result);
        verify(userRoleMapper).createUserRole(userRole);
    }

    @Test
    void testUpdateUserRole() {
        // Given
        UserRole userRole = new UserRole();
        when(userRoleMapper.updateUserRole(userRole)).thenReturn(1);

        // When
        int result = userRoleService.updateUserRole(userRole);

        // Then
        assertEquals(1, result);
        verify(userRoleMapper).updateUserRole(userRole);
        verify(tokenService).expireAllLoginUsersAfterCredentialChanges(userRole);
    }

    @Test
    void testDelUserRoleById() {
        // Given
        int id = 1;
        when(userRoleMapper.delUserRoleById(id)).thenReturn(1);

        // When
        int result = userRoleService.delUserRoleById(id);

        // Then
        assertEquals(1, result);
        verify(userRoleMapper).delUserRoleById(id);
    }

    @Test
    void testGetUserRoleById() {
        // Given
        Long id = 1L;
        UserRole userRole = new UserRole();
        when(userRoleMapper.getUserRoleById(id)).thenReturn(userRole);

        // When
        UserRole result = userRoleService.getUserRoleById(id);

        // Then
        assertEquals(userRole, result);
        verify(userRoleMapper).getUserRoleById(id);
    }

    @Test
    void testUpdateDeleteTimeById() {
        // Given
        String[] ids = { "1" };
        when(userGroupMapper.getRoleIdCount(1L)).thenReturn(0);
        when(userRoleMapper.getRoleCountById(1L)).thenReturn(0);
        when(userRoleMapper.updateDeleteTimeById(ids)).thenReturn(1);

        // When
        int result = userRoleService.updateDeleteTimeById(ids);

        // Then
        assertEquals(1, result);
        verify(userGroupMapper).getRoleIdCount(1L);
        verify(userRoleMapper).getRoleCountById(1L);
        verify(userRoleMapper).updateDeleteTimeById(ids);
    }

    @Test
    void testUpdateDeleteTimeByIdThrowsException() {
        // Given
        String[] ids = { "1" };
        when(userGroupMapper.getRoleIdCount(1L)).thenReturn(1);

        // When / Then
        assertThrows(QslException.class, () -> userRoleService.updateDeleteTimeById(ids));
        verify(userGroupMapper).getRoleIdCount(1L);
    }

    // Add more test methods for other methods in UserRoleService
}
