package com.matariky.userservice.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PermissionInfoVOTest {

    @InjectMocks
    private PermissionInfoVO permissioninfovo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testParentPermissionName() {
        // Given
        String expectedParentPermissionName = "Admin";
        permissioninfovo.setParentPermissionName(expectedParentPermissionName);

        // When
        String actualParentPermissionName = permissioninfovo.getParentPermissionName();

        // Then
        assertThat(actualParentPermissionName).isEqualTo(expectedParentPermissionName);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        PermissionInfoVO permissionInfoVO1 = new PermissionInfoVO();
        permissionInfoVO1.setParentPermissionName("Admin");

        PermissionInfoVO permissionInfoVO2 = new PermissionInfoVO();
        permissionInfoVO2.setParentPermissionName("Admin");

        // When & Then
        assertThat(permissionInfoVO1).isEqualTo(permissionInfoVO2);
        assertThat(permissionInfoVO1.hashCode()).isEqualTo(permissionInfoVO2.hashCode());
    }

    @Test
    void testNotEquals() {
        // Given
        PermissionInfoVO permissionInfoVO1 = new PermissionInfoVO();
        permissionInfoVO1.setParentPermissionName("Admin");

        PermissionInfoVO permissionInfoVO2 = new PermissionInfoVO();
        permissionInfoVO2.setParentPermissionName("User");

        // When & Then
        assertThat(permissionInfoVO1).isNotEqualTo(permissionInfoVO2);
    }

    // Add more test methods for other methods in PermissionInfoVO
}
