package com.matariky.commonservice.base.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import com.matariky.commonservice.base.bean.BasicBaseAppVersion;
import com.matariky.commonservice.base.service.BasicBaseAppVersionService;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionAddVO;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionQueryVO;
import com.matariky.commonservice.base.vo.BasicBaseAppVersionUpdateVO;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class BasicBaseAppVersionControllerTest {

    @InjectMocks
    private BasicBaseAppVersionController basicbaseappversioncontroller;

    @Mock
    private BasicBaseAppVersionService basicBaseAppversionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        BasicBaseAppVersionQueryVO vo = new BasicBaseAppVersionQueryVO();
        when(basicBaseAppversionService.getBasicBaseAppversionAll(vo)).thenReturn(new ArrayList<>());

        // When
        AjaxResult result = basicbaseappversioncontroller.list(vo);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(AjaxResult.SUCCESS).isEqualTo(result.get(AjaxResult.MSG_TAG));
        verify(basicBaseAppversionService, times(1)).getBasicBaseAppversionAll(vo);
    }

    @Test
    void testSave() {
        // Given
        BasicBaseAppVersionAddVO addVO = new BasicBaseAppVersionAddVO();
        MultipartFile file = mock(MultipartFile.class);

        // When
        AjaxResult result = basicbaseappversioncontroller.save(addVO, file);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        verify(basicBaseAppversionService, times(1)).createBasicBaseAppversionWithOrg(addVO, file);
    }

    @Test
    void testUpdate() {
        // Given
        BasicBaseAppVersionUpdateVO updateVO = new BasicBaseAppVersionUpdateVO();
        MultipartFile file = mock(MultipartFile.class);

        // When
        AjaxResult result = basicbaseappversioncontroller.update(updateVO, file);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        verify(basicBaseAppversionService, times(1)).updateBasicBaseAppversion(updateVO, file);
    }

    @Test
    void testDel() {
        // Given
        Long id = 1L;

        // When
        AjaxResult result = basicbaseappversioncontroller.del(id);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        verify(basicBaseAppversionService, times(1)).delBasicBaseAppversionById(id);
    }

    @Test
    void testGetOne() {
        // Given
        Long id = 1L;
        BasicBaseAppVersion info = new BasicBaseAppVersion();
        when(basicBaseAppversionService.selectById(id)).thenReturn(info);

        // When
        AjaxResult result = basicbaseappversioncontroller.getOne(id);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(info);
        verify(basicBaseAppversionService, times(1)).selectById(id);
    }

    @Test
    void testGetPrintApp() {
        // Given
        BasicBaseAppVersion version = new BasicBaseAppVersion();
        when(basicBaseAppversionService.getBasicBasePrintApp()).thenReturn(version);

        // When
        AjaxResult result = basicbaseappversioncontroller.getPrintApp();

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        verify(basicBaseAppversionService, times(1)).getBasicBasePrintApp();
    }

    @Test
    void testSaveWithoutFile() {
        // Given
        BasicBaseAppVersionAddVO addVO = new BasicBaseAppVersionAddVO();

        // When
        AjaxResult result = basicbaseappversioncontroller.save(addVO, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        verify(basicBaseAppversionService, times(1)).createBasicBaseAppversionWithOrg(addVO, null);
    }

    @Test
    void testUpdateWithoutFile() {
        // Given
        BasicBaseAppVersionUpdateVO updateVO = new BasicBaseAppVersionUpdateVO();

        // When
        AjaxResult result = basicbaseappversioncontroller.update(updateVO, null);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.MSG_TAG)).isEqualTo(AjaxResult.SUCCESS);
        verify(basicBaseAppversionService, times(1)).updateBasicBaseAppversion(updateVO, null);
    }
}
