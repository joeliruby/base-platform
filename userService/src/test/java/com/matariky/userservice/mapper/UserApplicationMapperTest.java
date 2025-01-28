package com.matariky.userservice.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.Map;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.userservice.bean.UserApplication;

@SpringBootTest
public class UserApplicationMapperTest {

    @InjectMocks
    private UserApplicationMapper userapplicationmapper;

    @Mock
    private UserApplicationMapper mockUserApplicationMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserApplicationAll() {
        // Given
        Map<String, Object> params = new HashMap<>();
        Page<UserApplication> expectedPage = new Page<>();
        when(mockUserApplicationMapper.getUserApplicationAll(params)).thenReturn(expectedPage);

        // When
        Page<UserApplication> result = userapplicationmapper.getUserApplicationAll(params);

        // Then
        assertEquals(expectedPage, result);
    }

    @Test
    void testCreateUserApplication() {
        // Given
        UserApplication userApplication = new UserApplication();
        when(mockUserApplicationMapper.createUserApplication(userApplication)).thenReturn(1);

        // When
        int result = userapplicationmapper.createUserApplication(userApplication);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateUserApplication() {
        // Given
        UserApplication userApplication = new UserApplication();
        when(mockUserApplicationMapper.updateUserApplication(userApplication)).thenReturn(1);

        // When
        int result = userapplicationmapper.updateUserApplication(userApplication);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelUserApplicationById() {
        // Given
        int id = 1;
        when(mockUserApplicationMapper.delUserApplicationById(id)).thenReturn(1);

        // When
        int result = userapplicationmapper.delUserApplicationById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetUserApplicationById() {
        // Given
        Long id = 1L;
        UserApplication expectedUserApplication = new UserApplication();
        when(mockUserApplicationMapper.getUserApplicationById(id)).thenReturn(expectedUserApplication);

        // When
        UserApplication result = userapplicationmapper.getUserApplicationById(id);

        // Then
        assertEquals(expectedUserApplication, result);
    }

    // Add more test methods for other methods in UserApplicationMapper
}
