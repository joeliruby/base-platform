package com.matariky.commonservice.commondict.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommonDictTypeTest {

    @InjectMocks
    private CommonDictType commondicttype;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        commondicttype.setId(expectedId);

        // When
        Long actualId = commondicttype.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetDictTypeName() {
        // Given
        String expectedName = "TypeName";
        commondicttype.setDictTypeName(expectedName);

        // When
        String actualName = commondicttype.getDictTypeName();

        // Then
        assertThat(actualName).isEqualTo(expectedName);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "Tenant123";
        commondicttype.setTenantId(expectedTenantId);

        // When
        String actualTenantId = commondicttype.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }

    @Test
    void testGetComment() {
        // Given
        String expectedComment = "This is a comment";
        commondicttype.setComment(expectedComment);

        // When
        String actualComment = commondicttype.getComment();

        // Then
        assertThat(actualComment).isEqualTo(expectedComment);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;
        commondicttype.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = commondicttype.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 987654321L;
        commondicttype.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = commondicttype.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetIsActive() {
        // Given
        Boolean expectedIsActive = true;
        commondicttype.setIsActive(expectedIsActive);

        // When
        Boolean actualIsActive = commondicttype.getIsActive();

        // Then
        assertThat(actualIsActive).isEqualTo(expectedIsActive);
    }

    @Test
    void testGetCreatedBy() {
        // Given
        Long expectedCreatedBy = 1L;
        commondicttype.setCreatedBy(expectedCreatedBy);

        // When
        Long actualCreatedBy = commondicttype.getCreatedBy();

        // Then
        assertThat(actualCreatedBy).isEqualTo(expectedCreatedBy);
    }

    @Test
    void testGetUpdatedBy() {
        // Given
        Long expectedUpdatedBy = 2L;
        commondicttype.setUpdatedBy(expectedUpdatedBy);

        // When
        Long actualUpdatedBy = commondicttype.getUpdatedBy();

        // Then
        assertThat(actualUpdatedBy).isEqualTo(expectedUpdatedBy);
    }

    @Test
    void testGetDictTypeKey() {
        // Given
        String expectedKey = "Key123";
        commondicttype.setDictTypeKey(expectedKey);

        // When
        String actualKey = commondicttype.getDictTypeKey();

        // Then
        assertThat(actualKey).isEqualTo(expectedKey);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = 123456789L;
        commondicttype.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = commondicttype.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }
}
