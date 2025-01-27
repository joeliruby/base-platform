package com.matariky.user.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ConstantsTest {

    @InjectMocks
    private Constants constants;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBaseUrl() {
        // Given
        String expectedValue = "customerBaseUrl";

        // When
        String actualValue = constants.BASE_URL;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testAuthUsername() {
        // Given
        String expectedValue = "customerAuthUsername";

        // When
        String actualValue = constants.AUTH_USERNAME;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testAuthPassword() {
        // Given
        String expectedValue = "customerAuthPassword";

        // When
        String actualValue = constants.AUTH_PASSWORD;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    // Add more test methods for other constants if needed
}
