package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.matariky.commonservice.base.bean.BasicBaseRfidInfo;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseRfidInfoMapperTest {

    @InjectMocks
    private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

    @Mock
    private BasicBaseRfidInfoMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidInfoAll() {
        // Given
        List<BasicBaseRfidInfo> expectedList = Collections.singletonList(new BasicBaseRfidInfo());
        when(mockMapper.getBasicBaseRfidInfoAll()).thenReturn(expectedList);

        // When
        List<BasicBaseRfidInfo> result = basicBaseRfidInfoMapper.getBasicBaseRfidInfoAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseRfidInfo() {
        // Given
        BasicBaseRfidInfo info = new BasicBaseRfidInfo();
        when(mockMapper.createBasicBaseRfidInfo(info)).thenReturn(1);

        // When
        int result = basicBaseRfidInfoMapper.createBasicBaseRfidInfo(info);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidInfo() {
        // Given
        BasicBaseRfidInfo info = new BasicBaseRfidInfo();
        when(mockMapper.updateBasicBaseRfidInfo(info)).thenReturn(1);

        // When
        int result = basicBaseRfidInfoMapper.updateBasicBaseRfidInfo(info);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidInfoById() {
        // Given
        int id = 1;
        when(mockMapper.delBasicBaseRfidInfoById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidInfoMapper.delBasicBaseRfidInfoById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidInfoById() {
        // Given
        int id = 1;
        BasicBaseRfidInfo expectedInfo = new BasicBaseRfidInfo();
        when(mockMapper.getBasicBaseRfidInfoById(id)).thenReturn(expectedInfo);

        // When
        BasicBaseRfidInfo result = basicBaseRfidInfoMapper.getBasicBaseRfidInfoById(id);

        // Then
        assertEquals(expectedInfo, result);
    }

    // Add more test methods for other methods in BasicBaseRfidInfoMapper
}
