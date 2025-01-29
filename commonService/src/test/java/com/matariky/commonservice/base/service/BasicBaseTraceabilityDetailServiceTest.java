package com.matariky.commonservice.base.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.mockito.Mock;
import com.matariky.commonservice.base.bean.BasicBaseTraceabilityDetail;
import com.matariky.commonservice.base.mapper.BasicBaseTraceabilityDetailMapper;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.constant.PermissionConstant;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseTraceabilityDetailServiceTest {

    @InjectMocks
    private BasicBaseTraceabilityDetailService basicBaseTraceabilityDetailService;

    @Mock
    private BasicBaseTraceabilityDetailMapper basicBaseTraceabilityDetailMapper;

    @Mock
    private HttpServletRequest request;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseTraceabilityDetailAll() {
        // Given
        BasicBaseTraceabilityDetail vo = new BasicBaseTraceabilityDetail();
        when(request.getHeader("id")).thenReturn("12345");
        when(request.getParameter("index")).thenReturn("1");
        when(request.getParameter("perPage")).thenReturn("10");
        when(TokenUtils.extractTenantIdFromHttpReqeust(request)).thenReturn("tenantId");
        CommonDictType commonDictType = new CommonDictType();
        commonDictType.setId(12l);
        when(commonDictTypeService.getDictTypeByKey("tenantId", PermissionConstant.DATA_ACCESS_PERMISSION))
                .thenReturn(commonDictType);
        CommonDict dict = new CommonDict();
        dict.setDictValue("strategyCode");
        when(commonDictService.getCommonDictByIdTenantIdAndDictType("dp1234", "tenantId", 12l))
                .thenReturn(dict);
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailAll(vo)).thenReturn(List.of(vo));

        // When
        List<BasicBaseTraceabilityDetail> result = basicBaseTraceabilityDetailService
                .getBasicBaseTraceabilityDetailAll(vo);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("strategyCode", vo.getStrategyCode());
    }

    @Test
    void testCreateBasicBaseTraceabilityDetail() {
        // Given
        BasicBaseTraceabilityDetail bean = new BasicBaseTraceabilityDetail();
        when(basicBaseTraceabilityDetailMapper.createBasicBaseTraceabilityDetail(bean)).thenReturn(1);

        // When
        int result = basicBaseTraceabilityDetailService.createBasicBaseTraceabilityDetail(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseTraceabilityDetail() {
        // Given
        BasicBaseTraceabilityDetail bean = new BasicBaseTraceabilityDetail();
        when(basicBaseTraceabilityDetailMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseTraceabilityDetailService.updateBasicBaseTraceabilityDetail(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseTraceabilityDetailById() {
        // Given
        int id = 1;
        when(basicBaseTraceabilityDetailMapper.delBasicBaseTraceabilityDetailById(id)).thenReturn(1);

        // When
        int result = basicBaseTraceabilityDetailService.delBasicBaseTraceabilityDetailById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseTraceabilityDetailById() {
        // Given
        int id = 1;
        BasicBaseTraceabilityDetail detail = new BasicBaseTraceabilityDetail();
        when(basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailById(id)).thenReturn(detail);

        // When
        BasicBaseTraceabilityDetail result = basicBaseTraceabilityDetailService.getBasicBaseTraceabilityDetailById(id);

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetBasicBaseTraceabilityDetailDAC() {
        // Given
        Map<String, Object> params = mock(Map.class);
        when(basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailDAC(params))
                .thenReturn(List.of(new BasicBaseTraceabilityDetail()));

        // When
        List<BasicBaseTraceabilityDetail> result = basicBaseTraceabilityDetailService
                .getBasicBaseTraceabilityDetailDAC(params, request);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetBasicBaseTraceabilityDetailDACCount() {
        // Given
        Map<String, Object> params = mock(Map.class);
        when(basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailDACCount(params)).thenReturn(1L);

        // When
        Long result = basicBaseTraceabilityDetailService.getBasicBaseTraceabilityDetailDACCount(params, request);

        // Then
        assertEquals(1L, result);
    }
}
