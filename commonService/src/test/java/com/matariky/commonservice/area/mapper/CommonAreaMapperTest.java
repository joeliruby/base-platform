package com.matariky.commonservice.area.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.matariky.commonservice.area.bean.CommonArea;
import com.matariky.commonservice.area.bean.CommonAreaVo;

@SpringBootTest
public class CommonAreaMapperTest {

    @InjectMocks
    private CommonAreaMapper commonAreaMapper;

    @Mock
    private CommonAreaMapper mockCommonAreaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonAreaAll() {
        // Given
        Page<CommonArea> expectedPage = new Page<>();
        when(mockCommonAreaMapper.getCommonAreaAll()).thenReturn(expectedPage);

        // When
        Page<CommonArea> result = commonAreaMapper.getCommonAreaAll();

        // Then
        assertEquals(expectedPage, result);
    }

    @Test
    void testGetCommonAreaAllCount() {
        // Given
        int expectedCount = 10;
        when(mockCommonAreaMapper.getCommonAreaAllCount()).thenReturn(expectedCount);

        // When
        int result = commonAreaMapper.getCommonAreaAllCount();

        // Then
        assertEquals(expectedCount, result);
    }

    @Test
    void testCreateCommonArea() {
        // Given
        CommonArea commonArea = new CommonArea();
        when(mockCommonAreaMapper.createCommonArea(commonArea)).thenReturn(1);

        // When
        int result = commonAreaMapper.createCommonArea(commonArea);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateCommonArea() {
        // Given
        CommonArea commonArea = new CommonArea();
        when(mockCommonAreaMapper.updateCommonArea(commonArea)).thenReturn(1);

        // When
        int result = commonAreaMapper.updateCommonArea(commonArea);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelCommonAreaById() {
        // Given
        int id = 1;
        when(mockCommonAreaMapper.delCommonAreaById(id)).thenReturn(1);

        // When
        int result = commonAreaMapper.delCommonAreaById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetCommonAreaById() {
        // Given
        int id = 1;
        CommonArea expectedCommonArea = new CommonArea();
        when(mockCommonAreaMapper.getCommonAreaById(id)).thenReturn(expectedCommonArea);

        // When
        CommonArea result = commonAreaMapper.getCommonAreaById(id);

        // Then
        assertEquals(expectedCommonArea, result);
    }

    @Test
    void testGetAreaByParentId() {
        // Given
        Long parentId = 1L;
        List<CommonArea> expectedList = Arrays.asList(new CommonArea(), new CommonArea());
        when(mockCommonAreaMapper.getAreaByParentId(parentId)).thenReturn(expectedList);

        // When
        List<CommonArea> result = commonAreaMapper.getAreaByParentId(parentId);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testSubNodesById() {
        // Given
        Long nodeId = 1L;
        List<CommonAreaVo> expectedList = Arrays.asList(new CommonAreaVo(), new CommonAreaVo());
        when(mockCommonAreaMapper.subNodesById(nodeId)).thenReturn(expectedList);

        // When
        List<CommonAreaVo> result = commonAreaMapper.subNodesById(nodeId);

        // Then
        assertEquals(expectedList, result);
    }

    // Add more test methods for other methods in CommonAreaMapper
}
