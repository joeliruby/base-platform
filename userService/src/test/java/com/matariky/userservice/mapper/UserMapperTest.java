package com.matariky.userservice.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.matariky.userservice.bean.User;

@SpringBootTest
public class UserMapperTest {

    @InjectMocks
    private UserMapper usermapper;

    @Mock
    private UserMapper mockUserMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserAll() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");
        when(mockUserMapper.getUserAll(params)).thenReturn(new Page<>());

        // When
        Page<User> result = usermapper.getUserAll(params);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testCreateUser() {
        // Given
        User user = new User();
        when(mockUserMapper.createUser(user)).thenReturn(1);

        // When
        int result = usermapper.createUser(user);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testUpdateUser() {
        // Given
        User user = new User();
        when(mockUserMapper.updateUser(user)).thenReturn(1);

        // When
        int result = usermapper.updateUser(user);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testDelUserById() {
        // Given
        int userId = 1;
        when(mockUserMapper.delUserById(userId)).thenReturn(1);

        // When
        int result = usermapper.delUserById(userId);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testGetUserById() {
        // Given
        Long userId = 1L;
        User user = new User();
        when(mockUserMapper.getUserById(userId)).thenReturn(user);

        // When
        User result = usermapper.getUserById(userId);

        // Then
        assertThat(result).isEqualTo(user);
    }

    // Add more test methods for other methods in UserMapper
}
