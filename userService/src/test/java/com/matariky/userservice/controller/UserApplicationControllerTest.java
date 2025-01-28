package com.matariky.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.github.pagehelper.Page;
import com.matariky.userservice.bean.UserApplication;
import com.matariky.userservice.dto.UserApplicationDTO;
import com.matariky.userservice.service.UserApplicationService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class UserApplicationControllerTest {

    @InjectMocks
    private UserApplicationController userApplicationController;

    @Mock
    private UserApplicationService userApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOne() {
        // Given
        Long applicationId = 1L;
        UserApplication application = new UserApplication();
        application.setId(applicationId);
        when(userApplicationService.selectById(applicationId)).thenReturn(application);

        // When
        AjaxResult result = (AjaxResult) userApplicationController.getOne(applicationId, null, null, null);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testList() {
        // Given
        Map<String, Object> params = new HashMap<>();
        Page<UserApplication> applications = new Page<>();
        applications.addAll(new ArrayList<UserApplication>());
        when(userApplicationService.getUserApplicationAll(params)).thenReturn(applications);

        // When
        AjaxResult result = (AjaxResult) userApplicationController.list(null, params, "tenantId", "jwt");

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testSave() {
        // Given
        UserApplicationDTO applicationDTO = new UserApplicationDTO();
        applicationDTO.setApplicationName("Test App");
        applicationDTO.setTenantId("tenantId");

        // When
        AjaxResult result = (AjaxResult) userApplicationController.save(applicationDTO, "jwt", null, null);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        UserApplicationDTO applicationDTO = new UserApplicationDTO();
        applicationDTO.setId(1L);

        // When
        AjaxResult result = userApplicationController.update(applicationDTO, null, null);

        // Then
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";

        // When
        AjaxResult result = (AjaxResult) userApplicationController.del(id, null, null);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof AjaxResult);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
    }

    // Add more test methods for other methods in UserApplicationController
}
