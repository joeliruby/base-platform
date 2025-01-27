package com.matariky.user.customer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.component.ComponentModel;
import org.keycloak.component.ComponentValidationException;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.provider.ProviderConfigProperty;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerUserProviderFactoryTest {

    @InjectMocks
    private CustomerUserProviderFactory customerUserProviderFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        // Given
        KeycloakSession session = mock(KeycloakSession.class);
        ComponentModel model = mock(ComponentModel.class);

        // When
        CustomerUserProvider result = customerUserProviderFactory.create(session, model);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetId() {
        // When
        String result = customerUserProviderFactory.getId();

        // Then
        assertEquals(CustomerUserProviderFactory.PROVIDER_ID, result);
    }

    @Test
    void testGetHelpText() {
        // When
        String result = customerUserProviderFactory.getHelpText();

        // Then
        assertEquals("customer User Provider", result);
    }

    @Test
    void testGetConfigProperties() {
        // When
        List<ProviderConfigProperty> result = customerUserProviderFactory.getConfigProperties();

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testValidateConfiguration() {
        // Given
        KeycloakSession session = mock(KeycloakSession.class);
        RealmModel realm = mock(RealmModel.class);
        ComponentModel config = mock(ComponentModel.class);

        when(config.get(Constants.BASE_URL)).thenReturn("http://example.com");
        when(config.get(Constants.AUTH_USERNAME)).thenReturn("username");
        when(config.get(Constants.AUTH_PASSWORD)).thenReturn("password");

        // When / Then
        assertDoesNotThrow(() -> customerUserProviderFactory.validateConfiguration(session, realm, config));
    }

    @Test
    void testValidateConfigurationThrowsException() {
        // Given
        KeycloakSession session = mock(KeycloakSession.class);
        RealmModel realm = mock(RealmModel.class);
        ComponentModel config = mock(ComponentModel.class);

        when(config.get(Constants.BASE_URL)).thenReturn("");
        when(config.get(Constants.AUTH_USERNAME)).thenReturn("");
        when(config.get(Constants.AUTH_PASSWORD)).thenReturn("");

        // When / Then
        assertThrows(ComponentValidationException.class,
                () -> customerUserProviderFactory.validateConfiguration(session, realm, config));
    }
}
