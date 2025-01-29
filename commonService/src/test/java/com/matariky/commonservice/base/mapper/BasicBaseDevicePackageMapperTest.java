package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseDevicePackage;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceUpgradeListVO;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseDevicePackageMapperTest {

    @InjectMocks
    private BasicBaseDevicePackageMapper basicbasedevicepackagemapper;

    @Mock
    private BasicBaseDevicePackageMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDevicepackageAll() {
        // Given
        BasicBaseDeviceUpgradeListVO vo = new BasicBaseDeviceUpgradeListVO();
        List<BasicBaseDevicePackageInfoVO> expectedList = Collections.singletonList(new BasicBaseDevicePackageInfoVO());
        when(mockMapper.getBasicBaseDevicepackageAll(vo)).thenReturn(expectedList);

        // When
        List<BasicBaseDevicePackageInfoVO> result = basicbasedevicepackagemapper.getBasicBaseDevicepackageAll(vo);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseDevicepackage() {
        // Given
        BasicBaseDevicePackage bean = new BasicBaseDevicePackage();
        when(mockMapper.createBasicBaseDevicepackage(bean)).thenReturn(1);

        // When
        int result = basicbasedevicepackagemapper.createBasicBaseDevicepackage(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDevicepackageById() {
        // Given
        Long id = 1L;
        when(mockMapper.delBasicBaseDevicepackageById(id)).thenReturn(1);

        // When
        int result = basicbasedevicepackagemapper.delBasicBaseDevicepackageById(id);

        // Then
        assertEquals(1, result);
    }

    // Add more test methods for other methods in BasicBaseDevicePackageMapper
}
