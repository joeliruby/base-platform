package com.matariky.user.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import org.keycloak.models.PasswordPolicy;
import org.keycloak.models.credential.PasswordCredentialModel;

@SpringBootTest
public class YXPWDHashProviderTest {

    @InjectMocks
    private YXPWDHashProvider yxpwdhashprovider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPolicyCheck() {
        // Given
        PasswordPolicy policy = mock(PasswordPolicy.class);
        PasswordCredentialModel credential = mock(PasswordCredentialModel.class);
        when(policy.getHashIterations()).thenReturn(1000);
        when(credential.getHashIterations()).thenReturn(1000);
        when(credential.getAlgorithm()).thenReturn(YXPWDHashProvider.ID);

        // When
        boolean result = yxpwdhashprovider.policyCheck(policy, credential);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void testVerify() {
        // Given
        String rawPassword = "password";
        PasswordCredentialModel credential = mock(PasswordCredentialModel.class);
        when(credential.getHashIterations()).thenReturn(1000);
        when(credential.getSalt()).thenReturn(new byte[16]);
        when(credential.getValue()).thenReturn("hashedPassword");

        // When
        boolean result = yxpwdhashprovider.verify(rawPassword, credential);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    void testGetId() {
        // When
        String id = yxpwdhashprovider.getId();

        // Then
        assertThat(id).isEqualTo(YXPWDHashProvider.ID);
    }

    // Add more test methods for other methods in YXPWDHashProvider
}
