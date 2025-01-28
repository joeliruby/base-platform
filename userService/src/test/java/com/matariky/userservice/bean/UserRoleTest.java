package com.matariky.userservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserRoleTest {

    @InjectMocks
    private UserRole userrole;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRoleNameNotBlank() {
        // Given
        userrole.setRoleName("");

        // When
        // Call the method you want to test

        // Then
        // Assert the expected results
        assertThatThrownBy(() -> {
            // Trigger validation
        }).isInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("Character name cannot be empty!");
    }

    @Test
    void testSetAndGetTenantId() {
        // Given
        String tenantId = "tenant123";
        userrole.setTenantId(tenantId);

        // When
        String result = userrole.getTenantId();

        // Then
        assertThat(result).isEqualTo(tenantId);
    }

    @Test
    void testSetAndGetRoleName() {
        // Given
        String roleName = "Admin";
        userrole.setRoleName(roleName);

        // When
        String result = userrole.getRoleName();

        // Then
        assertThat(result).isEqualTo(roleName);
    }

    @Test
    void testSetAndGetDescription() {
        // Given
        String description = "Administrator role";
        userrole.setDescription(description);

        // When
        String result = userrole.getDescription();

        // Then
        assertThat(result).isEqualTo(description);
    }

    @Test
    void testSetAndGetCreateTime() {
        // Given
        Long createTime = System.currentTimeMillis();
        userrole.setCreateTime(createTime);

        // When
        Long result = userrole.getCreateTime();

        // Then
        assertThat(result).isEqualTo(createTime);
    }

    // Add more test methods for other fields and methods in UserRole
}
