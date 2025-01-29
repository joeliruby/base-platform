package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Map;

@SpringBootTest
public class KeycloakUtilsTest {

    @InjectMocks
    private KeycloakUtils keycloakutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTokenToMap_withValidToken() {
        // Given
        String keycloakToken = "{\"key\":\"value\"}";

        // When
        Map<String, String> result = KeycloakUtils.tokenToMap(keycloakToken);

        // Then
        assertNotNull(result);
        assertEquals("value", result.get("key"));
    }

    @Test
    void testTokenToMap_withEmptyToken() {
        // Given
        String keycloakToken = "";

        // When
        Map<String, String> result = KeycloakUtils.tokenToMap(keycloakToken);

        // Then
        assertNull(result);
    }

    @Test
    void testGetToken_withValidCredentials() {
        // Given
        String loginName = "user";
        String password = "password";
        String keycloakUrl = "http://localhost:8080";
        String keycloakRealm = "realm";
        String keycloakSecret = "secret";
        String keycloakClientId = "clientId";
        String keycloakGrantType = "password";

        // When
        String token = KeycloakUtils.getToken(loginName, password, keycloakUrl, keycloakRealm, keycloakSecret,
                keycloakClientId, keycloakGrantType);

        // Then
        assertNotNull(token);
    }

    @Test
    void testGetToken_withInvalidCredentials() {
        // Given
        String loginName = "invalidUser";
        String password = "invalidPassword";
        String keycloakUrl = "http://localhost:8080";
        String keycloakRealm = "realm";
        String keycloakSecret = "secret";
        String keycloakClientId = "clientId";
        String keycloakGrantType = "password";

        // When
        String token = KeycloakUtils.getToken(loginName, password, keycloakUrl, keycloakRealm, keycloakSecret,
                keycloakClientId, keycloakGrantType);

        // Then
        assertNull(token);
    }

    @Test
    void testCacheToken() {
        // Given
        Long id = 1L;
        String keycloakToken = "token";

        // When
        KeycloakUtils.cacheToken(id, keycloakToken);

        // Then
        // Add assertions to verify the behavior of cacheToken method
    }
}
