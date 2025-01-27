package com.matariky.user.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.keycloak.models.RealmModel;
import org.keycloak.models.cache.infinispan.RealmCacheSession;
import org.keycloak.models.cache.infinispan.entities.CachedRole;

@SpringBootTest
public class CustomerRoleAdapterTest {

    @InjectMocks
    private CustomerRoleAdapter customerRoleAdapter;

    @Mock
    private CachedRole cachedRole;

    @Mock
    private RealmCacheSession realmCacheSession;

    @Mock
    private RealmModel realmModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerRoleAdapter = new CustomerRoleAdapter(cachedRole, realmCacheSession, realmModel);
    }

    @Test
    void testCustomerRoleAdapterCreation() {
        assertNotNull(customerRoleAdapter);
    }

    @Test
    void testGetName() {
        when(cachedRole.getName()).thenReturn("testRole");
        String roleName = customerRoleAdapter.getName();
        assertEquals("testRole", roleName);
    }

    @Test
    void testGetId() {
        when(cachedRole.getId()).thenReturn("12345");
        String roleId = customerRoleAdapter.getId();
        assertEquals("12345", roleId);
    }

    // Add more test methods for other methods in CustomerRoleAdapter
}
