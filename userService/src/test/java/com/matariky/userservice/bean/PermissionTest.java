package com.matariky.userservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PermissionTest {

    @InjectMocks
    private Permission permission;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCompareTo_SameSortOrder() {
        // Given
        Permission otherPermission = new Permission();
        otherPermission.setSortOrder(1L);
        permission.setSortOrder(1L);
        permission.setId(1L);
        otherPermission.setId(2L);

        // When
        int result = permission.compareTo(otherPermission);

        // Then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void testCompareTo_DifferentSortOrder() {
        // Given
        Permission otherPermission = new Permission();
        otherPermission.setSortOrder(2L);
        permission.setSortOrder(1L);

        // When
        int result = permission.compareTo(otherPermission);

        // Then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void testCompareTo_SameSortOrderSameId() {
        // Given
        Permission otherPermission = new Permission();
        otherPermission.setSortOrder(1L);
        permission.setSortOrder(1L);
        permission.setId(1L);
        otherPermission.setId(1L);

        // When
        int result = permission.compareTo(otherPermission);

        // Then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testIsActive() {
        // Given
        permission.setIsActive(true);

        // When
        Boolean isActive = permission.getIsActive();

        // Then
        assertThat(isActive).isTrue();
    }

    @Test
    void testSetAndGetPermissionName() {
        // Given
        String permissionName = "READ";
        permission.setPermissionName(permissionName);

        // When
        String result = permission.getPermissionName();

        // Then
        assertThat(result).isEqualTo(permissionName);
    }

    // Add more test methods for other methods in Permission
}
