package com.matariky.commonservice.base.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.matariky.commonservice.base.service.BasicBaseDeviceUpgradeService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseDeviceUpgradeControllerTest {

    @InjectMocks
    private BasicBaseDeviceUpgradeController basicbasedeviceupgradecontroller;

    @Mock
    private BasicBaseDeviceUpgradeService basicBaseDeviceupgradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        UpgradeListVO vo = new UpgradeListVO();
        List<BasicBaseDeviceUpgradeResVO> expectedList = Collections.singletonList(new BasicBaseDeviceUpgradeResVO());
        when(basicBaseDeviceupgradeService.list(vo)).thenReturn(expectedList);

        // When
        AjaxResult result = basicbasedeviceupgradecontroller.list(vo);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(expectedList);
    }

    @Test
    void testSave() {
        // Given
        BasicBaseDeviceUpgradeAddDTO addDTO = new BasicBaseDeviceUpgradeAddDTO();

        // When
        AjaxResult result = basicbasedeviceupgradecontroller.save(addDTO);

        // Then
        verify(basicBaseDeviceupgradeService, times(1)).createBasicBaseDeviceupgradeWithOrg(addDTO);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpgradeDeviceList() {
        // Given
        Long packageId = 1L;
        List<UpgradeDeviceVO> expectedList = Collections.singletonList(new UpgradeDeviceVO());
        when(basicBaseDeviceupgradeService.upgradeDeviceList(packageId)).thenReturn(expectedList);

        // When
        AjaxResult result = basicbasedeviceupgradecontroller.upgradeDeviceList(packageId);

        // Then
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(AjaxResult.DATA_TAG)).isEqualTo(expectedList);
    }

    @Test
    void testDel() {
        // Given
        DeviceUpgradeListVO vo = new DeviceUpgradeListVO();

        // When
        AjaxResult result = basicbasedeviceupgradecontroller.del(vo);

        // Then
        verify(basicBaseDeviceupgradeService, times(1)).delete(vo);
        assertThat(result.get(AjaxResult.CODE_TAG)).isEqualTo(HttpStatus.OK.value());
    }
}
