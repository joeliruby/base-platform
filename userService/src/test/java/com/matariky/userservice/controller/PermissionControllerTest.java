package com.matariky.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.matariky.userservice.bean.Permission;
import com.matariky.userservice.bean.UserApplication;
import com.matariky.userservice.service.PermissionService;
import com.matariky.userservice.service.UserApplicationService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class PermissionControllerTest {

    @InjectMocks
    private PermissionController permissionController;

    @Mock
    private PermissionService permissionService;

    @Mock
    private UserApplicationService applicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetDataPermission() {
        // Given
        String tenantId = "tenant1";
        Long permissionId = 1L;
        String dataPermissionFlag = "flag";
        String jwt = "someJwtToken";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Permission permission = new Permission();
        when(permissionService.selectById(permissionId)).thenReturn(permission);

        // When
        AjaxResult result = (AjaxResult) permissionController.setDataPermission(request, tenantId, permissionId,
                dataPermissionFlag, jwt);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testSearchPermissionByNamePrefix() {
        // Given
        String tenantId = "tenant1";
        String jwt = "someJwtToken";
        Long applicationId = 1L;
        String permissionName = "perm";
        UserApplication application = new UserApplication();
        when(applicationService.selectById(applicationId)).thenReturn(application);

        // When
        AjaxResult result = (AjaxResult) permissionController.searchPermissionByNamePrefix(tenantId, jwt, applicationId,
                permissionName);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetDataPermissionLevelByAlias() {
        // Given
        String tenantId = "tenant1";
        String jwt = "someJwtToken";
        Long applicationId = 1L;
        String url = "someUrl";
        UserApplication application = new UserApplication();
        when(applicationService.selectById(applicationId)).thenReturn(application);

        // When
        AjaxResult result = (AjaxResult) permissionController.getDataPermissionLevelByAlias(tenantId, jwt,
                applicationId, url);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    // Add more test methods for other methods in PermissionController
}
