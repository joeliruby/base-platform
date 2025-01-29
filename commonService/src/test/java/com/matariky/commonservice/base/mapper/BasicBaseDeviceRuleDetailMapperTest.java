package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRuleDetail;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailListVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailVO;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseDeviceRuleDetailMapperTest {

    @InjectMocks
    private BasicBaseDeviceRuleDetailMapper basicbasedeviceruledetailmapper;

    @Mock
    private BasicBaseDeviceRuleDetailMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDeviceruleDetailAll() {
        // Given
        BasicBaseDeviceRuleDetailListVO vo = new BasicBaseDeviceRuleDetailListVO();
        List<BasicBaseDeviceRuleDetailVO> expectedList = Collections.singletonList(new BasicBaseDeviceRuleDetailVO());
        when(mockMapper.getBasicBaseDeviceruleDetailAll(vo)).thenReturn(expectedList);

        // When
        List<BasicBaseDeviceRuleDetailVO> result = basicbasedeviceruledetailmapper.getBasicBaseDeviceruleDetailAll(vo);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseDeviceruleDetail() {
        // Given
        BasicBaseDeviceRuleDetail bean = new BasicBaseDeviceRuleDetail();
        when(mockMapper.createBasicBaseDeviceruleDetail(bean)).thenReturn(1);

        // When
        int result = basicbasedeviceruledetailmapper.createBasicBaseDeviceruleDetail(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseDeviceruleDetailById() {
        // Given
        Long id = 1L;
        when(mockMapper.delBasicBaseDeviceruleDetailById(id)).thenReturn(1);

        // When
        int result = basicbasedeviceruledetailmapper.delBasicBaseDeviceruleDetailById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetCountByEpcRule() {
        // Given
        Long epcRule = 1L;
        when(mockMapper.getCountByEpcRule(epcRule)).thenReturn(10);

        // When
        int result = basicbasedeviceruledetailmapper.getCountByEpcRule(epcRule);

        // Then
        assertEquals(10, result);
    }

    @Test
    void testGetRfidPrictCountByEpcRule() {
        // Given
        Long epcRule = 1L;
        when(mockMapper.getRfidPrictCountByEpcRule(epcRule)).thenReturn(5);

        // When
        int result = basicbasedeviceruledetailmapper.getRfidPrictCountByEpcRule(epcRule);

        // Then
        assertEquals(5, result);
    }
}
