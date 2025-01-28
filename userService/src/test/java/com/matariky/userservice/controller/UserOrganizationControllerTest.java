package com.matariky.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.github.pagehelper.Page;
import com.matariky.userservice.bean.UserOrganization;
import com.matariky.userservice.service.UserOrganizationService;
import com.matariky.userservice.service.UserService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class UserOrganizationControllerTest {

    @InjectMocks
    private UserOrganizationController userorganizationcontroller;

    @Mock
    private UserOrganizationService userOrganizationService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "someJwtToken";
        UserOrganization bean = new UserOrganization();
        when(userOrganizationService.getUserOrganizationAll()).thenReturn(new Page<>());

        // When
        AjaxResult result = (AjaxResult) userorganizationcontroller.list(request, bean, tenantId, pageIndex, perPage,
                jwt);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testEdit() {
        // Given
        String tenantId = "tenant1";
        String id = "1";
        UserOrganization userOrganization = new UserOrganization();
        userOrganization.setTenantId(tenantId);
        when(userOrganizationService.getUserOrganizationById(id)).thenReturn(userOrganization);

        // When
        AjaxResult result = (AjaxResult) userorganizationcontroller.edit(request, id, tenantId);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testCreate() {
        // Given
        String orgCode = "orgCode1";
        Long parentId = 1L;
        when(userOrganizationService.selectByOrgCode(orgCode)).thenReturn(new UserOrganization());

        // When
        AjaxResult result = (AjaxResult) userorganizationcontroller.create(request, orgCode, parentId);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testSave() {
        // Given
        String tenantId = "tenant1";
        UserOrganization bean = new UserOrganization();
        bean.setOrganizationName("OrgName");
        bean.setTenantId(tenantId);
        when(userOrganizationService.selectByMap(anyMap())).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = (AjaxResult) userorganizationcontroller.save(bean, tenantId, request, response);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        String tenantId = "tenant1";
        UserOrganization bean = new UserOrganization();
        bean.setOrganizationName("OrgName");
        bean.setTenantId(tenantId);
        when(userOrganizationService.selectByMap(anyMap())).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = userorganizationcontroller.update(bean, request, response, tenantId);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String tenantId = "tenant1";
        String id = "1";
        when(userOrganizationService.getParentcodeByParentId(anyLong(), eq(tenantId))).thenReturn("code");
        when(userOrganizationService.getChildrenOrganization(anyString(), eq(tenantId))).thenReturn(new Long[] { 1L });
        when(userService.getCountByOrganizationId(any(), eq(tenantId))).thenReturn(0);

        // When
        AjaxResult result = (AjaxResult) userorganizationcontroller.del(id, tenantId, request, response);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testQueryTreeList() {
        // Given
        String tenantId = "tenant1";
        when(userOrganizationService.getOrganizationTree(tenantId)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = (AjaxResult) userorganizationcontroller.queryTreeList(tenantId);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testQueryIndTree() {
        // Given
        String tenantId = "tenant1";
        when(userOrganizationService.getOrganizationTreeWithInd(tenantId)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = userorganizationcontroller.queryIndTree(tenantId);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testQueryTreeNode() {
        // Given
        String tenantId = "tenant1";
        when(userOrganizationService.queryTreeNode(tenantId)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = userorganizationcontroller.queryTreeNode(tenantId);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testQueryTreeListView() {
        // Given
        String tenantId = "tenant1";
        String code = "code";
        when(userOrganizationService.queryTreeListView(tenantId, code)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = (AjaxResult) userorganizationcontroller.queryTreeListView(tenantId, code);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testSelectUserAllByTenantId() {
        // Given
        String tenantId = "tenant1";
        when(userOrganizationService.selectByMap(anyMap())).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = (AjaxResult) userorganizationcontroller.selectUserAllByTenantId(tenantId);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }
}
