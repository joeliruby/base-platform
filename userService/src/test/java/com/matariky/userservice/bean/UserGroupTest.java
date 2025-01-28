package com.matariky.userservice.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserGroupTest {

    @InjectMocks
    private UserGroup usergroup;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGroupName() {
        // Given
        usergroup.setGroupName("Test Group");

        // When
        String groupName = usergroup.getGroupName();

        // Then
        assertEquals("Test Group", groupName);
    }

    @Test
    void testSetGroupName() {
        // Given
        String groupName = "New Group";

        // When
        usergroup.setGroupName(groupName);

        // Then
        assertEquals(groupName, usergroup.getGroupName());
    }

    @Test
    void testGetTenantId() {
        // Given
        usergroup.setTenantId("Tenant123");

        // When
        String tenantId = usergroup.getTenantId();

        // Then
        assertEquals("Tenant123", tenantId);
    }

    @Test
    void testSetTenantId() {
        // Given
        String tenantId = "Tenant456";

        // When
        usergroup.setTenantId(tenantId);

        // Then
        assertEquals(tenantId, usergroup.getTenantId());
    }

    @Test
    void testGetDescription() {
        // Given
        usergroup.setDescription("This is a test group");

        // When
        String description = usergroup.getDescription();

        // Then
        assertEquals("This is a test group", description);
    }

    @Test
    void testSetDescription() {
        // Given
        String description = "Updated description";

        // When
        usergroup.setDescription(description);

        // Then
        assertEquals(description, usergroup.getDescription());
    }

    // Add more test methods for other getters and setters in UserGroup
}
