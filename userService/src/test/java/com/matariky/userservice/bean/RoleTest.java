package com.matariky.userservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RoleTest {

    @InjectMocks
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        role.setId(expectedId);

        // When
        Long actualId = role.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "tenant123";
        role.setTenantId(expectedTenantId);

        // When
        String actualTenantId = role.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }

    @Test
    void testGetRoleName() {
        // Given
        String expectedRoleName = "Admin";
        role.setRoleName(expectedRoleName);

        // When
        String actualRoleName = role.getRoleName();

        // Then
        assertThat(actualRoleName).isEqualTo(expectedRoleName);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = System.currentTimeMillis();
        role.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = role.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetDescription() {
        // Given
        String expectedDescription = "Role description";
        role.setDescription(expectedDescription);

        // When
        String actualDescription = role.getDescription();

        // Then
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = System.currentTimeMillis();
        role.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = role.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = System.currentTimeMillis();
        role.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = role.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetComment() {
        // Given
        String expectedComment = "This is a comment";
        role.setComment(expectedComment);

        // When
        String actualComment = role.getComment();

        // Then
        assertThat(actualComment).isEqualTo(expectedComment);
    }

    @Test
    void testGetCreatedBy() {
        // Given
        Long expectedCreatedBy = 1L;
        role.setCreatedBy(expectedCreatedBy);

        // When
        Long actualCreatedBy = role.getCreatedBy();

        // Then
        assertThat(actualCreatedBy).isEqualTo(expectedCreatedBy);
    }

    @Test
    void testGetUpdatedBy() {
        // Given
        Long expectedUpdatedBy = 2L;
        role.setUpdatedBy(expectedUpdatedBy);

        // When
        Long actualUpdatedBy = role.getUpdatedBy();

        // Then
        assertThat(actualUpdatedBy).isEqualTo(expectedUpdatedBy);
    }
}
