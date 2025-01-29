package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.matariky.commonservice.base.bean.BasicBaseRfidInfo;
import com.matariky.commonservice.base.mapper.BasicBaseRfidInfoMapper;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BasicBaseRfidInfoServiceTest {

    @InjectMocks
    private BasicBaseRfidInfoService basicBaseRfidInfoService;

    @Mock
    private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidInfoAll() {
        // Given
        int pageIndex = 1;
        int perPage = 10;
        List<BasicBaseRfidInfo> expectedList = Collections.singletonList(new BasicBaseRfidInfo());
        when(basicBaseRfidInfoMapper.getBasicBaseRfidInfoAll()).thenReturn(expectedList);

        // When
        List<BasicBaseRfidInfo> result = basicBaseRfidInfoService.getBasicBaseRfidInfoAll(pageIndex, perPage);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseRfidInfoAllCount() {
        // Given
        int expectedCount = 5;
        when(basicBaseRfidInfoMapper.getBasicBaseRfidInfoAllCount()).thenReturn(expectedCount);

        // When
        int result = basicBaseRfidInfoService.getBasicBaseRfidInfoAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateBasicBaseRfidInfo() {
        // Given
        BasicBaseRfidInfo bean = new BasicBaseRfidInfo();
        when(basicBaseRfidInfoMapper.createBasicBaseRfidInfo(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidInfoService.createBasicBaseRfidInfo(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidInfo() {
        // Given
        BasicBaseRfidInfo bean = new BasicBaseRfidInfo();
        when(basicBaseRfidInfoMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidInfoService.updateBasicBaseRfidInfo(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidInfoById() {
        // Given
        int id = 1;
        when(basicBaseRfidInfoMapper.delBasicBaseRfidInfoById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidInfoService.delBasicBaseRfidInfoById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidInfoById() {
        // Given
        int id = 1;
        BasicBaseRfidInfo expectedInfo = new BasicBaseRfidInfo();
        when(basicBaseRfidInfoMapper.getBasicBaseRfidInfoById(id)).thenReturn(expectedInfo);

        // When
        BasicBaseRfidInfo result = basicBaseRfidInfoService.getBasicBaseRfidInfoById(id);

        // Then
        assertEquals(expectedInfo, result);
    }

    @Test
    void testGetBasicBaseRfidInfoDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<BasicBaseRfidInfo> expectedList = Collections.singletonList(new BasicBaseRfidInfo());
        when(basicBaseRfidInfoMapper.getBasicBaseRfidInfoDAC(params)).thenReturn(expectedList);

        // When
        List<BasicBaseRfidInfo> result = basicBaseRfidInfoService.getBasicBaseRfidInfoDAC(params, request);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseRfidInfoDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        long expectedCount = 5L;
        when(basicBaseRfidInfoMapper.getBasicBaseRfidInfoDACCount(params)).thenReturn(expectedCount);

        // When
        long result = basicBaseRfidInfoService.getBasicBaseRfidInfoDACCount(params, request);

        // Then
        assertEquals(expectedCount, result);
    }
}
