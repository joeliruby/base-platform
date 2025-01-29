package com.matariky.constant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PermissionConstantTest {

    @InjectMocks
    private PermissionConstant permissionconstant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testResourceStatusActive() {
        assertThat(PermissionConstant.RESOURCE_STAUTS_ACTIVE).isEqualTo("1");
    }

    @Test
    void testResourceStatusInactive() {
        assertThat(PermissionConstant.RESOURCE_STAUTS_INACTIVE).isEqualTo("0");
    }

    @Test
    void testResourceTypeMenu() {
        assertThat(PermissionConstant.RESOURCE_TYPE_MENU).isEqualTo(1);
    }

    @Test
    void testResourceTypeButton() {
        assertThat(PermissionConstant.RESOURCE_TYPE_BUTTON).isEqualTo(2);
    }

    @Test
    void testResourceTypeRestApi() {
        assertThat(PermissionConstant.RESOURCE_TYPE_RESTAPI).isEqualTo(0);
    }

    @Test
    void testResourceTypePage() {
        assertThat(PermissionConstant.RESOURCE_TYPE_PAGE).isEqualTo(3);
    }

    @Test
    void testAccessTypeInternal() {
        assertThat(PermissionConstant.ACCESS_TYPE_INTERNAL).isEqualTo(1);
    }

    @Test
    void testAccessTypeExternal() {
        assertThat(PermissionConstant.ACCESS_TYPE_EXTERNAL).isEqualTo(2);
    }

    @Test
    void testAccessTypePopup() {
        assertThat(PermissionConstant.ACCESS_TYPE_POPUP).isEqualTo(3);
    }

    @Test
    void testResourceAttributeDataAccessControl() {
        assertThat(PermissionConstant.RESOURCE_ATTRIBUTE_DATA_ACCESS_CONTROL).isEqualTo(1);
    }

    @Test
    void testResourceAttributeNoDataAccessControl() {
        assertThat(PermissionConstant.RESOURCE_ATTRIBUTE_NO_DATA_ACCESS_CONTROL).isEqualTo(0);
    }

    @Test
    void testCommonDataAccessPrivate() {
        assertThat(PermissionConstant.COMMON_DATA_ACCESS_PRIVATE).isEqualTo("1");
    }

    @Test
    void testCommonDataAccessAll() {
        assertThat(PermissionConstant.COMMON_DATA_ACCESS_ALL).isEqualTo("0");
    }

    @Test
    void testCommonDataAccessLevel() {
        assertThat(PermissionConstant.COMMON_DATA_ACCESS_LEVEL).isEqualTo("2");
    }

    @Test
    void testCommonDataAccessOrg() {
        assertThat(PermissionConstant.COMMON_DATA_ACCESS_ORG).isEqualTo("3");
    }

    @Test
    void testDataPermissionVisitorTypeIndividual() {
        assertThat(PermissionConstant.DATA_PERMISSION_VISITOR_TYPE_INDIVIDUAL).isEqualTo("0");
    }

    @Test
    void testDataPermissionVisitorTypeOrganization() {
        assertThat(PermissionConstant.DATA_PERMISSION_VISITOR_TYPE_ORGANIZATION).isEqualTo("1");
    }

    @Test
    void testDataPermissionVisibleScopeTypeIndividual() {
        assertThat(PermissionConstant.DATA_PERMISSION_VISIBLE_SCOPE_TYPE_INDIVIDUAL).isEqualTo("0");
    }

    @Test
    void testDataPermissionVisibleScopeTypeOrganization() {
        assertThat(PermissionConstant.DATA_PERMISSION_VISIBLE_SCOPE_TYPE_ORGANIZATION).isEqualTo("1");
    }

    @Test
    void testDataAccessPermission() {
        assertThat(PermissionConstant.DATA_ACCESS_PERMISSION).isEqualTo("DATA_ACCESS_PERMISSION");
    }
}
