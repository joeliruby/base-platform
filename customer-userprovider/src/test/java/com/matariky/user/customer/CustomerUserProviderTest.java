package com.matariky.user.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.keycloak.models.KeycloakSession;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.models.UserCredentialModel;

@SpringBootTest
public class CustomerUserProviderTest {

    @InjectMocks
    private CustomerUserProvider customerUserProvider;

    @Mock
    private KeycloakSession session;

    @Mock
    private ComponentModel model;

    @Mock
    private RealmModel realm;

    @Mock
    private UserModel user;

    @Mock
    private CredentialInput credentialInput;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSupportsCredentialType() {
        // Given
        String credentialType = "password";

        // When
        boolean result = customerUserProvider.supportsCredentialType(credentialType);

        // Then
        assertTrue(result);
    }

    @Test
    void testIsConfiguredFor() {
        // Given
        String credentialType = "password";
        when(user.getId()).thenReturn("user-id");

        // When
        boolean result = customerUserProvider.isConfiguredFor(realm, user, credentialType);

        // Then
        assertTrue(result);
    }

    @Test
    void testIsValid() {
        // Given
        String credentialType = "password";
        when(credentialInput.getType()).thenReturn(credentialType);
        when(credentialInput instanceof UserCredentialModel).thenReturn(true);
        when(user.getId()).thenReturn("user-id");

        // When
        boolean result = customerUserProvider.isValid(realm, user, credentialInput);

        // Then
        assertFalse(result); // Assuming the mock client returns null or throws an exception
    }

    @Test
    void testUpdateCredential() {
        // Given
        String credentialType = "password";
        when(credentialInput.getType()).thenReturn(credentialType);
        when(credentialInput instanceof UserCredentialModel).thenReturn(true);
        when(user.getId()).thenReturn("user-id");

        // When
        boolean result = customerUserProvider.updateCredential(realm, user, credentialInput);

        // Then
        assertFalse(result); // Assuming the mock client throws an exception
    }

    @Test
    void testGetUserById() {
        // Given
        String userId = "user-id";

        // When
        UserModel result = customerUserProvider.getUserById(realm, userId);

        // Then
        assertNull(result); // Assuming the mock client throws an exception
    }

    @Test
    void testGetUserByUsername() {
        // Given
        String username = "username";

        // When
        UserModel result = customerUserProvider.getUserByUsername(realm, username);

        // Then
        assertNull(result); // Assuming the mock client throws an exception
    }

    @Test
    void testGetUserByEmail() {
        // Given
        String email = "email@example.com";

        // When
        UserModel result = customerUserProvider.getUserByEmail(realm, email);

        // Then
        assertNull(result); // Assuming the mock client throws an exception
    }

    @Test
    void testGetUsersCount() {
        // When
        int result = customerUserProvider.getUsersCount(realm);

        // Then
        assertEquals(0, result); // Assuming the mock client returns 0
    }

    @Test
    void testSearchForUserStream() {
        // Given
        String search = "search";
        Integer firstResult = 0;
        Integer maxResults = 10;

        // When
        long result = customerUserProvider.searchForUserStream(realm, search, firstResult, maxResults).count();

        // Then
        assertEquals(0, result); // Assuming the mock client returns an empty list
    }
}
