package com.matariky.userservice.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.userservice.bean.UserGroup;

@SpringBootTest
public class UserGroupMapperTest {

    @InjectMocks
    private UserGroupMapper userGroupMapper;

    @Mock
    private UserGroupMapper mockUserGroupMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserGroupAll() {
        // Given
        Map<String, Object> params = new HashMap<>();
        Page<UserGroup> expectedPage = new Page<>();
        when(mockUserGroupMapper.getUserGroupAll(params)).thenReturn(expectedPage);

        // When
        Page<UserGroup> result = userGroupMapper.getUserGroupAll(params);

        // Then
        assertEquals(expectedPage, result);
    }

    @Test
    void testCreateUserGroup() {
        // Given
        UserGroup userGroup = new UserGroup();
        when(mockUserGroupMapper.createUserGroup(userGroup)).thenReturn(1);

        // When
        int result = userGroupMapper.createUserGroup(userGroup);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateUserGroup() {
        // Given
        UserGroup userGroup = new UserGroup();
        when(mockUserGroupMapper.updateUserGroup(userGroup)).thenReturn(1);

        // When
        int result = userGroupMapper.updateUserGroup(userGroup);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelUserGroupById() {
        // Given
        int id = 1;
        when(mockUserGroupMapper.delUserGroupById(id)).thenReturn(1);

        // When
        int result = userGroupMapper.delUserGroupById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetUserGroupById() {
        // Given
        Long id = 1L;
        UserGroup expectedUserGroup = new UserGroup();
        when(mockUserGroupMapper.getUserGroupById(id)).thenReturn(expectedUserGroup);

        // When
        UserGroup result = userGroupMapper.getUserGroupById(id);

        // Then
        assertEquals(expectedUserGroup, result);
    }

    @Test
    void testSelectGroup() {
        // Given
        String tenantId = "tenant1";
        List<UserGroup> expectedGroups = new ArrayList<>();
        when(mockUserGroupMapper.selectGroup(tenantId)).thenReturn(expectedGroups);

        // When
        List<UserGroup> result = userGroupMapper.selectGroup(tenantId);

        // Then
        assertEquals(expectedGroups, result);
    }

    @Test
    void testDeleteUserByGroupIds() {
        // Given
        Long[] groupIds = { 1L, 2L };
        doNothing().when(mockUserGroupMapper).deleteUserByGroupIds(groupIds);

        // When
        userGroupMapper.deleteUserByGroupIds(groupIds);

        // Then
        verify(mockUserGroupMapper, times(1)).deleteUserByGroupIds(groupIds);
    }

    @Test
    void testSavaRGoupAndUser() {
        // Given
        Long groupId = 1L;
        Long userId = 1L;
        doNothing().when(mockUserGroupMapper).savaRGoupAndUser(groupId, userId);

        // When
        userGroupMapper.savaRGoupAndUser(groupId, userId);

        // Then
        verify(mockUserGroupMapper, times(1)).savaRGoupAndUser(groupId, userId);
    }

    // Add more test methods for other methods in UserGroupMapper
}
