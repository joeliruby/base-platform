package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.base.mapper.BasicBaseGoodsBatchMapper;
import com.matariky.commonservice.base.vo.*;
import com.matariky.exception.QslException;
import com.matariky.utils.TokenUtils;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseGoodsBatchServiceTest {

    @InjectMocks
    private BasicBaseGoodsBatchService basicBaseGoodsBatchService;

    @Mock
    private BasicBaseGoodsBatchMapper basicBaseGoodsBatchMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseGoodsBatchAll() {
        // Given
        BasicBaseGoodsBatchListVO vo = new BasicBaseGoodsBatchListVO();
        when(request.getHeader("id")).thenReturn("12345");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");
        when(basicBaseGoodsBatchMapper.getBasicBaseGoodsBatchAll(vo)).thenReturn(Collections.emptyList());

        // When
        List<BasicBaseGoodsBatchResVO> result = basicBaseGoodsBatchService.getBasicBaseGoodsBatchAll(vo);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateBasicBaseGoodsBatch() {
        // Given
        BasicBaseGoodsBatchAddVO vo = new BasicBaseGoodsBatchAddVO();
        vo.setBatchCode("batchCode");
        vo.setGoodsId(1L);
        when(basicBaseGoodsBatchMapper.selectCount(any())).thenReturn(0L);
        when(TokenUtils.extractUserIdFromHttpReqeust(request)).thenReturn("1");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");
        when(basicBaseGoodsBatchMapper.createBasicBaseGoodsBatch(any())).thenReturn(1);

        // When
        int result = basicBaseGoodsBatchService.createBasicBaseGoodsBatch(vo);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testCreateBasicBaseGoodsBatch_ThrowsException() {
        // Given
        BasicBaseGoodsBatchAddVO vo = new BasicBaseGoodsBatchAddVO();
        vo.setBatchCode("batchCode");
        vo.setGoodsId(1L);
        when(basicBaseGoodsBatchMapper.selectCount(any())).thenReturn(1L);

        // When & Then
        assertThrows(QslException.class, () -> basicBaseGoodsBatchService.createBasicBaseGoodsBatch(vo));
    }

    @Test
    void testUpdateBasicBaseGoodsBatch() {
        // Given
        BasicBaseGoodsBatchUpdateVO updateVO = new BasicBaseGoodsBatchUpdateVO();
        updateVO.setBatchCode("batchCode");
        updateVO.setGoodsId(1L);
        updateVO.setId(1L);
        when(basicBaseGoodsBatchMapper.selectCount(any())).thenReturn(0L);
        when(TokenUtils.extractUserIdFromHttpReqeust(request)).thenReturn("1");
        when(basicBaseGoodsBatchMapper.updateBasicBaseGoodsBatch(any())).thenReturn(1);

        // When
        int result = basicBaseGoodsBatchService.updateBasicBaseGoodsBatch(updateVO);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseGoodsBatch_ThrowsException() {
        // Given
        BasicBaseGoodsBatchUpdateVO updateVO = new BasicBaseGoodsBatchUpdateVO();
        updateVO.setBatchCode("batchCode");
        updateVO.setGoodsId(1L);
        updateVO.setId(1L);
        when(basicBaseGoodsBatchMapper.selectCount(any())).thenReturn(1L);

        // When & Then
        assertThrows(QslException.class, () -> basicBaseGoodsBatchService.updateBasicBaseGoodsBatch(updateVO));
    }

    @Test
    void testDelBasicBaseGoodsBatchById() {
        // Given
        Long id = 1L;
        when(basicBaseGoodsBatchMapper.delBasicBaseGoodsBatchById(id)).thenReturn(1);

        // When
        int result = basicBaseGoodsBatchService.delBasicBaseGoodsBatchById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseGoodsBatchById() {
        // Given
        Long id = 1L;
        BasicBaseGoodsBatchResVO resVO = new BasicBaseGoodsBatchResVO();
        when(basicBaseGoodsBatchMapper.getBasicBaseGoodsBatchById(id)).thenReturn(resVO);

        // When
        BasicBaseGoodsBatchResVO result = basicBaseGoodsBatchService.getBasicBaseGoodsBatchById(id);

        // Then
        assertNotNull(result);
        assertEquals(resVO, result);
    }
}
