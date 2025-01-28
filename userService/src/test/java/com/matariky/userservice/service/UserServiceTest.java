package com.matariky.userservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.userservice.bean.User;
import com.matariky.userservice.mapper.UserMapper;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUsername() {
        // Given
        String loginName = "testUser";
        User expectedUser = new User();
        expectedUser.setLoginName(loginName);
        when(userMapper.selectByName(loginName)).thenReturn(expectedUser);

        // When
        User actualUser = userService.findByUsername(loginName);

        // Then
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testFindUserById() {
        // Given
        String userId = "1";
        User expectedUser = new User();
        expectedUser.setId(Long.valueOf(userId));
        when(userMapper.selectByPrimaryKey(Long.valueOf(userId))).thenReturn(expectedUser);

        // When
        User actualUser = userService.findUserById(userId);

        // Then
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testCreateUserAndTenantAndOrganization() {
        // Given
        Map<String, Object> map = new HashMap<>();
        int expectedResult = 1;
        when(userMapper.createUserAndTenantAndOrganization(map)).thenReturn(expectedResult);

        // When
        int actualResult = userService.createUserAndTenantAndOrganization(map);

        // Then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testSearchByUserNamePrefix() {
        // Given
        String tenantId = "1";
        String userNamePrefix = "test";
        List<User> expectedUsers = List.of(new User());
        when(userMapper.searchByUserNamePrefix(tenantId, userNamePrefix)).thenReturn(expectedUsers);

        // When
        List<User> actualUsers = userService.searchByUserNamePrefix(tenantId, userNamePrefix);

        // Then
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void testGetUserAll() {
        // Given
        Map<String, Object> map = new HashMap<>();
        Page<User> expectedPage = mock(Page.class);
        when(userMapper.getUserAll(map)).thenReturn(expectedPage);

        // When
        Page<User> actualPage = userService.getUserAll(map);

        // Then
        assertEquals(expectedPage, actualPage);
    }

    @Test
    void testCreateUser() {
        // Given
        User user = new User();
        int expectedResult = 1;
        when(userMapper.createUser(user)).thenReturn(expectedResult);

        // When
        int actualResult = userService.createUser(user);

        // Then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testUpdateUser() {
        // Given
        User user = new User();
        int expectedResult = 1;
        when(userMapper.updateUser(user)).thenReturn(expectedResult);

        // When
        int actualResult = userService.updateUser(user);

        // Then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testDelUserById() {
        // Given
        int userId = 1;
        int expectedResult = 1;
        when(userMapper.delUserById(userId)).thenReturn(expectedResult);

        // When
        int actualResult = userService.delUserById(userId);

        // Then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetUserById() {
        // Given
        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setId(userId);
        when(userMapper.getUserById(userId)).thenReturn(expectedUser);

        // When
        User actualUser = userService.getUserById(userId);

        // Then
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testSelectByMap() {
        // Given
        Map<String, Object> columnMap = new HashMap<>();
        List<User> expectedUsers = List.of(new User());
        when(userMapper.selectByMap(columnMap)).thenReturn(expectedUsers);

        // When
        List<User> actualUsers = userService.selectByMap(columnMap);

        // Then
        assertEquals(expectedUsers, actualUsers);
    }

    // Add more test methods for other methods in UserService
}
