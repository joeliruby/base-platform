package com.matariky.userservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.matariky.userservice.bean.UserTenant;

@SpringBootTest
public class UserTenantMapperTest {

    @InjectMocks
    private UserTenantMapper usertenantmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserTenantAll() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");

        // When
        com.github.pagehelper.Page<UserTenant> pageResult = usertenantmapper.getUserTenantAll(params);
        Page<UserTenant> result = new org.springframework.data.domain.PageImpl<>(pageResult.getResult(),
                PageRequest.of(pageResult.getPageNum() - 1, pageResult.getPageSize()), pageResult.getTotal());

        // Then
        assertNotNull(result);
        // Add more assertions based on expected behavior
    }

    @Test
    void testCreateUserTenant() {
        // Given
        UserTenant userTenant = new UserTenant();
        userTenant.setId(1l);
        userTenant.setTenantCode("tenantCode");

        // When
        int result = usertenantmapper.createUserTenant(userTenant);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateUserTenant() {
        // Given
        UserTenant userTenant = new UserTenant();
        userTenant.setId(1l);
        userTenant.setTenantCode("updatedTenantCode");

        // When
        int result = usertenantmapper.updateUserTenant(userTenant);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelUserTenantById() {
        // Given
        int id = 1;

        // When
        int result = usertenantmapper.delUserTenantById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetUserTenantById() {
        // Given
        String id = "1";

        // When
        UserTenant result = usertenantmapper.getUserTenantById(id);

        // Then
        assertNotNull(result);
        assertEquals("1", result.getId());
    }

    @Test
    void testSelectByTenantCode() {
        // Given
        String tenantCode = "tenantCode";

        // When
        UserTenant result = usertenantmapper.selectByTenantCode(tenantCode);

        // Then
        assertNotNull(result);
        assertEquals("tenantCode", result.getTenantCode());
    }

    // Add more test methods for other methods in UserTenantMapper
}
