package com.matariky.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.matariky.userservice.bean.UserGroup;
import com.matariky.userservice.mapper.UserGroupMapper;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class UserGroupServiceTest {

    @InjectMocks
    private UserGroupService userGroupService;

    @Mock
    private UserGroupMapper userGroupMapper;

    @Mock
    private TokenService tokenService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserGroupAll() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        Page<UserGroup> expectedPage = mock(Page.class);
        when(userGroupMapper.getUserGroupAll(params)).thenReturn(expectedPage);

        // When
        Page<UserGroup> result = userGroupService.getUserGroupAll(params);

        // Then
        assertEquals(expectedPage, result);
    }

    @Test
    void testCreateUserGroup() {
        // Given
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName("Test Group");
        userGroup.setRoleIdList(Collections.emptyList());
        userGroup.setUserIdList(Collections.emptyList());
        String tenantId = "tenantId";
        when(request.getHeader("Authorization")).thenReturn("Bearer token");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn(tenantId);
        when(userGroupMapper.selectCount(any())).thenReturn(0L);

        // When
        userGroupService.createUserGroup(userGroup);

        // Then
        verify(userGroupMapper).createUserGroup(userGroup);
    }

    @Test
    void testUpdateUserGroup() {
        // Given
        UserGroup userGroup = new UserGroup();
        userGroup.setId(1L);
        userGroup.setRoleIdList(Collections.emptyList());
        userGroup.setUserIdList(Collections.emptyList());

        // When
        userGroupService.updateUserGroup(userGroup);

        // Then
        verify(userGroupMapper).updateUserGroup(userGroup);
        verify(tokenService).expireAllLoginUsersAfterCredentialChanges(userGroup);
    }

    @Test
    void testGetGroupsByUserId() {
        // Given
        Long userId = 1L;
        String tenantId = "tenantId";
        Set<String> expectedGroups = Collections.singleton("Group1");
        when(userGroupMapper.getGroupsByUserId(userId, tenantId)).thenReturn(expectedGroups);

        // When
        Set<String> result = userGroupService.getGroupsByUserId(userId, tenantId);

        // Then
        assertEquals(expectedGroups, result);
    }

    @Test
    void testDelUserGroupById() {
        // Given
        int id = 1;
        when(userGroupMapper.delUserGroupById(id)).thenReturn(1);

        // When
        int result = userGroupService.delUserGroupById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetUserGroupById() {
        // Given
        Long id = 1L;
        UserGroup expectedUserGroup = new UserGroup();
        when(userGroupMapper.getUserGroupById(id)).thenReturn(expectedUserGroup);

        // When
        UserGroup result = userGroupService.getUserGroupById(id);

        // Then
        assertEquals(expectedUserGroup, result);
    }

    // Add more test methods for other methods in UserGroupService
}
