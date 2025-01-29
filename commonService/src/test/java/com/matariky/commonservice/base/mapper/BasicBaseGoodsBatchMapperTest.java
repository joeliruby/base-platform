package com.matariky.commonservice.base.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.bean.BasicBaseGoodsBatch;
import com.matariky.commonservice.base.vo.BasicBaseGoodsBatchListVO;
import com.matariky.commonservice.base.vo.BasicBaseGoodsBatchResVO;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseGoodsBatchMapperTest {

    @InjectMocks
    private BasicBaseGoodsBatchMapper basicbasegoodsbatchmapper;

    @Mock
    private BasicBaseGoodsBatchMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseGoodsBatchAll() {
        // Given
        BasicBaseGoodsBatchListVO vo = new BasicBaseGoodsBatchListVO();
        List<BasicBaseGoodsBatchResVO> expectedList = Collections.singletonList(new BasicBaseGoodsBatchResVO());
        when(mockMapper.getBasicBaseGoodsBatchAll(vo)).thenReturn(expectedList);

        // When
        List<BasicBaseGoodsBatchResVO> result = basicbasegoodsbatchmapper.getBasicBaseGoodsBatchAll(vo);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseGoodsBatch() {
        // Given
        BasicBaseGoodsBatch bean = new BasicBaseGoodsBatch();
        when(mockMapper.createBasicBaseGoodsBatch(bean)).thenReturn(1);

        // When
        int result = basicbasegoodsbatchmapper.createBasicBaseGoodsBatch(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseGoodsBatch() {
        // Given
        BasicBaseGoodsBatch bean = new BasicBaseGoodsBatch();
        when(mockMapper.updateBasicBaseGoodsBatch(bean)).thenReturn(1);

        // When
        int result = basicbasegoodsbatchmapper.updateBasicBaseGoodsBatch(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseGoodsBatchById() {
        // Given
        Long id = 1L;
        when(mockMapper.delBasicBaseGoodsBatchById(id)).thenReturn(1);

        // When
        int result = basicbasegoodsbatchmapper.delBasicBaseGoodsBatchById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseGoodsBatchById() {
        // Given
        Long id = 1L;
        BasicBaseGoodsBatchResVO expectedVO = new BasicBaseGoodsBatchResVO();
        when(mockMapper.getBasicBaseGoodsBatchById(id)).thenReturn(expectedVO);

        // When
        BasicBaseGoodsBatchResVO result = basicbasegoodsbatchmapper.getBasicBaseGoodsBatchById(id);

        // Then
        assertEquals(expectedVO, result);
    }
}
