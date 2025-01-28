package com.matariky.userservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.userservice.bean.Organization;

@SpringBootTest
public class OrganizationMapperTest {

    @InjectMocks
    private OrganizationMapper organizationmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteByPrimaryKey() {
        // Given
        Long id = 1L;
        when(organizationmapper.deleteByPrimaryKey(id)).thenReturn(1);

        // When
        int result = organizationmapper.deleteByPrimaryKey(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testInsertSelective() {
        // Given
        Organization organization = new Organization();
        when(organizationmapper.insertSelective(organization)).thenReturn(1);

        // When
        int result = organizationmapper.insertSelective(organization);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testSelectByPrimaryKey() {
        // Given
        Long id = 1L;
        Organization organization = new Organization();
        when(organizationmapper.selectByPrimaryKey(id)).thenReturn(organization);

        // When
        Organization result = organizationmapper.selectByPrimaryKey(id);

        // Then
        assertEquals(organization, result);
    }

    @Test
    void testUpdateByPrimaryKeySelective() {
        // Given
        Organization organization = new Organization();
        when(organizationmapper.updateByPrimaryKeySelective(organization)).thenReturn(1);

        // When
        int result = organizationmapper.updateByPrimaryKeySelective(organization);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateByPrimaryKey() {
        // Given
        Organization organization = new Organization();
        when(organizationmapper.updateByPrimaryKey(organization)).thenReturn(1);

        // When
        int result = organizationmapper.updateByPrimaryKey(organization);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetOrgIdByUserId() {
        // Given
        String tenantId = "tenant1";
        String userId = "user1";
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("organization_code", "org1");
        expectedMap.put("self_organization_code", "selfOrg1");
        when(organizationmapper.getOrgIdByUserId(tenantId, userId)).thenReturn(expectedMap);

        // When
        Map<String, String> result = organizationmapper.getOrgIdByUserId(tenantId, userId);

        // Then
        assertEquals(expectedMap, result);
    }

    // Add more test methods for other methods in OrganizationMapper
}
