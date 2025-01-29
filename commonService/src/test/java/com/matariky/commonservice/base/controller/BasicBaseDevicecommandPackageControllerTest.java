package com.matariky.commonservice.base.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseDevicecommandPackage;
import com.matariky.commonservice.base.service.BasicBaseDevicecommandPackageService;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class BasicBaseDevicecommandPackageControllerTest {

    @InjectMocks
    private BasicBaseDevicecommandPackageController basicbasedevicecommandpackagecontroller;

    @Mock
    private BasicBaseDevicecommandPackageService basicBaseDevicecommandPackageService;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseDevicecommandPackage bean = new BasicBaseDevicecommandPackage();
        String tenantId = "tenant1";
        int pageIndex = 1;
        int perPage = 10;
        String jwt = "jwtToken";
        List<BasicBaseDevicecommandPackage> packages = Collections.singletonList(bean);
        PageInfo<BasicBaseDevicecommandPackage> pageInfo = new PageInfo<>(packages);
        when(basicBaseDevicecommandPackageService.getBasicBaseDevicecommandPackageAll(bean)).thenReturn(packages);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecommandpackagecontroller.list(request, bean, tenantId,
                pageIndex, perPage, jwt);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(pageInfo);
    }

    @Test
    void testDaclist() {
        // Given
        String tenantId = "tenant1";
        String jwt = "jwtToken";
        Map<String, Object> params = mock(Map.class);
        when(request.getHeader("id")).thenReturn("id123");
        when(params.get("index")).thenReturn("1");
        when(params.get("perPage")).thenReturn("10");
        List<BasicBaseDevicecommandPackage> packages = Collections.singletonList(new BasicBaseDevicecommandPackage());
        when(basicBaseDevicecommandPackageService.getBasicBaseDevicecommandPackageDAC(params, request))
                .thenReturn(packages);
        when(basicBaseDevicecommandPackageService.getBasicBaseDevicecommandPackageDACCount(params, request))
                .thenReturn(1L);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecommandpackagecontroller.daclist(request, params, tenantId,
                jwt);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(((PageInfo<?>) result.get(AjaxResult.DATA_TAG)).getList()).isEqualTo(packages);
    }

    @Test
    void testSave() {
        // Given
        BasicBaseDevicecommandPackage bean = new BasicBaseDevicecommandPackage();
        when(basicBaseDevicecommandPackageService.createBasicBaseDevicecommandPackageWithOrg(bean, request))
                .thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecommandpackagecontroller.save(bean, request, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseDevicecommandPackage bean = new BasicBaseDevicecommandPackage();
        when(basicBaseDevicecommandPackageService.updateBasicBaseDevicecommandPackage(bean)).thenReturn(1);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecommandpackagecontroller.update(bean, request, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testDelete() {
        // Given
        String id = "1";
        when(basicBaseDevicecommandPackageService.deleteById(Long.parseLong(id))).thenReturn(true);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecommandpackagecontroller.del(id, request, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseDevicecommandPackage bean = new BasicBaseDevicecommandPackage();
        when(basicBaseDevicecommandPackageService.selectById(id)).thenReturn(bean);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicecommandpackagecontroller.getOne(id, request, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(bean);
    }
}
