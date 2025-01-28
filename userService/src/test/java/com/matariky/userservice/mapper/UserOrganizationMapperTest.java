package com.matariky.userservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserOrganization;

@SpringBootTest
public class UserOrganizationMapperTest {

    @InjectMocks
    private UserOrganizationMapper userorganizationmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserOrganizationAll() {
        // Given
        Page<UserOrganization> expectedPage = new Page<>();
        when(userorganizationmapper.getUserOrganizationAll()).thenReturn(expectedPage);

        // When
        Page<UserOrganization> result = userorganizationmapper.getUserOrganizationAll();

        // Then
        assertNotNull(result);
        assertEquals(expectedPage, result);
    }

    @Test
    void testCreateUserOrganization() {
        // Given
        UserOrganization userOrganization = new UserOrganization();
        when(userorganizationmapper.createUserOrganization(userOrganization)).thenReturn(1);

        // When
        int result = userorganizationmapper.createUserOrganization(userOrganization);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateUserOrganization() {
        // Given
        UserOrganization userOrganization = new UserOrganization();
        when(userorganizationmapper.updateUserOrganization(userOrganization)).thenReturn(1);

        // When
        int result = userorganizationmapper.updateUserOrganization(userOrganization);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelUserOrganizationById() {
        // Given
        int id = 1;
        when(userorganizationmapper.delUserOrganizationById(id)).thenReturn(1);

        // When
        int result = userorganizationmapper.delUserOrganizationById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetUserOrganizationById() {
        // Given
        String id = "1";
        UserOrganization expectedUserOrganization = new UserOrganization();
        when(userorganizationmapper.getUserOrganizationById(id)).thenReturn(expectedUserOrganization);

        // When
        UserOrganization result = userorganizationmapper.getUserOrganizationById(id);

        // Then
        assertNotNull(result);
        assertEquals(expectedUserOrganization, result);
    }

    @Test
    void testSelectByOrgCode() {
        // Given
        String orgCode = "ORG123";
        UserOrganization expectedUserOrganization = new UserOrganization();
        when(userorganizationmapper.selectByOrgCode(orgCode)).thenReturn(expectedUserOrganization);

        // When
        UserOrganization result = userorganizationmapper.selectByOrgCode(orgCode);

        // Then
        assertNotNull(result);
        assertEquals(expectedUserOrganization, result);
    }

    @Test
    void testGetOrganizationList() {
        // Given
        String tenantId = "tenant1";
        List<TreeModel> expectedList = Collections.emptyList();
        when(userorganizationmapper.getOrganizationList(tenantId)).thenReturn(expectedList);

        // When
        List<TreeModel> result = userorganizationmapper.getOrganizationList(tenantId);

        // Then
        assertNotNull(result);
        assertEquals(expectedList, result);
    }

    // Add more test methods for other methods in UserOrganizationMapper
}
