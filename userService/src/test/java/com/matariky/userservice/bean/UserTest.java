package com.matariky.userservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserTest {

    @InjectMocks
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLoginName() {
        // Given
        String expectedLoginName = "testUser";
        user.setLoginName(expectedLoginName);

        // When
        String actualLoginName = user.getLoginName();

        // Then
        assertThat(actualLoginName).isEqualTo(expectedLoginName);
    }

    @Test
    void testSetAndGetRealName() {
        // Given
        String expectedRealName = "John Doe";
        user.setRealName(expectedRealName);

        // When
        String actualRealName = user.getRealName();

        // Then
        assertThat(actualRealName).isEqualTo(expectedRealName);
    }

    @Test
    void testSetAndGetEmail() {
        // Given
        String expectedEmail = "john.doe@example.com";
        user.setEmail(expectedEmail);

        // When
        String actualEmail = user.getEmail();

        // Then
        assertThat(actualEmail).isEqualTo(expectedEmail);
    }

    @Test
    void testSetAndGetCellPhone() {
        // Given
        String expectedCellPhone = "12345678901";
        user.setCellPhone(expectedCellPhone);

        // When
        String actualCellPhone = user.getCellPhone();

        // Then
        assertThat(actualCellPhone).isEqualTo(expectedCellPhone);
    }

    @Test
    void testSetAndGetTenantId() {
        // Given
        String expectedTenantId = "tenant123";
        user.setTenantId(expectedTenantId);

        // When
        String actualTenantId = user.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }

    @Test
    void testSetAndGetIsActive() {
        // Given
        Boolean expectedIsActive = true;
        user.setIsActive(expectedIsActive);

        // When
        Boolean actualIsActive = user.getIsActive();

        // Then
        assertThat(actualIsActive).isEqualTo(expectedIsActive);
    }

    // Add more test methods for other methods in User
}
