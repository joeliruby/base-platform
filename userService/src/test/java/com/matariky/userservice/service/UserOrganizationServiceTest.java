package com.matariky.userservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.userservice.bean.UserOrganization;
import com.matariky.userservice.mapper.UserOrganizationMapper;

@SpringBootTest
public class UserOrganizationServiceTest {

    @InjectMocks
    private UserOrganizationService userOrganizationService;

    @Mock
    private UserOrganizationMapper userOrganizationMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserOrganizationAll() {
        // Given
        Page<UserOrganization> expectedPage = mock(Page.class);
        when(userOrganizationMapper.getUserOrganizationAll()).thenReturn(expectedPage);

        // When
        Page<UserOrganization> result = userOrganizationService.getUserOrganizationAll();

        // Then
        assertEquals(expectedPage, result);
    }

    @Test
    void testGetUserOrganizationAllCount() {
        // Given
        int expectedCount = 10;
        when(userOrganizationMapper.getUserOrganizationAllCount()).thenReturn(expectedCount);

        // When
        int result = userOrganizationService.getUserOrganizationAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateUserOrganization() {
        // Given
        UserOrganization userOrganization = new UserOrganization();
        when(userOrganizationMapper.createUserOrganization(userOrganization)).thenReturn(1);

        // When
        int result = userOrganizationService.createUserOrganization(userOrganization);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateUserOrganization() {
        // Given
        UserOrganization userOrganization = new UserOrganization();
        userOrganization.setParentId(1L);
        userOrganization.setUserGroupId(1L);
        List<UserOrganization> subOrgList = new ArrayList<>();
        when(userOrganizationMapper.selectList(any())).thenReturn(subOrgList);
        when(userOrganizationMapper.updateUserOrganization(userOrganization)).thenReturn(1);

        // When
        int result = userOrganizationService.updateUserOrganization(userOrganization);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelUserOrganizationById() {
        // Given
        int id = 1;
        when(userOrganizationMapper.delUserOrganizationById(id)).thenReturn(1);

        // When
        int result = userOrganizationService.delUserOrganizationById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetUserOrganizationById() {
        // Given
        String id = "1";
        UserOrganization expectedUserOrganization = new UserOrganization();
        when(userOrganizationMapper.getUserOrganizationById(id)).thenReturn(expectedUserOrganization);

        // When
        UserOrganization result = userOrganizationService.getUserOrganizationById(id);

        // Then
        assertEquals(expectedUserOrganization, result);
    }

    @Test
    void testSelectByOrgCode() {
        // Given
        String orgCode = "ORG001";
        UserOrganization expectedUserOrganization = new UserOrganization();
        when(userOrganizationMapper.selectByOrgCode(orgCode)).thenReturn(expectedUserOrganization);

        // When
        UserOrganization result = userOrganizationService.selectByOrgCode(orgCode);

        // Then
        assertEquals(expectedUserOrganization, result);
    }

    // Add more test methods for other methods in UserOrganizationService
}
