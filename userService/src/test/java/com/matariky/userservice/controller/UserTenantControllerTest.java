package com.matariky.userservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.userservice.bean.UserTenant;
import com.matariky.userservice.service.UserTenantService;

@SpringBootTest
public class UserTenantControllerTest {

    @InjectMocks
    private UserTenantController usertenantcontroller;

    @Mock
    private UserTenantService userTenantService;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private MinioUtil minioUtil;

    @Mock
    private IdentifierGenerator idGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("index", "1");
        params.put("perPage", "10");

        // When
        Object result = usertenantcontroller.list(null, params, "tenantId");

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testEdit() {
        // Given
        String id = "1";
        when(userTenantService.getUserTenantById(id)).thenReturn(new UserTenant());

        // When
        Object result = usertenantcontroller.edit(null, id, "tenantId");

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testGetParentNameAndId() {
        // Given
        when(userTenantService.selectBytenantCode("tenantId")).thenReturn(new UserTenant());

        // When
        Object result = usertenantcontroller.getParentNameAndId("tenantId");

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testSave() {
        // Given
        UserTenant userTenant = new UserTenant();

        // When
        Object result = usertenantcontroller.save(userTenant, null, null, "tenantId");

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testUpdate() {
        // Given
        UserTenant userTenant = new UserTenant();

        // When
        Object result = usertenantcontroller.update(userTenant, null, null, "tenantId");

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testUpdateLogo() throws Exception {
        // Given
        MultipartFile file = mock(MultipartFile.class);
        when(file.getInputStream()).thenReturn(null);
        when(file.getOriginalFilename()).thenReturn("logo.png");

        // When
        Object result = usertenantcontroller.updateLogo(file, "bucket", "objectName", "tenantId");

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testSwitchTheme() {
        // Given
        when(userTenantService.selectById("tenantId")).thenReturn(new UserTenant());

        // When
        Object result = usertenantcontroller.switchThem("tenantId", "theme", null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";

        // When
        Object result = usertenantcontroller.del(id, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testSelectTenant() {
        // Given
        when(userTenantService.selectBytenantCode("tenantId")).thenReturn(new UserTenant());

        // When
        Object result = usertenantcontroller.selectTenant("tenantId", null);

        // Then
        assertThat(result).isNotNull();
    }
}
