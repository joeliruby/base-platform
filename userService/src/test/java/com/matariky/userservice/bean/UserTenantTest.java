package com.matariky.userservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserTenantTest {

    @InjectMocks
    private UserTenant usertenant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTenantCode() {
        // Given
        usertenant.setTenantCode("TC123");

        // When
        String tenantCode = usertenant.getTenantCode();

        // Then
        assertThat(tenantCode).isEqualTo("TC123");
    }

    @Test
    void testSetTenantName() {
        // Given
        String tenantName = "Tenant Name";

        // When
        usertenant.setTenantName(tenantName);

        // Then
        assertThat(usertenant.getTenantName()).isEqualTo(tenantName);
    }

    @Test
    void testIsActive() {
        // Given
        usertenant.setIsActive(true);

        // When
        Boolean isActive = usertenant.getIsActive();

        // Then
        assertThat(isActive).isTrue();
    }

    @Test
    void testSetAndGetDomainName() {
        // Given
        String domainName = "example.com";

        // When
        usertenant.setDomainName(domainName);

        // Then
        assertThat(usertenant.getDomainName()).isEqualTo(domainName);
    }

    @Test
    void testSetAndGetTheme() {
        // Given
        String theme = "dark";

        // When
        usertenant.setTheme(theme);

        // Then
        assertThat(usertenant.getTheme()).isEqualTo(theme);
    }

    // Add more test methods for other methods in UserTenant
}
