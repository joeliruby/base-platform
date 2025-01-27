package com.matariky.user.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.stream.Stream;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.cache.infinispan.RealmCacheSession;
import org.keycloak.models.cache.infinispan.entities.CachedClient;
import org.mockito.Mock;

@SpringBootTest
public class CustomerClientAdapterTest {

    @InjectMocks
    private CustomerClientAdapter customerClientAdapter;

    @Mock
    private RealmModel cachedRealm;

    @Mock
    private CachedClient cachedClient;

    @Mock
    private RealmCacheSession cacheSession;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerClientAdapter = new CustomerClientAdapter(cachedRealm, cachedClient, cacheSession);
    }

    @Test
    void testGetRolesStream() {
        // Given
        Stream<RoleModel> expectedStream = Stream.of(mock(RoleModel.class));
        when(cacheSession.getClientRolesStream(customerClientAdapter)).thenReturn(expectedStream);

        // When
        Stream<RoleModel> result = customerClientAdapter.getRolesStream();

        // Then
        assertThat(result).isEqualTo(expectedStream);
    }

    @Test
    void testGetRolesStreamWithPagination() {
        // Given
        Stream<RoleModel> expectedStream = Stream.of(mock(RoleModel.class));
        when(cacheSession.getClientRolesStream(customerClientAdapter, 0, 10)).thenReturn(expectedStream);

        // When
        Stream<RoleModel> result = customerClientAdapter.getRolesStream(0, 10);

        // Then
        assertThat(result).isEqualTo(expectedStream);
    }

    @Test
    void testSearchForRolesStream() {
        // Given
        Stream<RoleModel> expectedStream = Stream.of(mock(RoleModel.class));
        when(cacheSession.searchForClientRolesStream(customerClientAdapter, "search", 0, 10))
                .thenReturn(expectedStream);

        // When
        Stream<RoleModel> result = customerClientAdapter.searchForRolesStream("search", 0, 10);

        // Then
        assertThat(result).isEqualTo(expectedStream);
    }

    // Add more test methods for other methods in CustomerClientAdapter
}
