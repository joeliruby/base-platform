package com.matariky.user.customer.external;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CredentialDataTest {

    @InjectMocks
    private CredentialData credentialData;

    @Mock
    private PasswordCredentialModel passwordCredentialModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToPasswordCredentialModel() {
        // Given
        credentialData = new CredentialData("value", "salt", "algorithm", 1000, "type");

        // When
        PasswordCredentialModel result = credentialData.toPasswordCredentialModel();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getPasswordSecretData().getValue()).isEqualTo("value");
        assertThat(result.getPasswordCredentialData().getAlgorithm()).isEqualTo("algorithm");
        assertThat(result.getPasswordCredentialData().getHashIterations()).isEqualTo(1000);
    }

    @Test
    void testFromPasswordCredentialModel() {
        // Given
        when(passwordCredentialModel.getPasswordSecretData().getValue()).thenReturn("value");
        when(passwordCredentialModel.getPasswordSecretData().getSalt()).thenReturn("salt".getBytes());
        when(passwordCredentialModel.getPasswordCredentialData().getAlgorithm()).thenReturn("algorithm");
        when(passwordCredentialModel.getPasswordCredentialData().getHashIterations()).thenReturn(1000);

        // When
        CredentialData result = CredentialData.fromPasswordCredentialModel(passwordCredentialModel);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getValue()).isEqualTo("value");
        assertThat(result.getSalt()).isEqualTo(Base64.getEncoder().encodeToString("salt".getBytes()));
        assertThat(result.getAlgorithm()).isEqualTo("algorithm");
        assertThat(result.getIterations()).isEqualTo(1000);
        assertThat(result.getType()).isEqualTo(PasswordCredentialModel.TYPE);
    }

    // Add more test methods for other methods in CredentialData
}
