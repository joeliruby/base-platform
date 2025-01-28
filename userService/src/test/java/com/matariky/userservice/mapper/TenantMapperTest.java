package com.matariky.userservice.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.userservice.bean.Tenant;

@SpringBootTest
public class TenantMapperTest {

    @InjectMocks
    private TenantMapper tenantmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertSelective() {
        // Given
        Tenant tenant = new Tenant();
        tenant.setId(1L);
        tenant.setTenantCode("testCode");
        when(tenantmapper.insertSelective(tenant)).thenReturn(1);

        // When
        int result = tenantmapper.insertSelective(tenant);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testSelectByPrimaryKey() {
        // Given
        Long id = 1L;
        Tenant tenant = new Tenant();
        tenant.setId(id);
        tenant.setTenantCode("testCode");
        when(tenantmapper.selectByPrimaryKey(id)).thenReturn(tenant);

        // When
        Tenant result = tenantmapper.selectByPrimaryKey(id);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    void testUpdateByPrimaryKeySelective() {
        // Given
        Tenant tenant = new Tenant();
        tenant.setId(1L);
        tenant.setTenantCode("updatedCode");
        when(tenantmapper.updateByPrimaryKeySelective(tenant)).thenReturn(1);

        // When
        int result = tenantmapper.updateByPrimaryKeySelective(tenant);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testDeleteByPrimaryKey() {
        // Given
        Long id = 1L;
        when(tenantmapper.deleteByPrimaryKey(id)).thenReturn(1);

        // When
        int result = tenantmapper.deleteByPrimaryKey(id);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testSelectByTenantCode() {
        // Given
        String tenantCode = "testCode";
        Tenant tenant = new Tenant();
        tenant.setTenantCode(tenantCode);
        when(tenantmapper.selectBytenantCode(tenantCode)).thenReturn(tenant);

        // When
        Tenant result = tenantmapper.selectBytenantCode(tenantCode);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTenantCode()).isEqualTo(tenantCode);
    }

    // Add more test methods for other methods in TenantMapper
}
