package com.matariky.userservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserOrganizationTest {

    @InjectMocks
    private UserOrganization userorganization;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCompareToEqual() {
        // Given
        UserOrganization other = new UserOrganization();
        other.setOrganizationCode("ORG123");
        userorganization.setOrganizationCode("ORG123");

        // When
        int result = userorganization.compareTo(other);

        // Then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testCompareToContains() {
        // Given
        UserOrganization other = new UserOrganization();
        other.setOrganizationCode("ORG");
        userorganization.setOrganizationCode("ORG123");

        // When
        int result = userorganization.compareTo(other);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testCompareToNotEqual() {
        // Given
        UserOrganization other = new UserOrganization();
        other.setOrganizationCode("ORG456");
        userorganization.setOrganizationCode("ORG123");

        // When
        int result = userorganization.compareTo(other);

        // Then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void testSetAndGetOrganizationName() {
        // Given
        String organizationName = "Test Organization";

        // When
        userorganization.setOrganizationName(organizationName);

        // Then
        assertThat(userorganization.getOrganizationName()).isEqualTo(organizationName);
    }

    @Test
    void testSetAndGetOrgType() {
        // Given
        Integer orgType = 1;

        // When
        userorganization.setOrgType(orgType);

        // Then
        assertThat(userorganization.getOrgType()).isEqualTo(orgType);
    }

    // Add more test methods for other getters and setters in UserOrganization
}
