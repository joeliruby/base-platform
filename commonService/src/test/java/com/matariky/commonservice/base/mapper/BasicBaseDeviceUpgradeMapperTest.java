package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseDeviceUpgrade;
import com.matariky.commonservice.base.vo.BasicBaseDeviceUpgradeResVO;
import com.matariky.commonservice.base.vo.UpgradeDeviceVO;
import com.matariky.commonservice.base.vo.UpgradeListVO;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class BasicBaseDeviceUpgradeMapperTest {

    @InjectMocks
    private BasicBaseDeviceUpgradeMapper basicbasedeviceupgrademapper;

    @Mock
    private BasicBaseDeviceUpgradeMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBasicBaseDeviceupgrade() {
        // Given
        BasicBaseDeviceUpgrade bean = new BasicBaseDeviceUpgrade();
        when(mockMapper.createBasicBaseDeviceupgrade(bean)).thenReturn(1);

        // When
        int result = basicbasedeviceupgrademapper.createBasicBaseDeviceupgrade(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetUpgradeDeviceList() {
        // Given
        Long packageId = 1L;
        List<UpgradeDeviceVO> expectedList = Arrays.asList(new UpgradeDeviceVO());
        when(mockMapper.getUpgradeDeviceList(packageId)).thenReturn(expectedList);

        // When
        List<UpgradeDeviceVO> result = basicbasedeviceupgrademapper.getUpgradeDeviceList(packageId);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseDeviceupgradeAll() {
        // Given
        UpgradeListVO vo = new UpgradeListVO();
        List<BasicBaseDeviceUpgradeResVO> expectedList = Arrays.asList(new BasicBaseDeviceUpgradeResVO());
        when(mockMapper.getBasicBaseDeviceupgradeAll(vo)).thenReturn(expectedList);

        // When
        List<BasicBaseDeviceUpgradeResVO> result = basicbasedeviceupgrademapper.getBasicBaseDeviceupgradeAll(vo);

        // Then
        assertEquals(expectedList, result);
    }

    // Add more test methods for other methods in BasicBaseDeviceUpgradeMapper
}
