package com.matariky.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import com.github.pagehelper.Page;
import com.matariky.userservice.bean.UserRole;
import com.matariky.userservice.service.UserRoleService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class UserRoleControllerTest {

    @InjectMocks
    private UserRoleController userRoleController;

    @Mock
    private UserRoleService userRoleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        Map<String, Object> map = new HashMap<>();
        map.put("index", "1");
        map.put("perPage", "20");
        List<UserRole> userList = new ArrayList<>();
        Page<UserRole> userPage = new com.github.pagehelper.Page<>();
        userPage.addAll(userList);

        // When
        Object result = userRoleController.list(null, map, "tenantId");

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        AjaxResult ajaxResult = (AjaxResult) result;
        userPage.close();
        assertEquals(HttpStatus.OK.value(), ajaxResult.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testEdit() {
        // Given
        UserRole userRole = new UserRole();
        when(userRoleService.getUserRoleById(1L)).thenReturn(userRole);

        // When
        Object result = userRoleController.edit(null, "1");

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        AjaxResult ajaxResult = (AjaxResult) result;
        assertEquals(HttpStatus.OK.value(), ajaxResult.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testSave() {
        // Given
        UserRole userRole = new UserRole();
        userRole.setRoleName("roleName");
        when(userRoleService.createUserRole(userRole)).thenReturn(1);

        // When
        Object result = userRoleController.save(userRole, "tenantId", null, null);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        AjaxResult ajaxResult = (AjaxResult) result;
        assertEquals(HttpStatus.OK.value(), ajaxResult.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        UserRole userRole = new UserRole();
        userRole.setRoleName("roleName");
        when(userRoleService.updateUserRole(userRole)).thenReturn(1);

        // When
        Object result = userRoleController.update(userRole, null, null, "tenantId");

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        AjaxResult ajaxResult = (AjaxResult) result;
        assertEquals(HttpStatus.OK.value(), ajaxResult.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testDelete() {
        // Given
        when(userRoleService.updateDeleteTimeById(any())).thenReturn(1);

        // When
        Object result = userRoleController.del("1", null, null);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        AjaxResult ajaxResult = (AjaxResult) result;
        assertEquals(HttpStatus.OK.value(), ajaxResult.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testSelectRole() {
        // Given
        List<UserRole> roleList = new ArrayList<>();
        when(userRoleService.selectRole("tenantId")).thenReturn(roleList);

        // When
        Object result = userRoleController.selectRole("tenantId");

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        AjaxResult ajaxResult = (AjaxResult) result;
        assertEquals(HttpStatus.OK.value(), ajaxResult.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testGetPermissionByUser() {
        // Given
        when(userRoleService.getPermissionByRole(anyLong(), anyString(), anyLong())).thenReturn(new ArrayList<>());
        when(userRoleService.getPermissionIdByRoleId(anyLong())).thenReturn(new ArrayList<>());

        // When
        Object result = userRoleController.getPermissionByUser(null, "roleId", "tenantId");

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        AjaxResult ajaxResult = (AjaxResult) result;
        assertEquals(HttpStatus.OK.value(), ajaxResult.get(AjaxResult.CODE_TAG));
    }
}
