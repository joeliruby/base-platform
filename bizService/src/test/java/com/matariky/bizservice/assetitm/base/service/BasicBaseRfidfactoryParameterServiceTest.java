package com.matariky.bizservice.assetitm.base.service;

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
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidfactoryParameter;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidfactoryParameterMapper;
import com.matariky.constant.PermissionConstant;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class BasicBaseRfidfactoryParameterServiceTest {

    @InjectMocks
    private BasicBaseRfidfactoryParameterService basicBaseRfidfactoryParameterService;

    @Mock
    private BasicBaseRfidfactoryParameterMapper basicBaseRfidfactoryParameterMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidfactoryParameterAll() {
        // Given
        List<BasicBaseRfidfactoryParameter> expectedList = List.of(new BasicBaseRfidfactoryParameter());
        when(basicBaseRfidfactoryParameterMapper.getBasicBaseRfidfactoryParameterAll()).thenReturn(expectedList);

        // When
        List<BasicBaseRfidfactoryParameter> result = basicBaseRfidfactoryParameterService
                .getBasicBaseRfidfactoryParameterAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseRfidfactoryParameter() {
        // Given
        BasicBaseRfidfactoryParameter parameter = new BasicBaseRfidfactoryParameter();
        when(basicBaseRfidfactoryParameterMapper.createBasicBaseRfidfactoryParameter(parameter)).thenReturn(1);

        // When
        int result = basicBaseRfidfactoryParameterService.createBasicBaseRfidfactoryParameter(parameter);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidfactoryParameter() {
        // Given
        BasicBaseRfidfactoryParameter parameter = new BasicBaseRfidfactoryParameter();
        when(basicBaseRfidfactoryParameterMapper.updateById(parameter)).thenReturn(1);

        // When
        int result = basicBaseRfidfactoryParameterService.updateBasicBaseRfidfactoryParameter(parameter);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidfactoryParameterById() {
        // Given
        Long id = 1L;
        when(basicBaseRfidfactoryParameterMapper.delBasicBaseRfidfactoryParameterById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidfactoryParameterService.delBasicBaseRfidfactoryParameterById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidfactoryParameterById() {
        // Given
        Long id = 1L;
        BasicBaseRfidfactoryParameter expectedParameter = new BasicBaseRfidfactoryParameter();
        when(basicBaseRfidfactoryParameterMapper.getBasicBaseRfidfactoryParameterById(id))
                .thenReturn(expectedParameter);

        // When
        BasicBaseRfidfactoryParameter result = basicBaseRfidfactoryParameterService
                .getBasicBaseRfidfactoryParameterById(id);

        // Then
        assertEquals(expectedParameter, result);
    }

    @Test
    void testGetBasicBaseRfidfactoryParameterDAC() {
        // Given
        Map<String, Object> params = Map.of();
        List<BasicBaseRfidfactoryParameter> expectedList = List.of(new BasicBaseRfidfactoryParameter());
        when(basicBaseRfidfactoryParameterMapper.getBasicBaseRfidfactoryParameterDAC(params)).thenReturn(expectedList);

        // When
        List<BasicBaseRfidfactoryParameter> result = basicBaseRfidfactoryParameterService
                .getBasicBaseRfidfactoryParameterDAC(params, request);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testGetBasicBaseRfidfactoryParameterDACCount() {
        // Given
        Map<String, Object> params = Map.of("strategyCode", PermissionConstant.COMMON_DATA_ACCESS_PRIVATE);
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(basicBaseRfidfactoryParameterMapper.getBasicBaseRfidfactoryParameterDACCount(params)).thenReturn(1L);

        // When
        Long result = basicBaseRfidfactoryParameterService.getBasicBaseRfidfactoryParameterDACCount(params, request);

        // Then
        assertEquals(1L, result);
    }
}
