package com.matariky.userservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GroupTest {

    @InjectMocks
    private Group group;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        group.setId(expectedId);

        // When
        Long actualId = group.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testSetGroupName() {
        // Given
        String expectedGroupName = "Admin";
        group.setGroupName(expectedGroupName);

        // When
        String actualGroupName = group.getGroupName();

        // Then
        assertThat(actualGroupName).isEqualTo(expectedGroupName);
    }

    @Test
    void testSetTenantId() {
        // Given
        String expectedTenantId = "tenant123";
        group.setTenantId(expectedTenantId);

        // When
        String actualTenantId = group.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }

    @Test
    void testSetDescription() {
        // Given
        String expectedDescription = "Group description";
        group.setDescription(expectedDescription);

        // When
        String actualDescription = group.getDescription();

        // Then
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    void testSetCreateTime() {
        // Given
        Long expectedCreateTime = System.currentTimeMillis();
        group.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = group.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testSetUpdateTime() {
        // Given
        Long expectedUpdateTime = System.currentTimeMillis();
        group.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = group.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testSetDeleteTime() {
        // Given
        Long expectedDeleteTime = System.currentTimeMillis();
        group.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = group.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testSetComment() {
        // Given
        String expectedComment = "This is a comment";
        group.setComment(expectedComment);

        // When
        String actualComment = group.getComment();

        // Then
        assertThat(actualComment).isEqualTo(expectedComment);
    }

    @Test
    void testSetCreatedBy() {
        // Given
        Long expectedCreatedBy = 1L;
        group.setCreatedBy(expectedCreatedBy);

        // When
        Long actualCreatedBy = group.getCreatedBy();

        // Then
        assertThat(actualCreatedBy).isEqualTo(expectedCreatedBy);
    }

    @Test
    void testSetUpdatedBy() {
        // Given
        Long expectedUpdatedBy = 2L;
        group.setUpdatedBy(expectedUpdatedBy);

        // When
        Long actualUpdatedBy = group.getUpdatedBy();

        // Then
        assertThat(actualUpdatedBy).isEqualTo(expectedUpdatedBy);
    }
}
