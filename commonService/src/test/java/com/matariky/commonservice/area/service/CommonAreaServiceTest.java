package com.matariky.commonservice.area.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.mockito.Mock;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.matariky.commonservice.area.bean.CommonArea;
import com.matariky.commonservice.area.bean.CommonAreaVo;
import com.matariky.commonservice.area.mapper.CommonAreaMapper;

@SpringBootTest
public class CommonAreaServiceTest {

    @InjectMocks
    private CommonAreaService commonAreaService;

    @Mock
    private CommonAreaMapper commonAreaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonAreaAll() {
        // Given
        Page<CommonArea> expectedPage = PageHelper.startPage(1, 10);
        when(commonAreaMapper.getCommonAreaAll()).thenReturn(expectedPage);

        // When
        Page<CommonArea> result = commonAreaService.getCommonAreaAll();

        // Then
        assertThat(result).isEqualTo(expectedPage);
    }

    @Test
    void testGetCommonAreaAllCount() {
        // Given
        int expectedCount = 10;
        when(commonAreaMapper.getCommonAreaAllCount()).thenReturn(expectedCount);

        // When
        int result = commonAreaService.getCommonAreaAllCount();

        // Then
        assertThat(result).isEqualTo(expectedCount);
    }

    @Test
    void testCreateCommonArea() {
        // Given
        CommonArea commonArea = new CommonArea();
        when(commonAreaMapper.createCommonArea(commonArea)).thenReturn(1);

        // When
        int result = commonAreaService.createCommonArea(commonArea);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testUpdateCommonArea() {
        // Given
        CommonArea commonArea = new CommonArea();
        when(commonAreaMapper.updateCommonArea(commonArea)).thenReturn(1);

        // When
        int result = commonAreaService.updateCommonArea(commonArea);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testDelCommonAreaById() {
        // Given
        int id = 1;
        when(commonAreaMapper.delCommonAreaById(id)).thenReturn(1);

        // When
        int result = commonAreaService.delCommonAreaById(id);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testGetCommonAreaById() {
        // Given
        int id = 1;
        CommonArea expectedCommonArea = new CommonArea();
        when(commonAreaMapper.getCommonAreaById(id)).thenReturn(expectedCommonArea);

        // When
        CommonArea result = commonAreaService.getCommonAreaById(id);

        // Then
        assertThat(result).isEqualTo(expectedCommonArea);
    }

    @Test
    void testGetAreaByParentId() {
        // Given
        Long parentId = 1L;
        List<CommonArea> expectedList = Arrays.asList(new CommonArea(), new CommonArea());
        when(commonAreaMapper.getAreaByParentId(parentId)).thenReturn(expectedList);

        // When
        List<CommonArea> result = commonAreaService.getAreaByParentId(parentId);

        // Then
        assertThat(result).isEqualTo(expectedList);
    }

    @Test
    void testSubNodesById() {
        // Given
        Long nodeId = 1L;
        List<CommonAreaVo> expectedList = Arrays.asList(new CommonAreaVo(), new CommonAreaVo());
        when(commonAreaMapper.subNodesById(nodeId)).thenReturn(expectedList);

        // When
        List<CommonAreaVo> result = commonAreaService.subNodesById(nodeId);

        // Then
        assertThat(result).isEqualTo(expectedList);
    }
}
