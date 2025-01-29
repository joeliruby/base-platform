package com.matariky.commonservice.base.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.matariky.commonservice.base.bean.BasicBaseDevice;
import com.matariky.commonservice.base.service.BasicBaseDeviceService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import java.util.Collections;

@SpringBootTest
public class BasicBaseDeviceControllerTest {

    @InjectMocks
    private BasicBaseDeviceController basicBaseDeviceController;

    @Mock
    private BasicBaseDeviceService basicBaseDeviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseDeviceListVO vo = new BasicBaseDeviceListVO();
        when(basicBaseDeviceService.getBasicBaseDeviceAll(vo)).thenReturn(Collections.emptyList());

        // When
        AjaxResult result = basicBaseDeviceController.list(vo);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
    }

    @Test
    void testSave() {
        // Given
        BasicBaseDeviceAddVO addVO = new BasicBaseDeviceAddVO();

        // When
        AjaxResult result = basicBaseDeviceController.save(addVO);

        // Then
        verify(basicBaseDeviceService, times(1)).createBasicBaseDeviceWithOrg(addVO);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseDeviceUpdateVO updateVO = new BasicBaseDeviceUpdateVO();

        // When
        AjaxResult result = basicBaseDeviceController.update(updateVO);

        // Then
        verify(basicBaseDeviceService, times(1)).updateBasicBaseDevice(updateVO);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testDel() {
        // Given
        Long id = 1L;

        // When
        AjaxResult result = basicBaseDeviceController.del(id);

        // Then
        verify(basicBaseDeviceService, times(1)).delBasicBaseDeviceById(id);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testDownLoadTemplate() {
        // When
        AjaxResult result = basicBaseDeviceController.downLoadTemplate();

        // Then
        verify(basicBaseDeviceService, times(1)).downLoadTemplate();
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testImportData() {
        // Given
        MultipartFile file = mock(MultipartFile.class);

        // When
        AjaxResult result = basicBaseDeviceController.importData(file);

        // Then
        verify(basicBaseDeviceService, times(1)).importData(file);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseDevice basicBaseDevice = new BasicBaseDevice();
        basicBaseDevice.setLatitude("10.0");
        basicBaseDevice.setLongitude("20.0");
        when(basicBaseDeviceService.selectById(id)).thenReturn(basicBaseDevice);

        // When
        AjaxResult result = basicBaseDeviceController.getOne(id);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
    }

    @Test
    void testGetDbmOption() {
        // When
        AjaxResult result = basicBaseDeviceController.getDbmOption();

        // Then
        verify(basicBaseDeviceService, times(1)).getDbmOption();
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
    }

    @Test
    void testGetPrintOption() {
        // When
        AjaxResult result = basicBaseDeviceController.list();

        // Then
        verify(basicBaseDeviceService, times(1)).getPrintOptionList();
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
    }

    @Test
    void testGetCodeOption() {
        // Given
        CodeOptionListVO vo = new CodeOptionListVO();

        // When
        AjaxResult result = basicBaseDeviceController.getCodeOption(vo);

        // Then
        verify(basicBaseDeviceService, times(1)).getCodeOption(vo);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isNotNull();
    }
}
