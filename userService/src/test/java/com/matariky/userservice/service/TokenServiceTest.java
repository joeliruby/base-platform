package com.matariky.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.constant.TokenConstant;
import com.matariky.exception.QslException;
import com.matariky.redis.RedisUtils;
import com.matariky.userservice.bean.Permission;
import com.matariky.userservice.bean.User;
import com.matariky.userservice.bean.UserApplication;
import com.matariky.userservice.bean.UserGroup;
import com.matariky.userservice.bean.UserRole;
import com.matariky.userservice.bean.UserTenant;

@SpringBootTest
public class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private PermissionService permissionService;

    @Mock
    private UserTenantService tenantService;

    @Mock
    private UserApplicationService applicationService;

    @Mock
    private UserService userService;

    @Mock
    private RedisUtils redisUtils;

    @Mock
    private UserGroupService userGroupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetToken() throws QslException {
        // Given
        User user = new User();
        user.setId(1L);
        user.setApplicationId(1L);
        user.setTenantId("tenant_1");
        user.setLoginName("testUser");
        user.setLocale("en");

        Permission permission = new Permission();
        permission.setId(1L);
        Set<Permission> permissionSet = new HashSet<>();
        permissionSet.add(permission);

        List<Long> appPermList = Arrays.asList(1L);
        when(applicationService.getPermissionIdList(1L)).thenReturn(appPermList);
        when(userService.isAdmin(1L)).thenReturn(1);
        when(permissionService.getPermissionsByUserId(1L, "tenant_1", 1L)).thenReturn(permissionSet);

        UserTenant tenant = new UserTenant();
        tenant.setTenantName("TenantName");
        when(tenantService.selectBytenantCode("tenant_1")).thenReturn(tenant);

        UserApplication userApplication = new UserApplication();
        userApplication.setActivityTimeout(1800L);
        when(applicationService.selectById(1L)).thenReturn(userApplication);

        Set<String> groupSet = new HashSet<>();
        groupSet.add("group_1");
        when(userGroupService.getGroupsByUserId(1L, "tenant_1")).thenReturn(groupSet);

        // When
        Map<String, String> tokenMap = tokenService.getToken(user);

        // Then
        assertNotNull(tokenMap);
        assertEquals("testUser", tokenMap.get("loginName"));
        assertEquals("TenantName", tokenMap.get("tenantName"));
    }

    @Test
    void testUpdateLoginInfo() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setLoginCount(5);

        // When
        tokenService.updateLoginInfo(user);

        // Then
        assertEquals(6, user.getLoginCount());
        verify(userService, times(1)).updateById(user);
    }

    @Test
    void testExpireAllLoginUsersAfterCredentialChanges_UserTenant() {
        // Given
        UserTenant userTenant = new UserTenant();
        userTenant.setTenantCode("tenant_1");

        User user = new User();
        user.setId(1L);
        List<User> usersToExpire = Arrays.asList(user);
        when(userService.selectByMap(anyMap())).thenReturn(usersToExpire);

        // When
        tokenService.expireAllLoginUsersAfterCredentialChanges(userTenant);

        // Then
        verify(redisUtils, times(1)).expire(TokenConstant.LAST_ACCESS_TIME + "_1", 1L);
    }

    @Test
    void testExpireAllLoginUsersAfterCredentialChanges_UserRole() {
        // Given
        UserRole userRole = new UserRole();
        userRole.setId(1L);

        List<Long> usersToExpire = Arrays.asList(1L);
        when(userService.getUsersByRoleId(1L)).thenReturn(usersToExpire);

        // When
        tokenService.expireAllLoginUsersAfterCredentialChanges(userRole);

        // Then
        verify(redisUtils, times(1)).expire(TokenConstant.LAST_ACCESS_TIME + "_1", 1L);
    }

    @Test
    void testExpireAllLoginUsersAfterCredentialChanges_UserGroup() {
        // Given
        UserGroup userGroup = new UserGroup();
        userGroup.setId(1L);

        List<Long> usersToExpire = Arrays.asList(1L);
        when(userGroupService.getUserIdList(1L)).thenReturn(usersToExpire);

        // When
        tokenService.expireAllLoginUsersAfterCredentialChanges(userGroup);

        // Then
        verify(redisUtils, times(1)).expire(TokenConstant.LAST_ACCESS_TIME + "_1", 1L);
    }
}
