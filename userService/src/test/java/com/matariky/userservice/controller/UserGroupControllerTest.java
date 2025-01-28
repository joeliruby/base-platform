package com.matariky.userservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.mockito.Mock;

import com.matariky.userservice.bean.UserGroup;
import com.matariky.userservice.service.UserGroupService;
import com.matariky.userservice.service.UserOrganizationService;
import com.matariky.userservice.service.UserTenantService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class UserGroupControllerTest {

    @InjectMocks
    private UserGroupController usergroupcontroller;

    @Mock
    private UserGroupService userGroupService;

    @Mock
    private UserTenantService userTenantService;

    @Mock
    private UserOrganizationService orgService;

    @Mock
    private HttpServletRequest request;

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
        String tenantId = "tenant123";

        // When
        AjaxResult result = (AjaxResult) usergroupcontroller.list(request, map, tenantId);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testEdit() {
        // Given
        Long id = 1L;
        String tenantId = "tenant123";
        when(userGroupService.getUserGroupById(id)).thenReturn(new UserGroup());

        // When
        AjaxResult result = (AjaxResult) usergroupcontroller.edit(request, id, tenantId);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testSave() {
        // Given
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName("Test Group");
        String tenantId = "tenant123";

        // When
        AjaxResult result = (AjaxResult) usergroupcontroller.save(userGroup, tenantId, request, null);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName("Updated Group");
        String tenantId = "tenant123";

        // When
        AjaxResult result = (AjaxResult) usergroupcontroller.update(userGroup, request, null, tenantId);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String id = "1,2,3";

        // When
        AjaxResult result = (AjaxResult) usergroupcontroller.del(id, request, null);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testSelectGroup() {
        // Given
        String tenantId = "tenant123";

        // When
        AjaxResult result = (AjaxResult) usergroupcontroller.selectGroup(tenantId);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testGetPermissionByGroup() {
        // Given
        Long groupId = 1L;
        String tenantId = "tenant123";

        // When
        AjaxResult result = (AjaxResult) usergroupcontroller.getPermissionByGroup(request, groupId, tenantId);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
    }
}
