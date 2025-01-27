package com.matariky.user.customer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.models.ClientModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RealmProvider;
import org.keycloak.models.RoleModel;
import org.keycloak.models.cache.infinispan.RealmCacheManager;
import org.keycloak.models.cache.infinispan.entities.CachedRealm;
import org.keycloak.models.cache.infinispan.entities.CachedRole;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerRoleProviderTest {

    @InjectMocks
    private CustomerRoleProvider customerRoleProvider;

    @Mock
    private RealmCacheManager cache;

    @Mock
    private KeycloakSession session;

    @Mock
    private RealmModel realm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRealm() {
        // Given
        String realmId = "testRealmId";
        when(cache.get(realmId, CachedRealm.class)).thenReturn(null);
        when(session.getProvider(RealmProvider.class)).thenReturn(mock(RealmProvider.class));

        // When
        RealmModel result = customerRoleProvider.getRealm(realmId);

        // Then
        assertNotNull(result);
        verify(cache).get(realmId, CachedRealm.class);
    }

    @Test
    void testGetRoleById() {
        // Given
        String roleId = "testRoleId";
        when(cache.get(roleId, CachedRole.class)).thenReturn(null);
        when(session.getProvider(RealmProvider.class)).thenReturn(mock(RealmProvider.class));

        // When
        RoleModel result = customerRoleProvider.getRoleById(realm, roleId);

        // Then
        assertNotNull(result);
        verify(cache).get(roleId, CachedRole.class);
    }

    @Test
    void testRegisterRealmInvalidation() {
        // Given
        String realmId = "testRealmId";
        String realmName = "testRealmName";

        // When
        customerRoleProvider.registerRealmInvalidation(realmId, realmName);

        // Then
        verify(cache).realmUpdated(eq(realmId), eq(realmName), anySet());
    }

    @Test
    void testGetRealmRolesStream() {
        // Given
        when(session.getProvider(RealmProvider.class)).thenReturn(mock(RealmProvider.class));

        // When
        Stream<RoleModel> result = customerRoleProvider.getRealmRolesStream(realm, 0, 10);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetClientRolesStream() {
        // Given
        ClientModel client = mock(ClientModel.class);
        when(session.getProvider(RealmProvider.class)).thenReturn(mock(RealmProvider.class));

        // When
        Stream<RoleModel> result = customerRoleProvider.getClientRolesStream(client, 0, 10);

        // Then
        assertNotNull(result);
    }

    // Add more test methods for other methods in CustomerRoleProvider
}
