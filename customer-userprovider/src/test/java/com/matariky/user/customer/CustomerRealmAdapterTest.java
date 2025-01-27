package com.matariky.user.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.stream.Stream;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RoleModel;
import org.keycloak.models.cache.infinispan.RealmCacheSession;
import org.keycloak.models.cache.infinispan.entities.CachedRealm;
import org.mockito.Mock;

@SpringBootTest
public class CustomerRealmAdapterTest {

    @Mock
    private KeycloakSession session;

    @Mock
    private CachedRealm cachedRealm;

    @Mock
    private RealmCacheSession cacheSession;

    @InjectMocks
    private CustomerRealmAdapter customerRealmAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerRealmAdapter = new CustomerRealmAdapter(session, cachedRealm, cacheSession);
    }

    @Test
    void testGetRolesStream() {
        // Given
        Stream<RoleModel> expectedStream = Stream.of(mock(RoleModel.class));
        when(cacheSession.getRealmRolesStream(customerRealmAdapter)).thenReturn(expectedStream);

        // When
        Stream<RoleModel> result = customerRealmAdapter.getRolesStream();

        // Then
        assertThat(result).isEqualTo(expectedStream);
    }

    @Test
    void testGetRolesStreamWithPagination() {
        // Given
        Stream<RoleModel> expectedStream = Stream.of(mock(RoleModel.class));
        when(cacheSession.getRealmRolesStream(customerRealmAdapter, 0, 10)).thenReturn(expectedStream);

        // When
        Stream<RoleModel> result = customerRealmAdapter.getRolesStream(0, 10);

        // Then
        assertThat(result).isEqualTo(expectedStream);
    }

    @Test
    void testSearchForRolesStream() {
        // Given
        Stream<RoleModel> expectedStream = Stream.of(mock(RoleModel.class));
        when(cacheSession.searchForRolesStream(customerRealmAdapter, "search", 0, 10)).thenReturn(expectedStream);

        // When
        Stream<RoleModel> result = customerRealmAdapter.searchForRolesStream("search", 0, 10);

        // Then
        assertThat(result).isEqualTo(expectedStream);
    }

    @Test
    void testInvalidateFlag() {
        // When
        customerRealmAdapter.invalidateFlag();

        // Then
        assertThat(customerRealmAdapter.invalidated).isTrue();
    }
}
