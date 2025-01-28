package com.matariky.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.matariky.userservice.bean.User;
import com.matariky.userservice.service.UserService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testResetPassword() {
        // Given
        Long userId = 1L;
        String tenantId = "tenant1";
        User user = new User();
        user.setId(userId);
        user.setPazzword("oldPassword");
        when(userService.selectById(userId)).thenReturn(user);

        // When
        Object result = userController.resetPassword(userId, tenantId);

        // Then
        assertNotNull(result);
        verify(userService).updateById(user);
    }

    @Test
    void testListExcelForSpecificUsers() {
        // Given
        HttpServletResponse response = mock(HttpServletResponse.class);
        List<Map<String, Object>> userList = new ArrayList<>();
        String tenantId = "tenant1";
        String fileName = "testFile";
        String jwt = "testJwt";

        // When
        String result = userController.listExcelForSpecificUsers(response, userList, tenantId, fileName, jwt);

        // Then
        assertNotNull(result);
    }

    @Test
    void testDownloadFile() throws IOException {
        // Given
        String fileName = "testFile";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // When
        userController.downloadFile(fileName, request, response);

        // Then
        // Add assertions as needed
    }

    @Test
    void testList() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        Map<String, Object> map = new HashMap<>();
        String tenantId = "tenant1";

        // When
        AjaxResult result = userController.list(request, map, tenantId);

        // Then
        assertNotNull(result);
    }

    @Test
    void testEdit() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        Long id = 1L;
        String tenantId = "tenant1";
        User user = new User();
        when(userService.getUserById(id)).thenReturn(user);

        // When
        AjaxResult result = userController.edit(request, id, tenantId);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetPermissionByUser() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        Long id = 1L;
        String tenantId = "tenant1";

        // When
        Object result = userController.getPermissionByUser(request, id, tenantId);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetPermissionByAll() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        Long id = 1L;
        String tenantId = "tenant1";

        // When
        Object result = userController.getPermissionByAll(request, id, tenantId);

        // Then
        assertNotNull(result);
    }

    @Test
    void testSave() {
        // Given
        User user = new User();
        String tenantId = "tenant1";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // When
        Object result = userController.save(user, tenantId, request, response);

        // Then
        assertNotNull(result);
    }

    @Test
    void testUpdate() {
        // Given
        User user = new User();
        String tenantId = "tenant1";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // When
        Object result = userController.update(user, tenantId, request, response);

        // Then
        assertNotNull(result);
    }

    @Test
    void testUpdateUserInfo() {
        // Given
        User user = new User();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // When
        Object result = userController.updateUserInfo(user, request, response);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetUserDetail() {
        // Given
        String tenantId = "tenant1";
        Long userId = 1L;
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // When
        Object result = userController.getUserDetail(tenantId, userId, request, response);

        // Then
        assertNotNull(result);
    }

    @Test
    void testDel() {
        // Given
        String id = "1";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // When
        Object result = userController.del(id, request, response);

        // Then
        assertNotNull(result);
    }

    @Test
    void testTemplateDownload() {
        // Given
        HttpServletResponse response = mock(HttpServletResponse.class);

        // When
        userController.templateDownload(response);

        // Then
        // Add assertions as needed
    }

    @Test
    void testSelectUserAllByTenantId() {
        // Given
        String tenantId = "tenant1";

        // When
        AjaxResult result = userController.selectUserAllByTenantId(tenantId);

        // Then
        assertNotNull(result);
    }

    @Test
    void testSwitchTheme() {
        // Given
        String tenantId = "tenant1";
        Long userId = 1L;
        String theme = "dark";
        HttpServletRequest request = mock(HttpServletRequest.class);

        // When
        Object result = userController.switchTheme(tenantId, userId, theme, request);

        // Then
        assertNotNull(result);
    }

    @Test
    void testUploadPercentage() {
        // Given
        MultipartFile file = mock(MultipartFile.class);
        String nonce = "testNonce";

        // When
        Object result = userController.uploadPercentage(file, nonce);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetAdmin() {
        // Given
        String jwt = "testJwt";
        String tenantCode = "tenant1";

        // When
        Object result = userController.getAdmin(jwt, tenantCode);

        // Then
        assertNotNull(result);
    }

    @Test
    void testAddAdmin() {
        // Given
        String jwt = "testJwt";
        String tenantId = "tenant1";
        User user = new User();
        HttpServletRequest request = mock(HttpServletRequest.class);

        // When
        Object result = userController.addAdmin(jwt, tenantId, user, request);

        // Then
        assertNotNull(result);
    }

    @Test
    void testUserBulkUpload() throws IOException {
        // Given
        MultipartFile file = mock(MultipartFile.class);
        String nonce = "testNonce";
        String jwt = "testJwt";

        // When
        Object result = userController.userBulkUpload(file, nonce, jwt);

        // Then
        assertNotNull(result);
    }

    @Test
    void testPassword() {
        // Given
        Map<String, Object> map = new HashMap<>();
        map.put("password", "oldPassword");
        map.put("newPassword", "newPassword");
        Long userId = 1L;
        String tenantId = "tenant1";
        HttpServletRequest request = mock(HttpServletRequest.class);

        // When
        Object result = userController.password(map, userId, tenantId, request);

        // Then
        assertNotNull(result);
    }

    @Test
    void testSelectTenant() {
        // Given
        String tenantId = "tenant1";
        String tenantCode = "tenantCode1";

        // When
        Object result = userController.selectTenant(tenantId, tenantCode);

        // Then
        assertNotNull(result);
    }

    @Test
    void testUpdateLogo() throws Exception {
        // Given
        MultipartFile uploadfile = mock(MultipartFile.class);
        String bucket = "bucket";
        String objectName = "objectName";
        Long userId = 1L;

        // When
        AjaxResult result = userController.updateLogo(uploadfile, bucket, objectName, userId);

        // Then
        assertNotNull(result);
    }
}
