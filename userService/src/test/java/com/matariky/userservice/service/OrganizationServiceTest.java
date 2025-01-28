package com.matariky.userservice.service;

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
import com.matariky.userservice.mapper.OrganizationMapper;

@SpringBootTest
public class OrganizationServiceTest {

    @InjectMocks
    private OrganizationService organizationService;

    @Mock
    private OrganizationMapper organizationMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrgIdByUserId() {
        // Given
        String tenantId = "tenant1";
        String userId = "user1";
        Map<String, String> expectedOrgIdMap = new HashMap<>();
        expectedOrgIdMap.put("orgId", "12345");

        when(organizationMapper.getOrgIdByUserId(tenantId, userId)).thenReturn(expectedOrgIdMap);

        // When
        Map<String, String> actualOrgIdMap = organizationService.getOrgIdByUserId(tenantId, userId);

        // Then
        assertEquals(expectedOrgIdMap, actualOrgIdMap);
        verify(organizationMapper, times(1)).getOrgIdByUserId(tenantId, userId);
    }

    // Add more test methods for other methods in OrganizationService if needed
}
