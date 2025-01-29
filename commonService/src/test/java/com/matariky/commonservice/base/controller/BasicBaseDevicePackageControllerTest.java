package com.matariky.commonservice.base.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseDevicePackage;
import com.matariky.commonservice.base.service.BasicBaseDevicePackageService;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageAddVO;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageUpdateVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceUpgradeListVO;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import java.util.Collections;

@SpringBootTest
public class BasicBaseDevicePackageControllerTest {

    @InjectMocks
    private BasicBaseDevicePackageController basicbasedevicepackagecontroller;

    @Mock
    private BasicBaseDevicePackageService basicBaseDevicepackageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseDeviceUpgradeListVO vo = new BasicBaseDeviceUpgradeListVO();
        PageInfo<BasicBaseDevicePackageInfoVO> pageInfo = new PageInfo<>(Collections.emptyList());
        when(basicBaseDevicepackageService.getBasicBaseDevicepackageAll(vo)).thenReturn(pageInfo.getList());

        // When
        AjaxResult result = basicbasedevicepackagecontroller.list(vo);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(pageInfo, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSave() {
        // Given
        MultipartFile file = mock(MultipartFile.class);
        BasicBaseDevicePackageAddVO addVO = new BasicBaseDevicePackageAddVO();

        // When
        AjaxResult result = basicbasedevicepackagecontroller.save(file, addVO);

        // Then
        verify(basicBaseDevicepackageService, times(1)).createBasicBaseDevicepackageWithOrg(addVO, file);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdate() {
        // Given
        MultipartFile file = mock(MultipartFile.class);
        BasicBaseDevicePackageUpdateVO updateVO = new BasicBaseDevicePackageUpdateVO();
        String jwt = "some-jwt-token";

        // When
        AjaxResult result = basicbasedevicepackagecontroller.update(file, updateVO, jwt);

        // Then
        verify(basicBaseDevicepackageService, times(1)).updateBasicBaseDevicepackage(updateVO, file, jwt);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDel() {
        // Given
        Long id = 1L;

        // When
        AjaxResult result = basicbasedevicepackagecontroller.del(id);

        // Then
        verify(basicBaseDevicepackageService, times(1)).delBasicBaseDevicepackageById(id);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseDevicePackage info = new BasicBaseDevicePackage();
        when(basicBaseDevicepackageService.selectById(id)).thenReturn(info);

        // When
        AjaxResult result = (AjaxResult) basicbasedevicepackagecontroller.getOne(id);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(info, result.get(AjaxResult.DATA_TAG));
    }
}
