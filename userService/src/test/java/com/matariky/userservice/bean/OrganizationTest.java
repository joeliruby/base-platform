package com.matariky.userservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class OrganizationTest {

    @InjectMocks
    private Organization organization;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        organization.setId(expectedId);

        // When
        Long actualId = organization.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetOrganizationCode() {
        // Given
        String expectedCode = "ORG123";
        organization.setOrganizationCode(expectedCode);

        // When
        String actualCode = organization.getOrganizationCode();

        // Then
        assertThat(actualCode).isEqualTo(expectedCode);
    }

    @Test
    void testGetOrganizationName() {
        // Given
        String expectedName = "Test Organization";
        organization.setOrganizationName(expectedName);

        // When
        String actualName = organization.getOrganizationName();

        // Then
        assertThat(actualName).isEqualTo(expectedName);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedTime = System.currentTimeMillis();
        organization.setCreateTime(expectedTime);

        // When
        Long actualTime = organization.getCreateTime();

        // Then
        assertThat(actualTime).isEqualTo(expectedTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedTime = System.currentTimeMillis();
        organization.setUpdateTime(expectedTime);

        // When
        Long actualTime = organization.getUpdateTime();

        // Then
        assertThat(actualTime).isEqualTo(expectedTime);
    }

    @Test
    void testGetDescription() {
        // Given
        String expectedDescription = "This is a test organization.";
        organization.setDescription(expectedDescription);

        // When
        String actualDescription = organization.getDescription();

        // Then
        assertThat(actualDescription).isEqualTo(expectedDescription);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "TENANT123";
        organization.setTenantId(expectedTenantId);

        // When
        String actualTenantId = organization.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }

    @Test
    void testIsVirtual() {
        // Given
        Boolean expectedIsVirtual = true;
        organization.setIsVirtual(expectedIsVirtual);

        // When
        Boolean actualIsVirtual = organization.getIsVirtual();

        // Then
        assertThat(actualIsVirtual).isEqualTo(expectedIsVirtual);
    }

    @Test
    void testGetCreatedBy() {
        // Given
        Long expectedCreatedBy = 1L;
        organization.setCreatedBy(expectedCreatedBy);

        // When
        Long actualCreatedBy = organization.getCreatedBy();

        // Then
        assertThat(actualCreatedBy).isEqualTo(expectedCreatedBy);
    }

    @Test
    void testGetUpdatedBy() {
        // Given
        Long expectedUpdatedBy = 2L;
        organization.setUpdatedBy(expectedUpdatedBy);

        // When
        Long actualUpdatedBy = organization.getUpdatedBy();

        // Then
        assertThat(actualUpdatedBy).isEqualTo(expectedUpdatedBy);
    }

    @Test
    void testGetOrgType() {
        // Given
        Integer expectedOrgType = 1;
        organization.setOrgType(expectedOrgType);

        // When
        Integer actualOrgType = organization.getOrgType();

        // Then
        assertThat(actualOrgType).isEqualTo(expectedOrgType);
    }

    @Test
    void testGetOrderNum() {
        // Given
        Integer expectedOrderNum = 10;
        organization.setOrderNum(expectedOrderNum);

        // When
        Integer actualOrderNum = organization.getOrderNum();

        // Then
        assertThat(actualOrderNum).isEqualTo(expectedOrderNum);
    }

    @Test
    void testGetComment() {
        // Given
        String expectedComment = "This is a comment.";
        organization.setComment(expectedComment);

        // When
        String actualComment = organization.getComment();

        // Then
        assertThat(actualComment).isEqualTo(expectedComment);
    }

    @Test
    void testGetLiaisonName() {
        // Given
        String expectedLiaisonName = "John Doe";
        organization.setLiaisonName(expectedLiaisonName);

        // When
        String actualLiaisonName = organization.getLiaisonName();

        // Then
        assertThat(actualLiaisonName).isEqualTo(expectedLiaisonName);
    }

    @Test
    void testGetLiaisonMobile() {
        // Given
        String expectedLiaisonMobile = "1234567890";
        organization.setLiaisonMobile(expectedLiaisonMobile);

        // When
        String actualLiaisonMobile = organization.getLiaisonMobile();

        // Then
        assertThat(actualLiaisonMobile).isEqualTo(expectedLiaisonMobile);
    }

    @Test
    void testGetParentId() {
        // Given
        Long expectedParentId = 1L;
        organization.setParentId(expectedParentId);

        // When
        Long actualParentId = organization.getParentId();

        // Then
        assertThat(actualParentId).isEqualTo(expectedParentId);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = System.currentTimeMillis();
        organization.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = organization.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }
}
