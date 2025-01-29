package com.matariky.constant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CacheConstantsTest {

    @InjectMocks
    private CacheConstants cacheConstants;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeviceOnlineConstant() {
        // Given
        String expectedValue = "device_online:";

        // When
        String actualValue = CacheConstants.DEVICE_ONLINE;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testKeycloakTokenPrefixConstant() {
        // Given
        String expectedValue = "KCT_";

        // When
        String actualValue = CacheConstants.KEYCLOAK_TOKEN_PREFIX;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    // Add more test methods for other constants in CacheConstants if needed
}
