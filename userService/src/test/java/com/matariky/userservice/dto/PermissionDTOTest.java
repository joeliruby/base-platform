package com.matariky.userservice.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PermissionDTOTest {

    @InjectMocks
    private PermissionDTO permissiondto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAndSetId() {
        // Given
        Long id = 1L;

        // When
        permissiondto.setId(id);

        // Then
        assertThat(permissiondto.getId()).isEqualTo(id);
    }

    @Test
    void testGetAndSetPid() {
        // Given
        Long pid = 2L;

        // When
        permissiondto.setPid(pid);

        // Then
        assertThat(permissiondto.getPid()).isEqualTo(pid);
    }

    @Test
    void testGetAndSetPermissionName() {
        // Given
        String permissionName = "READ";

        // When
        permissiondto.setPermissionName(permissionName);

        // Then
        assertThat(permissiondto.getPermissionName()).isEqualTo(permissionName);
    }

    @Test
    void testGetAndSetDescription() {
        // Given
        String description = "Permission to read data";

        // When
        permissiondto.setDescription(description);

        // Then
        assertThat(permissiondto.getDescription()).isEqualTo(description);
    }

    @Test
    void testGetAndSetTenantId() {
        // Given
        String tenantId = "tenant123";

        // When
        permissiondto.setTenantId(tenantId);

        // Then
        assertThat(permissiondto.getTenantId()).isEqualTo(tenantId);
    }

    @Test
    void testGetAndSetCreateTime() {
        // Given
        Long createTime = System.currentTimeMillis();

        // When
        permissiondto.setCreateTime(createTime);

        // Then
        assertThat(permissiondto.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void testGetAndSetUpdateTime() {
        // Given
        Long updateTime = System.currentTimeMillis();

        // When
        permissiondto.setUpdateTime(updateTime);

        // Then
        assertThat(permissiondto.getUpdateTime()).isEqualTo(updateTime);
    }

    @Test
    void testGetAndSetDeleteTime() {
        // Given
        Long deleteTime = System.currentTimeMillis();

        // When
        permissiondto.setDeleteTime(deleteTime);

        // Then
        assertThat(permissiondto.getDeleteTime()).isEqualTo(deleteTime);
    }

    @Test
    void testGetAndSetApplicationId() {
        // Given
        Long applicationId = 3L;

        // When
        permissiondto.setApplicationId(applicationId);

        // Then
        assertThat(permissiondto.getApplicationId()).isEqualTo(applicationId);
    }

    @Test
    void testGetAndSetResourceType() {
        // Given
        Integer resourceType = 1;

        // When
        permissiondto.setResourceType(resourceType);

        // Then
        assertThat(permissiondto.getResourceType()).isEqualTo(resourceType);
    }

    @Test
    void testGetAndSetIcon() {
        // Given
        String icon = "icon.png";

        // When
        permissiondto.setIcon(icon);

        // Then
        assertThat(permissiondto.getIcon()).isEqualTo(icon);
    }

    @Test
    void testGetAndSetIsActive() {
        // Given
        Boolean isActive = true;

        // When
        permissiondto.setIsActive(isActive);

        // Then
        assertThat(permissiondto.getIsActive()).isEqualTo(isActive);
    }

    @Test
    void testGetAndSetOrderNum() {
        // Given
        Integer orderNum = 1;

        // When
        permissiondto.setOrderNum(orderNum);

        // Then
        assertThat(permissiondto.getOrderNum()).isEqualTo(orderNum);
    }

    @Test
    void testGetAndSetUrl() {
        // Given
        String url = "http://example.com";

        // When
        permissiondto.setUrl(url);

        // Then
        assertThat(permissiondto.getUrl()).isEqualTo(url);
    }

    @Test
    void testGetAndSetCreatedBy() {
        // Given
        Long createdBy = 4L;

        // When
        permissiondto.setCreatedBy(createdBy);

        // Then
        assertThat(permissiondto.getCreatedBy()).isEqualTo(createdBy);
    }

    @Test
    void testGetAndSetUpdatedBy() {
        // Given
        Long updatedBy = 5L;

        // When
        permissiondto.setUpdatedBy(updatedBy);

        // Then
        assertThat(permissiondto.getUpdatedBy()).isEqualTo(updatedBy);
    }

    @Test
    void testGetAndSetAccessType() {
        // Given
        Integer accessType = 2;

        // When
        permissiondto.setAccessType(accessType);

        // Then
        assertThat(permissiondto.getAccessType()).isEqualTo(accessType);
    }

    @Test
    void testGetAndSetResourceAttribute() {
        // Given
        Integer resourceAttribute = 3;

        // When
        permissiondto.setResourceAttribute(resourceAttribute);

        // Then
        assertThat(permissiondto.getResourceAttribute()).isEqualTo(resourceAttribute);
    }
}
