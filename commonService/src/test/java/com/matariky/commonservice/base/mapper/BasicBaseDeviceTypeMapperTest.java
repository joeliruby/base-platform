package com.matariky.commonservice.base.mapper;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import com.matariky.commonservice.base.bean.BasicBaseDeviceType;
import com.matariky.commonservice.base.vo.BasicBaseDeviceTypeInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceTypeListVO;
import com.matariky.commonservice.base.vo.DeviceTypeOption;
import com.matariky.model.QueryDataIsolation;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseDeviceTypeMapperTest {

    @InjectMocks
    private BasicBaseDeviceTypeMapper basicbasedevicetypemapper;

    @Mock
    private BasicBaseDeviceTypeMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseDevicetypeAll() {
        // Given
        BasicBaseDeviceTypeListVO vo = new BasicBaseDeviceTypeListVO();
        List<BasicBaseDeviceTypeInfoVO> expectedList = Collections.singletonList(new BasicBaseDeviceTypeInfoVO());
        when(mockMapper.getBasicBaseDevicetypeAll(vo)).thenReturn(expectedList);

        // When
        List<BasicBaseDeviceTypeInfoVO> result = basicbasedevicetypemapper.getBasicBaseDevicetypeAll(vo);

        // Then
        assertThat(result).isEqualTo(expectedList);
    }

    @Test
    void testGetOptionList() {
        // Given
        QueryDataIsolation vo = new QueryDataIsolation();
        List<DeviceTypeOption> expectedList = Collections.singletonList(new DeviceTypeOption());
        when(mockMapper.getOptionList(vo)).thenReturn(expectedList);

        // When
        List<DeviceTypeOption> result = basicbasedevicetypemapper.getOptionList(vo);

        // Then
        assertThat(result).isEqualTo(expectedList);
    }

    @Test
    void testCreateBasicBaseDevicetype() {
        // Given
        BasicBaseDeviceType bean = new BasicBaseDeviceType();
        when(mockMapper.createBasicBaseDevicetype(bean)).thenReturn(1);

        // When
        int result = basicbasedevicetypemapper.createBasicBaseDevicetype(bean);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testDelBasicBaseDevicetypeById() {
        // Given
        Long id = 1L;
        when(mockMapper.delBasicBaseDevicetypeById(id)).thenReturn(1);

        // When
        int result = basicbasedevicetypemapper.delBasicBaseDevicetypeById(id);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testGetBasicBaseDevicetypeById() {
        // Given
        Long id = 1L;
        BasicBaseDeviceType expectedDeviceType = new BasicBaseDeviceType();
        when(mockMapper.getBasicBaseDevicetypeById(id)).thenReturn(expectedDeviceType);

        // When
        BasicBaseDeviceType result = basicbasedevicetypemapper.getBasicBaseDevicetypeById(id);

        // Then
        assertThat(result).isEqualTo(expectedDeviceType);
    }
}
