package com.matariky.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class OrgCodeUtilTest {

    @InjectMocks
    private OrgCodeUtil orgcodeutil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateSelfOrganizationCode() {
        // Given
        String departmentOrgCode = "dept123";
        Long userId = 456L;

        // When
        String result = OrgCodeUtil.generateSelfOrganizationCode(departmentOrgCode, userId);

        // Then
        assertThat(result).isEqualTo("ind_dept123_456");
    }

    @Test
    void testGenerateDeparmentOrganzationCode() {
        // Given
        Long parentId = 789L;
        Long id = 101L;

        // When
        String result = OrgCodeUtil.generateDeparmentOrganzationCode(parentId, id);

        // Then
        assertThat(result).isEqualTo("org_789_101");
    }

    @Test
    void testGenerateDeparmentOrganzationCodeByParentOrgCode() {
        // Given
        String parentCode = "org_123";
        Long orgId = 456L;

        // When
        String result = OrgCodeUtil.generateDeparmentOrganzationCodeByParentOrgCode(parentCode, orgId);

        // Then
        assertThat(result).isEqualTo("org_123_456");
    }

    @Test
    void testGenerateOrgCodeFromOrgBeanForIndividual() {
        // Given
        String parentOrgCode = "dept123";
        Long orgId = 456L;
        Integer orgType = OrgCodeUtil.ORG_TYPE_INDIVIDUAL;

        // When
        String result = OrgCodeUtil.generateOrgCodeFromOrgBean(parentOrgCode, orgId, orgType);

        // Then
        assertThat(result).isEqualTo("ind_dept123_456");
    }

    @Test
    void testGenerateOrgCodeFromOrgBeanForDepartment() {
        // Given
        String parentOrgCode = "org_123";
        Long orgId = 456L;
        Integer orgType = OrgCodeUtil.ORG_TYPE_DEPARTMENT;

        // When
        String result = OrgCodeUtil.generateOrgCodeFromOrgBean(parentOrgCode, orgId, orgType);

        // Then
        assertThat(result).isEqualTo("org_123_456");
    }
}
