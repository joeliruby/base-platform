package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.base.bean.BasicBaseDevicePackage;
import com.matariky.commonservice.base.bean.BasicBaseDeviceUpgrade;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDevicePackageMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceUpgradeMapper;
import com.matariky.commonservice.base.vo.BasicBaseDeviceUpgradeAddDTO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceUpgradeResVO;
import com.matariky.commonservice.base.vo.DeviceUpgradeListVO;
import com.matariky.commonservice.base.vo.UpgradeDeviceVO;
import com.matariky.commonservice.base.vo.UpgradeListVO;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;

@SpringBootTest
public class BasicBaseDeviceUpgradeServiceTest {

    @InjectMocks
    private BasicBaseDeviceUpgradeService basicbasedeviceupgradeservice;

    @Mock
    private BasicBaseDeviceUpgradeMapper basicBaseDeviceupgradeMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private BasicBaseDevicePackageMapper basicBaseDevicepackageMapper;

    @Mock
    private BasicBaseDeviceMapper basicBaseDeviceMapper;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Given
        UpgradeListVO vo = new UpgradeListVO();
        when(request.getHeader("id")).thenReturn("someId");
        when(basicBaseDeviceupgradeMapper.getBasicBaseDeviceupgradeAll(vo)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseDeviceUpgradeResVO> result = basicbasedeviceupgradeservice.list(vo);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseDeviceupgradeWithOrg() {
        // Given
        BasicBaseDeviceUpgradeAddDTO addDTO = new BasicBaseDeviceUpgradeAddDTO();
        BasicBaseDevicePackage devicePackage = new BasicBaseDevicePackage();
        when(basicBaseDevicepackageMapper.selectById(addDTO.getPackageId())).thenReturn(devicePackage);

        // When
        basicbasedeviceupgradeservice.createBasicBaseDeviceupgradeWithOrg(addDTO);

        // Then
        verify(basicBaseDevicepackageMapper, times(1)).selectById(addDTO.getPackageId());
    }

    @Test
    void testUpgradeDeviceList() {
        // Given
        Long packageId = 1L;
        when(basicBaseDeviceupgradeMapper.getUpgradeDeviceList(packageId)).thenReturn(Collections.emptyList());

        // When
        List<UpgradeDeviceVO> result = basicbasedeviceupgradeservice.upgradeDeviceList(packageId);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testDelete() {
        // Given
        DeviceUpgradeListVO vo = new DeviceUpgradeListVO();
        vo.setIdList(Collections.singletonList(1L));

        // When
        basicbasedeviceupgradeservice.delete(vo);

        // Then
        verify(basicBaseDeviceupgradeMapper, times(1)).updateById(any(BasicBaseDeviceUpgrade.class));
    }
}
